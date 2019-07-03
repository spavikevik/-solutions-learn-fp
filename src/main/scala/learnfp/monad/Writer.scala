package learnfp.monad

import learnfp.functor.Writer
import learnfp.functor.WriterInstance._
import learnfp.monoid.Monoid
import learnfp.monoid.MonoidOps._

object WriterInstance {
  implicit def writerMonadInstance[W](implicit monoid: Monoid[W]) = new Monad[({type E[X] = Writer[W, X]})#E] {
    override def pure[A](a: A): Writer[W, A] = Writer[W, A](() => (monoid.mzero, a))
    override def flatMap[A, B](a: Writer[W, A])(fx: A => Writer[W, B]): Writer[W, B] = {
      Writer {
        val xa = a.run()
        val b = xa match {
          case (_, x) => fx(x)
        }

        () => (xa, b.run()) match {
          case ((wa, xa), (wb, xb)) => (wa |+| wb, xb)
        }
      }
    }
  }

  implicit def writerToMonadOps[W, A](w: Writer[W, A])(implicit monoid: Monoid[W]) = new MonadOps[A, ({type E[X] = Writer[W, X]})#E](w)
}
