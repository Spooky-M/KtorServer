package queue.producer

import com.rabbitmq.client.Channel
import com.rabbitmq.client.Connection
import queue.QueueHelper
import util.Constants.EXCHANGE_NAME
import util.Constants.TAG
import java.nio.charset.StandardCharsets

object Producer {

    private var connection: Connection? = null
    private var channel: Channel? = null

    fun getConnectionInstance(): Connection {
        if (connection == null) {
            connection = QueueHelper.createConnection()
        }
        return connection!!
    }

    fun getChannelInstance(): Channel {
        connection = getConnectionInstance()
        if (channel == null) {
            channel = connection?.createChannel()
        }
        return channel!!
    }

    fun produce(msg: String) {
        // called for possible init
        if (channel == null)
            getChannelInstance()

        channel?.let {
            QueueHelper.exchangeDeclare(it)
            it.basicPublish(EXCHANGE_NAME, TAG, null, msg.toByteArray(StandardCharsets.UTF_8))
            println(" [x] Sent '$msg'")
        }
    }
}
