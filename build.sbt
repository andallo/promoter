import com.typesafe.sbt.packager.archetypes.JavaAppPackaging
import play.sbt.PlayJava


val commonSettings = Seq(
  organization := "carbay",
  version := "1.0",
  scalaVersion := "2.12.3",
  scalacOptions ++= Seq("-encoding", "UTF8", "-deprecation"),
  javacOptions ++= Seq("-encoding", "UTF-8")
)

lazy val scanner: Project = Project(id = "scanner", base = file("scanner"))
  .enablePlugins(JavaAppPackaging)
  .settings(commonSettings: _*)
  .settings(
    checksums := Nil,
    name := "promo_scanner",
    mainClass in Compile := Some("ru.carbay.promoter.Main"),
    libraryDependencies ++= Seq(
      "org.seleniumhq.selenium" % "selenium-java" % "3.5.3",
      "org.quartz-scheduler" % "quartz" % "2.2.2",
      "joda-time" % "joda-time" % "2.2",
      "javax.mail" % "mail" % "1.4.7"
    )
  ).dependsOn(datastore)

lazy val web = Project(id = "web", base = file("web"))
  .enablePlugins(PlayJava)
  .settings(commonSettings: _*)
  .settings(
    checksums := Nil,
    name := "promo_web",
    libraryDependencies ++= Seq(

    )
  ).dependsOn(datastore)

lazy val datastore = Project(id = "datastore", base = file("datastore"))
  .settings(commonSettings: _*)
  .settings(
    checksums := Nil,
    name := "promo_datastore",
    libraryDependencies ++= Seq(
      "org.mongodb" % "mongo-java-driver" % "3.1.0",
      "org.mongodb.morphia" % "morphia" % "1.0.1",
      "org.mongodb.morphia" % "morphia-logging-slf4j" % "1.0.1"
    )
  )

