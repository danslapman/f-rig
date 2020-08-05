import algebra.Eq
import algebra.ring.Rig
import cats.{FlatMap, Monad}
import cats.syntax.all._

import scala.language.higherKinds

package object frig {
  implicit class FRig[F[_], A](val fa: F[A]) extends AnyVal {
    def \+\(fb: => F[A])(implicit M: FlatMap[F], rig: Rig[A], eq: Eq[A]): F[A] = fa.flatMap {
      case zero if Rig[A].isZero(zero) => fb
      case a => fb.map(Rig.plus(a, _))
    }

    def \*\(fb: => F[A])(implicit M: Monad[F], rig: Rig[A], eq: Eq[A]): F[A] = fa.flatMap {
      case zero if Rig[A].isZero(zero) => zero.pure[F]
      case one if Rig[A].isOne(one) => fb
      case a => fb.map(Rig[A].times(a, _))
    }
  }

  implicit class FRigBool[F[_]](val fa: F[Boolean]) extends AnyVal {
    def \&&\(fb: => F[Boolean])(implicit M: Monad[F]): F[Boolean] = fa.flatMap {
      case false => false.pure[F]
      case true => fb
    }

    def \||\(fb: => F[Boolean])(implicit M: Monad[F]): F[Boolean] = fa.flatMap {
      case true => true.pure[F]
      case false => fb
    }
  }
}
