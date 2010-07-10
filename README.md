Scala extensions for Google Guice

Features:

* `Manifest[T]` -> `TypeLiteral[T]` conversion (`typeLiteral[T]`)
* Binding DSL extensions using type parameters to specify types
* ScalaModule trait including the above binding DSL extensions

      class MyModule extends AbstractModule with ScalaModule {
        def configure {
          bind[Service].to[ServiceImpl].in[Singleton]
          bind[CreditCardPaymentService]
          bind[Bar[Foo]].to[FooBarImpl]
          bind[PaymentService].to[CreditCardPaymentService]
        }
      }

Future:

* `TypeLiteral[T]` -> `Manifest[T]` conversion
* Have `Manifest[T]` injected anywhere Guice could inject `TypeLiteral[T]` (see [Jesse's post for context](http://blog.publicobject.com/2008/11/guice-punches-erasure-in-face.html))
* Extractor objects for pattern-matching against the binding AST

If technically possible:

* Inject `Option[T]` for optional dependencies
* Injection that takes variance into account
