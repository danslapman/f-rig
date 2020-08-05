lazy val root = (project in file("."))
  .settings(Settings.common)
  .settings(
    name := "f-rig",
    parallelExecution in ThisBuild := false,
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-core" % "2.1.1",
      "org.typelevel" %% "algebra" % "2.0.1",
      "org.scalatest" %% "scalatest" % "3.0.8" % Test
    )
  )