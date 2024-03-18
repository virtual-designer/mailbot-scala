package org.onesoftnet.mailbot.core

import scala.concurrent.Future

abstract class Command {
    private var _client: Client = null
    protected def client: Client = _client
    def setClient(client: Client): Unit = _client = client

    val name: String
    val group: String
    val aliases: Seq[String] = List[String]()

    def execute(context: CommandContext): Future[Unit]
}
