package org.onesoftnet.mailbot.logging

import java.io.PrintStream

enum LogLevel(val printStream: PrintStream = System.out) {
    case Debug
    case Info
    case Warn extends LogLevel(System.err)
    case Error extends LogLevel(System.err)
    case Fatal extends LogLevel(System.err)
    
    private val lowercaseName = toString.toLowerCase;
    def name: String = lowercaseName
}