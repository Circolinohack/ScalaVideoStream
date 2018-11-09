import sbt._

object Dependencies {

  private lazy val akkaHttpVersion: String = "10.1.5"
  private lazy val akkaVersion: String = "2.5.18"

  private lazy val akkaHttp: ModuleID = "com.typesafe.akka" %% "akka-http" % akkaHttpVersion
  private lazy val akkaHttpSprayJson: ModuleID = "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion
  private lazy val akkaHttpXml: ModuleID = "com.typesafe.akka" %% "akka-http-xml" % akkaHttpVersion
  private lazy val akkaStream: ModuleID = "com.typesafe.akka" %% "akka-stream" % akkaVersion

  private lazy val akkaTestkit: ModuleID = "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test
  private lazy val akkaHttpTestkit: ModuleID = "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test
  private lazy val akkaStreamTestkit: ModuleID = "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % Test
  private lazy val scalatest: ModuleID = "org.scalatest" %% "scalatest" % "3.0.5" % Test

  lazy val deps: Seq[ModuleID] = Seq(akkaHttp,
    akkaHttpSprayJson,
    akkaHttpXml,
    akkaStream,
    akkaTestkit,
    akkaHttpTestkit,
    akkaStreamTestkit,
    scalatest)

}
