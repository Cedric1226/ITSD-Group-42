// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/24956/Desktop/CardGame/conf/routes
// @DATE:Thu Jan 26 15:14:14 GMT 2023


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
