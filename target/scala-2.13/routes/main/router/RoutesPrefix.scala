// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/24956/Documents/GitHub/ITSD-Group-42/conf/routes
// @DATE:Mon Jan 30 21:29:22 GMT 2023


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
