package uk.me.lings

package object scalaguice {
    
    import com.google.inject._

    import java.lang.reflect.Type
    
    private[scalaguice] def typeOf[T](implicit m: Manifest[T]): Type = {
        def toWrapper(c:Type) = c match {
            case java.lang.Byte.TYPE => classOf[java.lang.Byte]
            case java.lang.Short.TYPE => classOf[java.lang.Short]
            case java.lang.Character.TYPE => classOf[java.lang.Character]
            case java.lang.Integer.TYPE => classOf[java.lang.Integer]
            case java.lang.Long.TYPE => classOf[java.lang.Long]
            case java.lang.Float.TYPE => classOf[java.lang.Float]
            case java.lang.Double.TYPE => classOf[java.lang.Double]
            case java.lang.Boolean.TYPE => classOf[java.lang.Boolean]
            case java.lang.Void.TYPE => classOf[java.lang.Void]
            case cls => cls
        }
        import com.google.inject.util.Types
        m.typeArguments match {
            case Nil => toWrapper(m.erasure)
            case args => m.erasure match {
                case c:Class[_] if c.getEnclosingClass == null => Types.newParameterizedType(c, args.map(typeOf(_)):_*)
                case c:Class[_] => Types.newParameterizedTypeWithOwner(c.getEnclosingClass, c, args.map(typeOf(_)):_*)
            }
        }
    }
    
    def typeLiteral[T : Manifest]: TypeLiteral[T] = {
        TypeLiteral.get(typeOf[T]).asInstanceOf[TypeLiteral[T]]
    }
    
    import java.lang.annotation.{Annotation => JAnnotation}
    
    type AnnotationClass = Class[_ <: JAnnotation]
    
    def annotation[T <: JAnnotation : ClassManifest]: AnnotationClass = {
        classManifest[T].erasure.asInstanceOf[AnnotationClass]
    }

}
