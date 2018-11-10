import sbt._

object Dependencies {

  private lazy val akkaHttpVersion: String = "10.1.5"
  private lazy val akkaVersion: String = "2.5.18"

  private lazy val akkaHttp: ModuleID = "com.typesafe.akka" %% "akka-http" % akkaHttpVersion
  private lazy val akkaStream: ModuleID = "com.typesafe.akka" %% "akka-stream" % akkaVersion
  private lazy val alpakkaFile: ModuleID = "com.lightbend.akka" %% "akka-stream-alpakka-file" % "1.0-M1"


  lazy val deps: Seq[ModuleID] = Seq(akkaHttp, akkaStream, alpakkaFile)

}
