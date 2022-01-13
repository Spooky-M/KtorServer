package server

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import queue.producer.Producer
import util.Constants.PORT

class EmbeddedServer {
    private var id: Int = 0

    fun start() {
        embeddedServer(Netty, port = PORT) {
            routing {
                get("/") {
                    call.respondText("Hello, world!")
                }
                post("poster") {
                    call.respondText("Poster post")
                    // test with thread sleep
//                    try {
//                        val r = Random.nextLong(0, 10)
//                        println("$curId sleeps for $r")
//                        Thread.sleep(r)
//                    } catch (e: InterruptedException) {
//                        println(e.message)
//                    }
                    Producer.produce((id++).toString())
                    println("Sent [$id]")
                }
            }
        }.start(wait = true)
    }
}