
lazy val ScalatraVersion = "2.7.0"

lazy val root = (project in file("."))
  .enablePlugins(SbtTwirl)
  .enablePlugins(ScalatraPlugin)
  .settings(
    organization := "com.lubo",
    name := "kube-micro-master",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := "2.13.2",
    resolvers += Classpaths.typesafeReleases,
    mainClass in assembly := Some("com.lubo.ServerLauncher"),
    assemblyJarName in assembly := "kube-master.jar",
    assemblyMergeStrategy in assembly := {
      case x if x.contains("io.netty.versions.properties") => MergeStrategy.discard
      case x =>
        val oldStrategy = (assemblyMergeStrategy in assembly).value
        oldStrategy(x)
    },
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.client" %% "core" % "2.2.1",
      "com.softwaremill.sttp.client" %% "async-http-client-backend-future" % "2.2.0",
      "com.softwaremill.sttp.client" %% "circe" % "2.2.0",
      "io.circe" %% "circe-generic" % "0.13.0",
      "org.scalatra" %% "scalatra" % ScalatraVersion,
      "org.scalatra" %% "scalatra-scalatest" % ScalatraVersion % "test",
      "org.scalatest" %% "scalatest" % "3.0.8" % "test",
      "ch.qos.logback" % "logback-classic" % "1.2.3" % "runtime",
      "org.eclipse.jetty" % "jetty-webapp" % "9.4.28.v20200408" % "container;compile",
      "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided"
    ),
  )


