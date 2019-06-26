package learnfp.typeclass

trait Eq[A] {
  def eq(lhs: A, rhs: A): Boolean
}

object Eq {
  def eq[A](lhs: A, rhs: A)(implicit eqt: Eq[A]) = eqt.eq(lhs, rhs)
}

class EqOps[A](lhs: A)(implicit eqt: Eq[A]) {
  def ====(rhs: A): Boolean = eqt.eq(lhs, rhs)
}

object EqOps {
  implicit def toEqOps[A](lhs: A)(implicit eqt: Eq[A]) = new EqOps(lhs)
}

object EqInstances {
  implicit val intEqInstance = new Eq[Int] {
    override def eq(lhs: Int, rhs: Int): Boolean = lhs == rhs
  }

  implicit val stringEqInstance = new Eq[String] {
    override def eq(lhs: String, rhs: String): Boolean = lhs == rhs
  }

  implicit def listEqInstance[A: Eq] = new Eq[List[A]] {
    override def eq(lhs: List[A], rhs: List[A]): Boolean =
    (lhs, rhs) match {
      case (l::Nil, r::Nil) => implicitly[Eq[A]].eq(l, r)
      case (l::ltail, r::rtail) => implicitly[Eq[A]].eq(l, r) && eq(ltail, rtail)
      case (_, _) => false
    }
  }
}
