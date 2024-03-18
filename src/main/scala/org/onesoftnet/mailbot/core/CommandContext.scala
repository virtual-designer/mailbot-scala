package org.onesoftnet.mailbot.core

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.emoji.Emoji
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder

import scala.jdk.CollectionConverters.*
import scala.concurrent.Future

trait CommandContext {
    def reply(builder: MessageCreateBuilder): Future[Message] = reply(builder, false)
    def reply(builder: MessageCreateBuilder, fetch: Boolean): Future[Message]

    def replyEmbeds(embeds: EmbedBuilder*): Future[Message] = {
        val builder = MessageCreateBuilder()
            .addEmbeds(embeds.map(embed => embed.build()).asJava)
        reply(builder)
    }

    def error(message: String): Future[Message] = {
        val builder = MessageCreateBuilder()
            .addContent(s"❌ $message")

        reply(builder)
    }

    def success(message: String): Future[Message] = {
        val builder = MessageCreateBuilder()
            .addContent(s"✅ $message")

        reply(builder)
    }
}
