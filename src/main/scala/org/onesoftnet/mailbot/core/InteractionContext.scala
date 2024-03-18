package org.onesoftnet.mailbot.core
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.interactions.InteractionHook
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction
import net.dv8tion.jda.api.utils.messages.{MessageCreateBuilder, MessageEditData}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.boundary

import scala.concurrent.Future

class InteractionContext(val interaction: SlashCommandInteraction) extends CommandContext {
    override def reply(builder: MessageCreateBuilder, fetch: Boolean): Future[Message] = Future {
        if (interaction.isAcknowledged) {
            val hook = interaction.getHook
            var message: Message = null

            boundary {
                if (hook.isExpired) {
                    boundary.break()
                }

                message = hook.editOriginal(MessageEditData.fromCreateData(builder.build())).complete()
            }

            message
        }
        else {
            val hook = interaction.reply(builder.build()).submit().get()
            if (fetch) hook.retrieveOriginal().complete() else null
        }
    }

    def replyHook(builder: MessageCreateBuilder): Future[InteractionHook] = Future {
        interaction.reply(builder.build()).submit().get()
    }
}
