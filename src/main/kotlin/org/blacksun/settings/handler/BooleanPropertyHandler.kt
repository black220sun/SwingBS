package org.blacksun.settings.handler

import org.blacksun.settings.Settings

internal class BooleanPropertyHandler: PropertyHandler<Boolean> {
    override fun set(name: String, value: Boolean): Boolean {
        Settings["__bool__$name"] = value.toString()
        return value
    }
    override fun getOrNull(name: String) = Settings["__bool__$name"]?.toBoolean()
}