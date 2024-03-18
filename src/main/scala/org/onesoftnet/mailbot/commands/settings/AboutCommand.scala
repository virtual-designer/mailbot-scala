package org.onesoftnet.mailbot.commands.settings

import net.dv8tion.jda.api.entities.Message
import org.onesoftnet.mailbot.core.{Command, CommandContext}

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration.Duration

class AboutCommand extends Command {
    override val name = "about"
    override val group = "settings"

    override def execute(context: CommandContext): Future[Unit] = Future {
        Await.ready(context.success("Hello world"), Duration.Inf)
    }
}
