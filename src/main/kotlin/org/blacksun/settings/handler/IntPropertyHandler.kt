package org.blacksun.settings.handler

import org.blacksun.settings.Settings

internal class IntPropertyHandler: PropertyHandler<Int> {
    override fun set(name: String, value: Int): Int {
        Settings["__int__$name"] = value.toString()
        return value
    }
    override fun getOrNull(name: String) = Settings["__int__$name"]?.toIntOrNull()
}