import sbt._
import Keys._
import sbtassembly.Plugin._
import AssemblyKeys._

object projectBuild extends Build {

  lazy val root = Project("root", file("."),
    settings = Defaults.defaultSettings ++ assemblySettings)
    .settings(
      name := "",
      organization := "piggydev",
      version := "1.0",
      scalaVersion := "2.11.2",

      //scalacOptions ++= Seq("-language:postfixOps"),

      resolvers ++= Seq(
        //"Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
        ),

      libraryDependencies ++= Seq(
        //"com.novus" %% "salat" % "1.9.2-SNAPSHOT"
      )
    )

}
