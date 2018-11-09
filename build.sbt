import Dependencies.deps

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization    := "org.circolinohack",
      scalaVersion    := "2.12.7"
    )),
    name := "Scala Video Stream",
    libraryDependencies ++= deps
  )
