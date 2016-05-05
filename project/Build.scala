import sbt._
import Keys._

object RaikuBuild extends Build {
  override lazy val settings = super.settings ++
    Seq(
      name := "raiku",
      version := "0.7.0-M2",
      organization := "nl.gideondk",
      scalaVersion := "2.11.1",
      publishTo := Some(Resolver.file("file", new File("/Users/gideondk/Development/gideondk-mvn-repo"))),

      resolvers ++= Seq("Typesafe Repository (releases)" at "http://repo.typesafe.com/typesafe/releases/",
                  "Typesafe Repository (snapshots)" at "http://repo.typesafe.com/typesafe/snapshots/",
                  "gideondk-repo" at "https://raw.github.com/gideondk/gideondk-mvn-repo/master",

                  "Scala Tools Repository (snapshots)" at "http://scala-tools.org/repo-snapshots",
                  "Scala Tools Repository (releases)"  at "http://scala-tools.org/repo-releases",

                  "Sonatype OSS Releases" at "http://oss.sonatype.org/content/repositories/releases/",
                  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",

                  "spray repo" at "http://repo.spray.io"))

  lazy val sentinel = ProjectRef(uri("https://github.com/davecromberge/sentinel.git#master"), "sentinel")

  val appDependencies = Seq(
      "io.spray" %%  "spray-json" % "1.2.6",
      "net.sandrogrzicic" %% "scalabuff-runtime" % "1.3.8",
      "org.scalatest" %% "scalatest" % "2.2.0" % "test"
  )


  lazy val root = Project(id = "raiku",
    base = file("."),
    settings = Project.defaultSettings ++ Seq(
      libraryDependencies ++= appDependencies
    )
  ).settings(Format.settings : _*).dependsOn(sentinel)
}

object Format {

  import com.typesafe.sbt.SbtScalariform._

  lazy val settings = scalariformSettings ++ Seq(
    ScalariformKeys.preferences := formattingPreferences
  )

  lazy val formattingPreferences = {
    import scalariform.formatter.preferences._
    FormattingPreferences().
      setPreference(AlignParameters, true).
      setPreference(AlignSingleLineCaseStatements, true).
      setPreference(CompactControlReadability, true).
      setPreference(CompactStringConcatenation, true).
      setPreference(DoubleIndentClassDeclaration, true).
      setPreference(IndentLocalDefs, true).
      setPreference(IndentPackageBlocks, true).
      setPreference(IndentSpaces, 2).
      setPreference(MultilineScaladocCommentsStartOnFirstLine, true).
      setPreference(PreserveSpaceBeforeArguments, false).
      setPreference(PreserveDanglingCloseParenthesis, false).
      setPreference(RewriteArrowSymbols, true).
      setPreference(SpaceBeforeColon, false).
      setPreference(SpaceInsideBrackets, false).
      setPreference(SpacesWithinPatternBinders, true)
  }
}
