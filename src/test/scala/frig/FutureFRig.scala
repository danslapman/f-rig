package frig

import algebra.instances.boolean._
import algebra.instances.int._
import cats.instances.future._
import org.scalatest.{AsyncFunSuite, Matchers}

import scala.concurrent.Future

class FutureFRig extends AsyncFunSuite with Matchers {
  test("addition") {
    (Future.successful(1) \+\ Future.successful(2)).map(_ shouldEqual 3)
  }

  test("multiplication") {
    (Future.successful(2) \*\ Future.successful(3)).map(_ shouldEqual 6)
  }

  test("\\*\\ laziness") {
    def succTrue = Future.successful(0)
    def failure = Future.failed[Int]{
      fail("rhs should not be called")
    }

    (succTrue \*\ failure).map(_ shouldBe 0)
  }
}
