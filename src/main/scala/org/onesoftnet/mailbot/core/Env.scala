package org.onesoftnet.mailbot.core

import io.github.cdimascio.dotenv.Dotenv

object Env {
    private val dotenv = Dotenv.configure().load()

    def get(name: String): String = {
        val dotenvValue = dotenv.get(name)
        
        if (dotenvValue != null) {
            return dotenvValue
        }
        
        System.getenv(name)
    }
}
