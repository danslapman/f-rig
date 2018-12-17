lazy val root = (project in file("."))
  .settings(Settings.common)
  .settings(
    name := "f-rig",
    parallelExecution in ThisBuild := false,
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-core" % "1.5.0",
      "org.typelevel" %% "algebra" % "1.0.0",
      "org.scalatest" %% "scalatest" % "3.0.5" % Test
    )
  )