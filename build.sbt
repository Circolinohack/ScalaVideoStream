import Dependencies.deps

lazy val root = (project in file(".")).
  settings(
    organization    := "org.circolinohack",
    name := "Scala Video Stream",
    scalaVersion    := "2.12.7",
    libraryDependencies ++= deps,
    scalafmtOnCompile := true
  )
