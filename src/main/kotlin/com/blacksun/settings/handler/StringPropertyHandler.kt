package com.blacksun.settings.handler

import com.blacksun.settings.Settings

internal class StringPropertyHandler: PropertyHandler<String> {
    override fun set(name: String, value: String) = Settings.set(name, value)
    override fun getOrNull(name: String) = Settings[name]
}