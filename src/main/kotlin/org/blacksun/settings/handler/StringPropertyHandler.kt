package org.blacksun.settings.handler

import org.blacksun.settings.Settings

internal class StringPropertyHandler: PropertyHandler<String> {
    override fun set(name: String, value: String) = Settings.set(name, value)
    override fun getOrNull(name: String) = Settings[name]
}