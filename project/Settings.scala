import bintray.BintrayKeys._
import sbt._
import sbt.Keys._

object Settings {
  val common = Seq(
    organization := "danslapman",
    version := "0.2",
    scalaVersion := "2.12.12",
    crossScalaVersions := Seq("2.12.12", "2.13.3"),
    licenses += ("WTFPL", url("http://www.wtfpl.net")),
    bintrayOrganization := Some("danslapman"),
    bintrayReleaseOnPublish in ThisBuild := false
  )
}
