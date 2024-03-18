package org.onesoftnet.mailbot.core

import net.dv8tion.jda.api.hooks.ListenerAdapter

abstract class EventListener extends ListenerAdapter {
    protected var client: Client = null
    def setClient(client: Client): Unit = this.client = client
}
