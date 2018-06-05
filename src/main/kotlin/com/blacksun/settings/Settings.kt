package com.blacksun.settings

import com.blacksun.settings.handler.*
import java.awt.Component
import java.io.FileReader
import java.io.FileWriter
import java.nio.file.Path
import java.nio.file.Paths

object Settings {
    private val components = LinkedHashMap<String, Component>()
    private val properties = LinkedHashMap<String, String>()
    private lateinit var lazyProjectPath: Path
    private val saveFile by lazy { Paths.get(projectPath.toString(), "settings").toFile() }
    internal val projectPath by lazy { lazyProjectPath.toFile().mkdirs(); lazyProjectPath }
    internal val resourceDir by lazy {
        val dir = Paths.get(projectPath.toString(), "resources").toFile()
        dir.mkdirs()
        dir.absolutePath
    }
    val string: PropertyHandler<String> = StringPropertyHandler()
    val int: PropertyHandler<Int> = IntPropertyHandler()
    val boolean: PropertyHandler<Boolean> = BooleanPropertyHandler()
    val lang by lazy { LanguagePropertyHandler() }

    fun configure(config: SettingsConfiguration) {
        lazyProjectPath = Paths.get(config.workingDirPath, ".${config.projectName}")
        load()
    }

    internal fun clear() = properties.clear()

    fun load(clear: Boolean = true) {
        if (clear)
            clear()
        FileReader(saveFile).forEachLine {
            val parts = it.split(',')
            properties[parts[0]] = parts[1]
        }
    }

    fun save() {
        FileWriter(saveFile).use {
            properties.forEach { key, value -> it.appendln("$key,$value") }
        }
        lang.save()
    }

    internal fun register(id: String, component: Component) {
        if (id == "")
            return
        if (id in components.keys)
            throw IllegalArgumentException("ID exists")
        components[id] = component
    }

    fun getComponent(id: String): Component = components[id]!!

    internal operator fun get(name: String) = properties[name]
    internal operator fun set(name: String, value: String): String {
        properties[name] = value
        return value
    }
}