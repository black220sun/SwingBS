package org.blacksun.settings

import org.blacksun.gui.LWrapper
import org.blacksun.settings.handler.*
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
        if (!saveFile.exists())
            return
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

    fun <T>getWrapped(id: String): T {
        val component = components[id] as? LWrapper<*> ?: throw IllegalArgumentException("Component is not LWrapper")
        @Suppress("UNCHECKED_CAST")
        return component.value as? T ?: throw IllegalArgumentException("Value class mismatch")
    }

    internal operator fun get(name: String) = properties[name]
    internal operator fun set(name: String, value: String): String {
        properties[name] = value
        return value
    }
}