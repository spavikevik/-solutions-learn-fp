package learnfp.typeclass

trait Show[A] {
  def show(x: A): String
}

object Printer {
  def show[A](x: A)(implicit showInstance: Show[A]): String = {
    showInstance.show(x)
  }
}

object ShowInstances {
  implicit val intInstance: Show[Int] = new Show[Int] {
    override def show(x: Int): String = x.toString
  }

  implicit val doubleInstance: Show[Double] = new Show[Double] {
    override def show(x: Double): String = x.toString
  }

  implicit def listInstance[T: Show]: Show[List[T]] = new Show[List[T]] {
    override def show(xs: List[T]): String = {
      def showElements(xs: List[T]): String = {
        xs match {
          case x :: Nil => implicitly[Show[T]].show(x)
          case x :: tail => implicitly[Show[T]].show(x) + ", " + showElements(tail)
          case _ => ""
        }
      }
      "[" + showElements(xs) + "]"
    }
  }
}