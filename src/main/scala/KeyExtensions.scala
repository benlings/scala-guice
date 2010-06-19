package uk.me.lings.scalaguice

import com.google.inject._

object KeyExtensions {

    import java.lang.annotation.{Annotation => JAnnotation}

    implicit def enrichTypeLiteral[T](t: TypeLiteral[T]) = new {
        def toKey: Key[T] = Key.get(t)
        def annotatedWith(annotation: JAnnotation): Key[T] = Key.get(t, annotation)
        def annotatedWith[TAnn <: JAnnotation : ClassManifest]:Key[T] = 
            Key.get(t, annotation[TAnn])
    }
        
}
