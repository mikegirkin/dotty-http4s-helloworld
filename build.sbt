val dottyVersion = "0.25.0-RC2"
val scala213Version = "2.13.1"
val http4sVersion = "1.0.0-M3"


lazy val root = project
  .in(file("."))
  .settings(
    name := "dotty-simple",
    version := "0.1.0",

    fork in run := true,

    scalaVersion := dottyVersion,
    resolvers += Resolver.sonatypeRepo("snapshots"),

    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-dsl" % http4sVersion,
      "org.http4s" %% "http4s-blaze-server" % http4sVersion,
      "org.http4s" %% "http4s-blaze-client" % http4sVersion,
      
      "org.scalatest" %% "scalatest-funsuite" % "3.2.0" % "test",
      "org.scalatest" %% "scalatest-shouldmatchers" % "3.2.0" % "test"
    ).map(_.withDottyCompat(scalaVersion.value)),
  )

