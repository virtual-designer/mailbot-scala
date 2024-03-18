package org.onesoftnet.mailbot.core

import org.onesoftnet.mailbot.utils.HasClient

abstract class Service extends HasClient {
    def boot(): Unit = {}
}
