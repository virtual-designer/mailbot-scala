package org.onesoftnet.mailbot

import org.onesoftnet.mailbot.core.Client
import org.onesoftnet.mailbot.logging.Logger

import scala.io.StdIn.readChar
import System.exit

object Main {
    private val logger = Logger.getFor(this)
    
    def main(args: Array[String]): Unit = {
        logger.info("Starting up")
        
        val client = Client()
        client.run()
        loop()
    }

    private def loop(): Unit = {
        while (true) {
            val key = readChar()

            if (key == 'q') {
                println("Quitting...")
                exit(0)
            }
        }
    }
}