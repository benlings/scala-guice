import sbt._

class ScalaGuice(info: ProjectInfo) extends DefaultProject(info) {

  // Repositories

  // Dependencies
  val scalatest = "org.scalatest" % "scalatest" % "1.3"
  val guice2 = "com.google.inject" % "guice" % "2.0"

}
