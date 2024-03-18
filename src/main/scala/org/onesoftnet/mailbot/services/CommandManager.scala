package org.onesoftnet.mailbot.services

import org.onesoftnet.mailbot.core.Service
import org.onesoftnet.mailbot.core.Command

import scala.collection.mutable

class CommandManager extends Service {
    private val commands = mutable.Map[String, Command]()

    override def boot(): Unit = {
        client.dynamicLoader.loadCommands()
    }
    
    def loadCommand(command: Command): Unit = {
        commands(command.name) = command
        
        for (alias <- command.aliases) {
            commands(alias) = command
        }
    }
    
    def getCommand(name: String): Option[Command] = commands.get(name)
}
