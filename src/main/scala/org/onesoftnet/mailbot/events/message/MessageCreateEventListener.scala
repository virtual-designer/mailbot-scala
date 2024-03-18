package org.onesoftnet.mailbot.events.message

import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import org.onesoftnet.mailbot.core.{EventListener, LegacyContext}
import org.onesoftnet.mailbot.logging.Logger
import org.onesoftnet.mailbot.services.CommandManager

class MessageCreateEventListener extends EventListener {
    private val prefix = "="
    private val logger = Logger.getFor(this)
    
    override def onMessageReceived(event: MessageReceivedEvent): Unit = {
        val message = event.getMessage
        
        if (message.getAuthor.isBot || message.getContentRaw.isEmpty || !message.getContentRaw.startsWith(prefix)) {
            return
        }
        
        val argv = message.getContentRaw
            .substring(prefix.length)
            .stripLeading()
            .split(" +")
        
        if (argv.isEmpty) {
            return
        }
        
        val commandName = argv(0)
        val command = client.service(classOf[CommandManager]).getCommand(commandName)
        
        if (command.isEmpty) {
            logger.debug(s"Command not found: $commandName")
            return
        }
        
        val context = LegacyContext(message, argv)
        command.get.execute(context)
    }
}
