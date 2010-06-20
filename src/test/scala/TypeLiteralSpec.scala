package uk.me.lings.scalaguice

import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers

class TypeLiteralSpec extends Spec with ShouldMatchers {
  
  import com.google.inject._

  object Outer {
    class Inner
  }

  describe("type literal creation") {

    it("should create a type literal from a non-generic reference type") {
      typeLiteral[String] should equal (TypeLiteral.get(classOf[String]))
    }

    it("should create a type literal from a generic reference type") {
      typeLiteral[List[String]] should equal (new TypeLiteral[List[String]] {})
    }

    it("should convert type parameters to wrapper classes") {
      typeLiteral[List[Int]] should equal (new TypeLiteral[List[java.lang.Integer]] {})
    }
    
    it("should handle nested types") {
      typeLiteral[Outer.Inner] should equal (TypeLiteral.get(classOf[Outer.Inner]))
    }

    it("should handle type parameters that are nested types") {
      typeLiteral[List[Outer.Inner]] should equal (new TypeLiteral[List[Outer.Inner]] {})
    }
    
    it("should handle type parameters that are arrays") {
      typeLiteral[Array[Int]] should equal (new TypeLiteral[Array[Int]] {})
    }

  }




}
