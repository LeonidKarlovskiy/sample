import com.sun.tools.javac.Main
import io.vertx.core.Vertx
import org.slf4j.LoggerFactory


fun main() {
    val vertx = Vertx.vertx()
     val logger = LoggerFactory.getLogger(Main::class.java)
    vertx.deployVerticle(ServerVerticle()) {
        if (it.succeeded()) {
           logger.info("ServerVerticle deployed successfully.")
        } else {
            logger.error("Failed to deploy ServerVerticle: ${it.cause().message}")
        }
    }
}




