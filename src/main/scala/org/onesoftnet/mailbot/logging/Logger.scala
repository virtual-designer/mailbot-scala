package org.onesoftnet.mailbot.logging

import org.onesoftnet.mailbot.logging.LogLevel.{Debug, Fatal, Info, Warn, Error}
import scala.collection.mutable

class Logger(val name: String) {
    private def log(logLevel: LogLevel, args: Any*): Unit = {
        logLevel.printStream.print(s"[system:${logLevel.name}] [$name] ")

        args.foreach { it =>
            logLevel.printStream.print(it)
            logLevel.printStream.print(' ')
        }

        logLevel.printStream.print('\n')
    }

    def info(args: Any*): Unit = {
        log(Info, args*)
    }

    def debug(args: Any*): Unit = {
        log(Debug, args*)
    }

    def warn(args: Any*): Unit = {
        log(Warn, args*)
    }

    def error(args: Any*): Unit = {
        log(Error, args*)
    }

    def fatal(args: Any*): Unit = {
        log(Fatal, args*)
    }
}

object Logger {
    private val loggers = mutable.Map[Class[?], Logger]()

    def getFor(clazz: Class[?]): Logger = {
        if (!loggers.contains(clazz)) {
            loggers(clazz) = Logger(clazz.getName)
        }

        loggers(clazz)
    }

    def getFor(obj: Any): Logger = getFor(obj.getClass)
}