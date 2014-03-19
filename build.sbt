import AssemblyKeys._

name := """elasticsearch-tablewatch-river"""

version := "1.0"

scalaVersion := "2.10.2"

resolvers += "sonatype-snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

libraryDependencies += "org.elasticsearch" % "elasticsearch" % "0.19.8"

libraryDependencies += "com.github.shyiko" % "mysql-binlog-connector-java" % "0.1.0-SNAPSHOT"

libraryDependencies += "com.typesafe.akka" % "akka-actor_2.10" % "2.3-M1"

test in assembly := {}

mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
    {
        case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
        case _ => MergeStrategy.first
    }
}