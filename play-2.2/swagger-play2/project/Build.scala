import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {
  val appName = "swagger-play2"
  val appVersion = "1.3.7"

  checksums in update := Nil

  val appDependencies: Seq[sbt.ModuleID] = Seq(
    "org.slf4j" % "slf4j-api" % "1.6.4",
    "com.wordnik" % "swagger-jaxrs_2.10" % "1.3.7",
    "javax.ws.rs" % "jsr311-api" % "1.1.1",
    "org.mockito" % "mockito-core" % "1.9.5" % "test")

  val main = play.Project(appName, appVersion, appDependencies).settings(
    crossScalaVersions := Seq("2.9.1", "2.10.4"),
    publishTo <<= version { (v: String) =>
      val nexus = "https://oss.sonatype.org/"
      if (v.trim.endsWith("SNAPSHOT"))
        Some("snapshots" at nexus + "content/repositories/snapshots")
      else
        Some("releases"  at nexus + "service/local/staging/deploy/maven2")
    },
    publishArtifact in Test := false,
    publishMavenStyle := true,
    pomIncludeRepository := { x => false },
    credentials += Credentials(Path.userHome / ".ivy2" / ".credentials"),
    organization := "com.wordnik",
    pomExtra := (
  <url>http://swagger.io</url>
  <licenses>
    <license>
      <name>Apache License 2.0</name>
      <url>http://www.apache.org/licenses/LICENSE-2.0.html</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:swagger-api/swagger-play.git</url>
    <connection>scm:git:git@github.com:swagger-api/swagger-play.git</connection>
  </scm>
  <developers>
    <developer>
      <id>fehguy</id>
      <name>Tony Tam</name>
      <email>fehguy@gmail.com</email>
    </developer>
    <developer>
      <id>ayush</id>
      <name>Ayush Gupta</name>
      <email>ayush@glugbot.com</email>
    </developer>
  </developers>)
    ,
    resolvers := Seq(
      "maven-central" at "https://repo1.maven.org/maven2",
      "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository",
      "sonatype-snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
      "sonatype-releases" at "https://oss.sonatype.org/content/repositories/releases",
      "java-net" at "http://download.java.net/maven/2",
      "Typesafe Snapshots" at "http://repo.typesafe.com/typesafe/snapshots/",
      "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"))
}
