package learnfp.foldable

import learnfp.functor.Disjunction.{Disjunction, LeftDisjunction, RightDisjunction}
import learnfp.functor.Id
import learnfp.functor.Maybe.{Just, Maybe, Nothing}

trait Foldable[C[_]] {
  def foldr[A, B](xs:C[A])(init:B)(fx:(A, B) => B):B
}

object FoldableInstances {
  implicit def idFoldable = new Foldable[Id] {
    override def foldr[A, B](xs: Id[A])(init: B)(fx: (A, B) => B): B = fx(xs.value, init)
  }

  implicit def listFoldable = new Foldable[List] {
    override def foldr[A, B](xs: List[A])(init: B)(fx: (A, B) => B): B = {
      xs match {
        case Nil => init
        case head :: tl => fx(head, foldr(tl)(init)(fx))
      }
    }
  }

  implicit def tuple2Foldable = new Foldable[({type E[X] = Tuple2[X, X]})#E] {
    override def foldr[A, B](xs: (A, A))(init: B)(fx: (A, B) => B): B = {
      xs match {
        case (fst, snd) => fx(fst, fx(snd, init))
      }
    }
  }

  implicit def tuple3Foldable = new Foldable[({type E[X] = (X, X, X)})#E] {
    override def foldr[A, B](xs: (A, A, A))(init: B)(fx: (A, B) => B): B = {
      xs match {
        case (fst, snd, third) => fx(fst, tuple2Foldable.foldr((snd, third))(init)(fx))
      }
    }
  }

  implicit val maybeFoldable = new Foldable[Maybe] {
    override def foldr[A, B](xs: Maybe[A])(init: B)(fx: (A, B) => B): B = {
      xs match {
        case Nothing() => init
        case Just(value) => fx(value, init)
      }
    }
  }

  implicit def disjunctionFoldable[L] = new Foldable[({type E[X] = Disjunction[L, X]})#E] {
    override def foldr[A, B](xs: Disjunction[L, A])(init: B)(fx: (A, B) => B): B = {
      xs match {
        case LeftDisjunction(_) => init
        case RightDisjunction(rightValue) => fx(rightValue, init)
      }
    }
  }
}

class FoldableOps[C[_], A](xs:C[A])(implicit foldable: Foldable[C]) {
  def myfoldr[B](init:B)(fx:(A, B) => B):B = foldable.foldr(xs)(init)(fx)
}

object FoldableOps {
  implicit def toFoldableOps[C[_], A](xs:C[A])(implicit foldable: Foldable[C]) = new FoldableOps[C, A](xs)
  implicit def tuple2ToFoldableOps[A](xs:(A, A))(implicit foldable:Foldable[({type E[X] = (X, X)})#E]) = new FoldableOps[({type E[X] = (X, X)})#E, A](xs)
  implicit def tuple3ToFoldableOps[A](xs:(A, A, A))(implicit foldable:Foldable[({type E[X] = (X, X, X)})#E]) = new FoldableOps[({type E[X] = (X, X, X)})#E, A](xs)
  implicit def disjunctionToFoldableOps[L, A](xs:Disjunction[L, A])(implicit foldable:Foldable[({type E[X] = Disjunction[L, X]})#E]) =
    new FoldableOps[({type E[X] = Disjunction[L, X]})#E, A](xs)
}
