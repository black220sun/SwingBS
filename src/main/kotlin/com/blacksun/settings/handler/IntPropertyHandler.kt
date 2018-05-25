package com.blacksun.settings.handler

import com.blacksun.settings.Settings

class IntPropertyHandler: PropertyHandler<Int> {
    override fun set(name: String, value: Int): Int {
        Settings[name] = value.toString()
        return value
    }
    override fun getOrNull(name: String) = Settings[name]?.toIntOrNull()
}