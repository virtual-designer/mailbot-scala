package org.onesoftnet.mailbot.events

import net.dv8tion.jda.api.events.session.ReadyEvent
import org.onesoftnet.mailbot.core.EventListener
import org.onesoftnet.mailbot.logging.Logger

class ReadyEventListener extends EventListener {
    private val logger = Logger.getFor(this)
    
    override def onReady(event: ReadyEvent): Unit = {
        logger.info(s"Logged in as ${event.getJDA.getSelfUser.getName}")
    }
}
