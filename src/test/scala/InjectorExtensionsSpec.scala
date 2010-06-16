package uk.me.lings.scalaguice

import org.scalatest.WordSpec
import org.scalatest.matchers.ShouldMatchers

import com.google.inject._
import com.google.inject.name.Names.named

class InjectorExtensionsSpec extends WordSpec with ShouldMatchers {
  
  import InjectorExtensions._

  val module = new AbstractModule {
    def configure = {
      bind(classOf[A]).to(classOf[B])
      bind(classOf[A]).annotatedWith(named("d")).to(classOf[B])
      bind(new TypeLiteral[Gen[String]]{}).to(classOf[C])
    }
  }

  val injector = Guice createInjector module

  "Injector extensions" should {
  
    "allow instance to be retrieved using a type parameter" in {
      injector.instance[A]
    }

    "allow generic instance to be retrieved using a type parameter" in {
      val inst = injector.instance[Gen[String]]
      inst.get should equal ("String")
    }
  }


}