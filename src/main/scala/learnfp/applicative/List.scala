package learnfp.applicative

object ListInstance {
  import learnfp.functor.ListInstance._
  import learnfp.functor.FunctorOps._
  implicit def appListInstance = new Applicative[List] {
    override def pure[A](a: A): List[A] = List[A](a)
    override def <*>[A, R](fxs: List[A => R])(as: List[A]): List[R] = {
      fxs match {
        case Nil => Nil
        case fx :: fys => {
          as.fmap(fx) ::: <*>(fys)(as)
        }
      }
    }
  }
}
