import algebra.Eq
import algebra.ring.Rig
import cats.FlatMap
import cats.syntax.flatMap._
import cats.syntax.functor._

import scala.language.higherKinds

package object frig {
  implicit class FRig[F[_], A](val fa: F[A]) extends AnyVal {
    def \+\(fb: => F[A])(implicit M: FlatMap[F], rig: Rig[A], eq: Eq[A]): F[A] = fa.flatMap {
      case zero if Rig[A].isZero(zero) => fb
      case a => fb.map(Rig.plus(a, _))
    }

    def \*\(fb: => F[A])(implicit M: FlatMap[F], rig: Rig[A], eq: Eq[A]): F[A] = fa.flatMap {
      case zero if Rig[A].isZero(zero) => fa
      case one if Rig[A].isOne(one) => fb
      case a => fb.map(Rig[A].times(a, _))
    }
  }

  implicit class FRigBool[F[_]](val fa: F[Boolean]) extends AnyVal {
    def \&&\(fb: => F[Boolean])(implicit M: FlatMap[F]): F[Boolean] = fa.flatMap {
      case false => fa
      case true => fb
    }

    def \||\(fb: => F[Boolean])(implicit M: FlatMap[F]): F[Boolean] = fa.flatMap {
      case true => fa
      case false => fb
    }
  }
}
