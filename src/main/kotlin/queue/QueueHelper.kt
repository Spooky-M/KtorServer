package queue

import com.rabbitmq.client.*
import util.Constants

object QueueHelper {

    fun exchangeDeclare(channel: Channel): AMQP.Exchange.DeclareOk? {
        return channel.exchangeDeclare(Constants.EXCHANGE_NAME, BuiltinExchangeType.FANOUT)
    }

    fun createConnection(): Connection? {
        val factory = ConnectionFactory()
        return factory.newConnection(Constants.CONNECTION)
    }
}