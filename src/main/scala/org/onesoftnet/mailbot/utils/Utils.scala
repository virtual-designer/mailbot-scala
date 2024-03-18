package org.onesoftnet.mailbot.utils

def firstNonNull[T](args: T*): Option[T] = {
    var value = Option.empty[T]

    for (arg <- args) {
        if (arg != null) {
            value = Option.apply(arg)
        }
    }

    value
}