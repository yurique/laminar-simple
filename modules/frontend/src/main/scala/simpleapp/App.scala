package simpleapp

import com.raquo.laminar.api.L._
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("stylesheets/index.css", JSImport.Default)
object IndexCss extends js.Object

object App {

  val indexCss: IndexCss.type = IndexCss

  def main(args: Array[String]): Unit = {
    val container = dom.document.getElementById("app-container")
    val app       = div(
      cls("text-rose-100"),
      cls := "text-rose-200",
      className("text-rose-300"),
      cls <-- Val("text-rose-400"),
      cls.toggle("text-rose-500") <-- Val(true),
      cls <-- Val(cls"test-rose-600"),
      span("Hello world!")
    )

    val _ = com.raquo.laminar.api.L.renderOnDomContentLoaded(container, app)
  }

  implicit class CssClassInterpolator(sc: StringContext) {
    def cls(args: Any*): String = StringContext.standardInterpolator(StringContext.processEscapes, args, sc.parts)
  }

}
