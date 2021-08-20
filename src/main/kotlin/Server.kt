import com.fasterxml.jackson.databind.SerializationFeature
import dao.getPaymentById
import dao.setPaymentById
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.jackson.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import model.CheckPayment
import utils.getServerHost
import utils.getServerPort
import utils.loadProps

fun Application.module() {
    install(DefaultHeaders)
    install(CallLogging)
    install(ContentNegotiation) {
        jackson {
            configure(SerializationFeature.INDENT_OUTPUT, true)
        }
    }
}

fun main() {
    loadProps()
    embeddedServer(Netty, port = getServerPort(), host = getServerHost()) {
        module()
        routing {
            post("/api/check") {
                val request = call.receive<CheckPayment>()
                val check = getPaymentById(request.id)
                if (check?.id.isNullOrEmpty()) {
                    setPaymentById(request)
                    call.respond(HttpStatusCode(200, "OK"), "tx save to db")
                } else {
                    call.respond(HttpStatusCode(500, "Server Error"), "ID already exists")
                }
            }
            get("/utils/reload/config") {
                loadProps()
                call.application.log.info("config reload")
            }
        }
    }.start(wait = true)
}