package com.blacksun.settings.handler

interface PropertyHandler<T> {
    operator fun set(name: String, value: T): T
    operator fun get(name: String): T = getOrNull(name)!!
    fun getOrDefault(name: String, default: ()->T) = getOrNull(name) ?: default()
    fun getOrDefault(name: String, default: T) = getOrDefault(name, {default})
    fun getOrPut(name: String, value: ()->T) = getOrNull(name) ?: set(name, value())
    fun getOrPut(name: String, value: T) = getOrPut(name, {value})
    fun getOrNull(name: String): T?
}