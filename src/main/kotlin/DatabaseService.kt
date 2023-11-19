import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import org.jooq.impl.DSL.field
import org.jooq.impl.DSL.table
import org.postgresql.ds.PGSimpleDataSource
import java.util.*
import javax.sql.DataSource

class DatabaseService(dbConfig: JsonObject) {
    private val dslContext: DSLContext

    init {
        val dataSource = createDataSource(dbConfig)
        dslContext = DSL.using(dataSource, SQLDialect.POSTGRES)
    }


    private fun createDataSource(dbConfig: JsonObject): DataSource {
        val dataSource = PGSimpleDataSource()
        dataSource.setURL(dbConfig.getString("url"))
        dataSource.user = dbConfig.getString("user")
        dataSource.password = dbConfig.getString("password")
        return dataSource
    }

    fun insertClient(name: String, surname: String) {
        val id = UUID.randomUUID().toString()
        dslContext.transaction { configuration ->
            val ctx = DSL.using(configuration)
            ctx.insertInto(table("client"),field("id"), field("name"), field("surname"))
                .values(id,name, surname)
                .execute()
        }
    }

    fun getAllClients() : JsonArray {
        val result = dslContext
            .selectFrom(table("client"))
            .fetch()

        val clients = JsonArray()
        result.forEach { record ->
            val client = JsonObject()
                .put("id", record.getValue(field("id", String::class.java)))
                .put("name", record.getValue(field("name", String::class.java)))
                .put("surname", record.getValue(field("surname", String::class.java)))
            clients.add(client)
        }

        return clients
    }

}