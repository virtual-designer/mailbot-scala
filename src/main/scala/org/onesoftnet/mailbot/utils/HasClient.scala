package org.onesoftnet.mailbot.utils

import org.onesoftnet.mailbot.core.Client

abstract class HasClient {
    private var _client: Client = null
    protected def client = _client
    def setClient(client: Client): Unit = _client = client
}
