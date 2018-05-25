package com.blacksun.settings.handler

import com.blacksun.settings.Settings

class BooleanPropertyHandler: PropertyHandler<Boolean> {
    override fun set(name: String, value: Boolean): Boolean {
        Settings[name] = value.toString()
        return value
    }
    override fun getOrNull(name: String) = Settings[name]?.toBoolean()
}