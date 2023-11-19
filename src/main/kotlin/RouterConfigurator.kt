import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler

class RouterConfigurator (private val vertx: Vertx, private val dbService: DatabaseService){


    fun configure(): Router {
        val router = Router.router(vertx)
        val clientHandler = ClientHandler(dbService)

        router.route().handler(BodyHandler.create())
        router.post("/client").handler { context -> clientHandler.handleNewClient(context) }
        router.get("/client").handler{ context-> clientHandler.handleAllClients(context)}

        return router
    }
}