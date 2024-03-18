package org.onesoftnet.mailbot.core

import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

class LegacyContext(val message: Message, val argv: Seq[String]) extends CommandContext {
    override def reply(builder: MessageCreateBuilder, fetch: Boolean): Future[Message] = Future {
        val result = message.reply(builder.build()).submit().get()
        if (fetch) result else null
    }
}
