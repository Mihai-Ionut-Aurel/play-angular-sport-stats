name := """scala-play-angular-seed"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava).settings(
  watchSources ++= (baseDirectory.value / "public/ui" ** "*").get
)

resolvers += Resolver.sonatypeRepo("snapshots")

scalaVersion := "2.12.3"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
libraryDependencies += "com.h2database" % "h2" % "1.4.196"

libraryDependencies += "com.nrinaudo" %% "kantan.csv" % "0.3.1"
// Provides instances for joda time types.
libraryDependencies += "com.nrinaudo" %% "kantan.csv-joda-time" % "0.3.1"