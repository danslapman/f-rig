package frig

import cats.instances.future._
import org.scalatest.{AsyncFunSuite, Matchers}

import scala.concurrent.Future

class FutureFRigBool extends AsyncFunSuite with Matchers {
  test("conjunction") {
    (Future.successful(true) \&&\ Future.successful(false)).map(_ shouldBe false)
  }

  test("disjunction") {
    (Future.successful(true) \||\ Future.successful(false)).map(_ shouldBe true)
  }

  test("\\&&\\ laziness") {
    (Future.successful(false) \&&\ Future.failed[Boolean](fail("rhs should not be called"))).map(_ shouldBe false)
  }

  test("\\||\\ laziness") {
    (Future.successful(true) \||\ Future.failed[Boolean](fail("rhs should not be called"))).map(_ shouldBe true)
  }
}
