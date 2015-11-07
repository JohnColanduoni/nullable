package nullable

import org.scalatest.{Matchers, FunSpec}

class NullableTests extends FunSpec with Matchers {
  describe("An null nullable") {
    val x: Nullable[Object] = Null

    it("matches as Null") {
      x should matchPattern { case Null => }
    }

    it("does not match as NotNull") {
      x should not matchPattern { case NotNull(_) => }
    }
  }

  describe("A non null nullable") {
    val obj = new Object()
    val x: Nullable[Object] = Nullable(obj)

    it("does not match as Null") {
      x should not matchPattern { case Null => }
    }

    it("matches as a NotNull") {
      x should matchPattern { case NotNull(`obj`) => }
    }
  }
}
