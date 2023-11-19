import io.vertx.ext.web.RoutingContext

class ClientHandler(private val dbService: DatabaseService) {


    fun handleNewClient(context: RoutingContext) {
        val requestBody = context.body().asJsonObject()
        val name = requestBody.getString("name")
        val surname = requestBody.getString("surname")
        dbService.insertClient(name, surname)

        context.response()
            .putHeader("content-type", "text/plain")
            .setStatusCode(201)
            .end("Received client with name: $name and surname: $surname")
    }

    fun handleAllClients(context: RoutingContext) {
      val clients =dbService.getAllClients()
        context.response()
            .putHeader("content-type", "application/json")
            .end(clients.encode())
    }
}