enablePlugins(JavaAppPackaging)

name := "akka-request"
organization := "io.dronekit"
version := "3.3.0"
scalaVersion := "2.12.8"

resolvers += "Artifactory" at "https://dronekit.artifactoryonline.com/dronekit/libs-snapshot-local/"
scalacOptions := Seq("-Ywarn-unused-import")

credentials += Credentials(Path.userHome / ".sbt" / ".credentials")
isSnapshot := true
publishTo := {
  val artifactory = "https://dronekit.artifactoryonline.com/"
  if (isSnapshot.value)
    Some("snapshots" at artifactory + s"dronekit/libs-snapshot-local;build.timestamp=${new java.util.Date().getTime}")
  else
    Some("snapshots" at artifactory + "dronekit/libs-release-local")
}

libraryDependencies ++= {
  val akkaV = "2.5.22"
  val akkaHttpV = "10.1.8"
  
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaV,
    "com.typesafe.akka" %% "akka-stream" % akkaV,
    
    "com.typesafe.akka" %% "akka-http-core" % akkaHttpV,
    "com.typesafe.akka" %% "akka-http" % akkaHttpV,

    "com.typesafe.scala-logging" %% "scala-logging" % "3.5.0",
    
    "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpV % Test,
    "de.heikoseeberger" %% "akka-http-play-json" % "1.27.0" % Test,
    "com.typesafe.play" %% "play-json" % "2.7.4" % Test,
    "org.scalatest" %% "scalatest" % "3.0.8" % Test,
    "ch.qos.logback" % "logback-classic" % "1.1.3" % Test
  )
}
