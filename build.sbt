ThisBuild / version := "1.0.0-dev"
ThisBuild / organization := "org.onesoftnet"
ThisBuild / scalaVersion := "3.4.0"
ThisBuild / assemblyMergeStrategy := {
    case PathList("module-info.class") => MergeStrategy.last
    case path if path.endsWith("/module-info.class") || path.endsWith("okio.kotlin_module") => MergeStrategy.last
    case x =>
        val oldStrategy = (assembly / assemblyMergeStrategy).value
        oldStrategy(x)
}

val rootPackage = "org.onesoftnet.mailbot"
val mainClassName = Some(s"$rootPackage.Main")

Compile / mainClass := mainClassName
assembly / mainClass := mainClassName

lazy val root = (project in file("."))
    .settings(
        name := "mailbot-scala",
    )

libraryDependencies ++= Seq(
    "net.dv8tion" % "JDA" % "5.0.0-beta.21",
    "io.github.cdimascio" % "dotenv-java" % "3.0.0",
    "jline" % "jline" % "2.14.6",
    "org.slf4j" % "slf4j-nop" % "2.0.12",
    "com.google.guava" % "guava" % "33.1.0-jre"
)

updateOptions := updateOptions.value.withCachedResolution(true)
