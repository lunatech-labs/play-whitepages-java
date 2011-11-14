import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "whitepages"
    val appVersion      = "0.1"

    val appDependencies = Nil

  val main = PlayProject(appName, appVersion, appDependencies).settings(defaultJavaSettings:_*).settings(
  )

}
