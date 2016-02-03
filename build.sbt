name := "nullable"

organization := "com.hevylight"

version := "2.1.0"

scalaVersion := "2.11.7"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"

scalacOptions ++= Seq("-optimize", "-Yinline-warnings")
    