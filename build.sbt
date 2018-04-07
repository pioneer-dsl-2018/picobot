name := "Picobot Internal"

version := "0.1"

scalaVersion := "2.12.5"

libraryDependencies += "org.scalafx" %% "scalafx" % "8.0.144-R12"

lazy val root = Project("root", file(".")) dependsOn(picolib)
lazy val picolib = RootProject(uri("https://github.com/bwiedermann/picolib.git#2.0"))
