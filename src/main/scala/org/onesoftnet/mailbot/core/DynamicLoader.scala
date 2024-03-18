package org.onesoftnet.mailbot.core

import com.google.common.reflect.ClassPath
import org.onesoftnet.mailbot.logging.Logger
import org.onesoftnet.mailbot.services.CommandManager

import scala.jdk.CollectionConverters.*
import scala.language.reflectiveCalls

class DynamicLoader(val client: Client, val rootPackageName: String) {
    private val logger = Logger.getFor(this)

    private def loadClasses[T](packageName: String): List[Class[T]] = {
        ClassPath.from(ClassLoader.getSystemClassLoader)
            .getAllClasses
            .stream
            .filter(clazz => clazz.getPackageName.startsWith(packageName))
            .map(clazz => clazz.load.asInstanceOf[Class[T]])
            .iterator()
            .asScala
            .toList
    }

    def loadServices(): Unit = {
        logger.info("Loading services")
        val classes = loadClasses[Service](s"$rootPackageName.services")

        for (clazz <- classes) {
            logger.info(s"Loading service: ${clazz.getName}")
            val instance = clazz.getDeclaredConstructor().newInstance()
            instance.setClient(client)
            client.addService(instance)
            instance.boot()
        }
    }

    def loadEvents(): Unit = {
        logger.info("Loading event handlers")
        val classes = loadClasses[EventListener](s"$rootPackageName.events")

        for (clazz <- classes) {
            logger.info(s"Loading event: ${clazz.getName}")
            val instance = clazz.getDeclaredConstructor().newInstance()
            instance.setClient(client)
            client.builder.addEventListeners(instance)
        }
    }

    def loadCommands(): Unit = {
        val classes = loadClasses[Command](s"$rootPackageName.commands")

        for (clazz <- classes) {
            logger.info(s"Loading command: ${clazz.getName}")
            val instance = clazz.getDeclaredConstructor().newInstance()
            instance.setClient(client)
            client.service(classOf[CommandManager]).loadCommand(instance)
        }
    }
}
