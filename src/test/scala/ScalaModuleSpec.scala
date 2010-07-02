package uk.me.lings.scalaguice

import org.scalatest.WordSpec
import org.scalatest.matchers.ShouldMatchers

import com.google.inject._

class ScalaModuleSpec extends WordSpec with ShouldMatchers {

  "A Scala Guice module" should {

    "allow binding source type using a type parameter" in {
      val module = new AbstractModule with ScalaModule {
        def configure = {
          bind[A].to(classOf[B])
        }
      }
      Guice.createInjector(module).getInstance(classOf[A])
    }

    "allow binding target type using a type parameter" in {
      val module = new AbstractModule with ScalaModule {
        def configure = {
          bind[A].to[B]
        }
      }
      Guice.createInjector(module).getInstance(classOf[A])
    }

    "allow binding in scope using a type parameter" in {
      val module = new AbstractModule with ScalaModule {
        def configure = {
          bind[A].to[B].in[Singleton]
        }
      }
      Guice.createInjector(module).getInstance(classOf[A])
    }

    "allow binding with annotation using a type parameter" in {
      import name.Named
      val module = new AbstractModule with ScalaModule {
        def configure = {
          bind[A].annotatedWith[Named].to[B]
        }
      }
      Guice.createInjector(module).getInstance(Key.get(classOf[A],classOf[Named]))
    }

  }

}
