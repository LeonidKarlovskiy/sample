import io.vertx.kotlin.coroutines.CoroutineVerticle
import org.slf4j.LoggerFactory

class ServerVerticle : CoroutineVerticle() {
    private val logger = LoggerFactory.getLogger(ServerVerticle::class.java)

    override suspend fun start() {
        val config = ConfigLoader(vertx).loadConfig()
        val dbService = DatabaseService(config.getJsonObject("database"))
        logger.info("Storage configuration loaded: ${config.getJsonObject("database").getString("type")}")

        val router = RouterConfigurator(vertx, dbService).configure()

        vertx.createHttpServer().requestHandler(router).listen(8080) {
            if (it.succeeded()) {
                logger.info("Server is now listening on port 8080")
            } else {
                logger.error("Failed to bind to port 8080. Error: ${it.cause().message}")
            }
        }
    }
}