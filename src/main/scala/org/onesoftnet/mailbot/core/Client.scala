package org.onesoftnet.mailbot.core

import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent
import org.onesoftnet.mailbot.logging.Logger

import scala.collection.mutable

class Client {
    private val logger = Logger.getFor(this)
    private val services = mutable.Map[Class[? <: Service], Service]()
    val builder: JDABuilder = JDABuilder.createDefault(Env.get("TOKEN"))
    val dynamicLoader: DynamicLoader = DynamicLoader(this, this.getClass.getPackageName.replace(".core", ""))

    setIntents()
    dynamicLoader.loadServices()
    dynamicLoader.loadEvents()

    def service[T <: Service](clazz: Class[T]): T = services(clazz).asInstanceOf[T]

    def addService(service: Service): Unit = services(service.getClass) = service

    private def setIntents(): Unit = {
        builder.enableIntents(
            GatewayIntent.GUILD_MESSAGES,
            GatewayIntent.GUILD_MESSAGE_TYPING,
            GatewayIntent.MESSAGE_CONTENT,
            GatewayIntent.DIRECT_MESSAGES,
            GatewayIntent.GUILD_MEMBERS,
        )
    }
    
    def run(): Unit = {
        logger.info("Attempting to connect to Discord")
        builder.build()
    }
}
