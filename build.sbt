import org.scalajs.linker.interface.ESVersion

import org.scalajs.linker.interface.ModuleSplitStyle

lazy val frontend = project
  .in(file("modules/frontend"))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    scalaVersion                    := "2.13.11",
    Compile / fastLinkJS / scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.ESModule) },
    Compile / fastLinkJS / scalaJSLinkerConfig ~= (_.withModuleSplitStyle(ModuleSplitStyle.FewestModules)),
    scalaJSLinkerConfig ~= { _.withESFeatures(_.withESVersion(ESVersion.ES5_1)) },
    scalaJSLinkerConfig ~= { _.withSourceMap(false) },
    scalaJSUseMainModuleInitializer := true,
    libraryDependencies ++= Seq(
      "com.raquo" %%% "laminar" % "16.0.0"
    )
  )

lazy val root = project
  .in(file("."))
  .aggregate(frontend)
