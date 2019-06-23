val Http4sVersion = "0.20.3"
val CirceVersion = "0.11.1"
val Specs2Version = "4.1.0"
val LogbackVersion = "1.2.3"

lazy val baseSettings = Seq(
  organization := "io.kellydavid",
  name := "http4s-aws-lambda",
  version := "0.0.1-SNAPSHOT",
  scalaVersion := "2.12.8",
  scalacOptions ++= customScalacOptions,
  libraryDependencies ++= Seq(
    "org.http4s"      %% "http4s-blaze-server" % Http4sVersion,
    "org.http4s"      %% "http4s-blaze-client" % Http4sVersion,
    "org.http4s"      %% "http4s-circe"        % Http4sVersion,
    "org.http4s"      %% "http4s-dsl"          % Http4sVersion,
    "io.circe"        %% "circe-generic"       % CirceVersion,
    "org.specs2"      %% "specs2-core"         % Specs2Version % "test",
    "ch.qos.logback"  %  "logback-classic"     % LogbackVersion
  ),
  addCompilerPlugin("org.typelevel" %% "kind-projector"     % "0.10.3"),
  addCompilerPlugin("com.olegpy"    %% "better-monadic-for" % "0.3.0")
)

lazy val http4sawslambda = project.aggregate(
  server,
  resourceHello,
  resourceJoke
).settings(baseSettings)

lazy val server = (project in file("server"))
  .settings(baseSettings ++ Seq(
    name := "server"
  )).dependsOn(resourceHello, resourceJoke)

lazy val resourceHello = (project in file("resource-hello"))
  .settings(baseSettings ++ Seq(
    name := "resource-hello"
  ))

lazy val resourceJoke = (project in file("resource-joke"))
  .settings(baseSettings ++ Seq(
    name := "resource-joke"
  ))

lazy val customScalacOptions = Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-language:higherKinds",
  "-language:postfixOps",
  "-feature",
  "-Ypartial-unification",
  "-Xfatal-warnings",
)
