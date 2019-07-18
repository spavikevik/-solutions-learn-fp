package learnfp.traversable

import learnfp.applicative.Applicative
import learnfp.foldable.Foldable
import learnfp.functor.{Functor, Id}


trait Traversable[C[_]] {
  def traverse[A, B, F[_]](xs: C[A])(fx: A => F[B])(implicit foldable: Foldable[C], functor: Functor[F], applicative: Applicative[F]): F[C[B]]
}

object TraversableInstances {
  import learnfp.foldable.FoldableInstances._
  import learnfp.applicative.ApplicativeOps._
  import learnfp.functor.FunctorOps._

  implicit val idTraversableInstance = new Traversable[Id] {
    override def traverse[A, B, F[_]](xs: Id[A])(fx: A => F[B])(implicit foldable: Foldable[Id], functor: Functor[F], applicative: Applicative[F]): F[Id[B]] =
      fx(xs.value).fmap(Id(_))
  }

  type STuple3[A] = (A, A, A)
  def stuple3[A](a:A, b:A, c:A):STuple3[A] = (a, b, c)

  implicit val tuple3TraversableInstance = new Traversable[STuple3] {
    override def traverse[A, B, F[_]](xs: (A, A, A))(fx: A => F[B])(implicit foldable: Foldable[STuple3], functor: Functor[F], applicative: Applicative[F]): F[(B, B, B)] =
      {
        val f = {(a: B, b: B, c: B) => (a, b, c)}.curried
        // foldable.foldr(xs)(applicative.pure[B](fxToFunctorOps(f)))
        xs match { // can this be refactored?
          case (a, b, c) => {
            f `<$>` fx(a) <*> fx(b) <*> fx(c) // fxToFunctorOps(f) happens implicitly
          }
        }
      }
  }

  implicit val listTraversableInstance = new Traversable[List] {
    override def traverse[A, B, F[_]](xs: List[A])(fx: A => F[B])(implicit foldable: Foldable[List],
                                                                  functor: Functor[F], applicative: Applicative[F]): F[List[B]] = {
      foldable.foldr(xs)(applicative.pure[List[B]](Nil)) { (x, accum) =>
        {(b: B, lst: List[B]) => b :: lst}.curried `<$>` fx(x) <*> accum
        // {(lst: List[B], b: B) => b :: lst}.curried `<$>` accum <*> fx(x) ?
      }
                                                                  }
  }
}

class TraversableOps[A, C[_]](xs:C[A])(implicit foldable: Foldable[C], traversable: Traversable[C]) {
  def traverse[B, F[_]](fx:A=>F[B])(implicit functor: Functor[F], applicative: Applicative[F]):F[C[B]] = traversable.traverse(xs)(fx)
}

object TraversableOps {
  implicit def toTraversableOps[A, C[_]](xs:C[A])(implicit foldable:Foldable[C], traversable: Traversable[C]) = new TraversableOps[A, C](xs)
}

class SequenceOps[A, C[_], F[_]](xs:C[F[A]])(implicit foldable: Foldable[C], traversable: Traversable[C],
                                             functor: Functor[F], applicative: Applicative[F]) {
  def sequence:F[C[A]] = 
    traversable.traverse(xs) { identity[F[A]] }
}

object SequenceOps {
    implicit def toSequenceOps[A, C[_], F[_]](xs:C[F[A]])(implicit functor:Functor[F], applicative: Applicative[F],
                                                             foldable:Foldable[C], traversable: Traversable[C]) = new SequenceOps[A, C, F](xs)
}


