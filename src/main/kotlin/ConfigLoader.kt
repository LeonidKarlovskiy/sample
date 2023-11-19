import io.vertx.config.ConfigRetriever
import io.vertx.config.ConfigRetrieverOptions
import io.vertx.config.ConfigStoreOptions
import io.vertx.core.Vertx
import io.vertx.core.json.JsonObject
import io.vertx.kotlin.coroutines.awaitResult


class ConfigLoader(private val vertx: Vertx) {
    suspend fun loadConfig(): JsonObject {
        val configStoreOptions = ConfigStoreOptions()
            .setType("file")
            .setFormat("hocon")
            .setConfig(JsonObject().put("path", "default.conf"))

        val configRetrieverOptions = ConfigRetrieverOptions().addStore(configStoreOptions)
        val configRetriever = ConfigRetriever.create(vertx, configRetrieverOptions)
        return awaitResult { handler ->
            configRetriever.getConfig(handler)

        }
    }


}