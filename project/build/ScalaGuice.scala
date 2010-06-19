import sbt._

class ScalaGuice(info: ProjectInfo) extends DefaultProject(info) {

  // Repositories
  val scalaToolsSnapshots = "Scala-Tools Maven2 Snapshots Repository" at "http://scala-tools.org/repo-snapshots"

  // Dependencies
  val scalatest = "org.scalatest" % "scalatest" % "1.2-for-scala-2.8.0.RC5-SNAPSHOT"
  val guice2 = "com.google.inject" % "guice" % "2.0"

}
