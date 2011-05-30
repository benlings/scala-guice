import sbt._

class ScalaGuice(info: ProjectInfo) extends DefaultProject(info) {

  // Repositories

  // Dependencies
  val guice2 = "com.google.inject" % "guice" % "2.0"
  val TwoPointEight = """2\.8\.\d""".r
  val TwoPointNine = """2\.9\.[\d.-]+""".r

  val scalatest =
    buildScalaVersion match {
      case TwoPointEight() => "org.scalatest" % "scalatest" % "1.3"
      case TwoPointNine() => "org.scalatest" % "scalatest_2.9.0" % "1.4.1"
      case x => error("Unsupported Scala version " + x)
    }

}
