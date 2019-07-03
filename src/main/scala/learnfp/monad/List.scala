package learnfp.monad

import learnfp.functor.ListInstance._

object ListInstance {
  implicit val listMonadInstance = new Monad[List] {
    override def pure[A](a: A): List[A] = List[A](a)
    override def flatMap[A, B](a: List[A])(fx: A => List[B]): List[B] = {
      a match {
        case Nil => Nil
        case head :: tl => fx(head) ::: flatMap(tl)(fx)
      }
    }
  }
}
