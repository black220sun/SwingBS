package com.blacksun.settings

import com.blacksun.settings.handler.*
import java.io.FileReader
import java.io.FileWriter
import java.nio.file.Path
import java.nio.file.Paths

object Settings {
    private val properties = LinkedHashMap<String, String>()
    private lateinit var lazyProjectPath: Path
    private val saveFile by lazy { Paths.get(projectPath.toString(), "settings").toFile() }
    val projectPath by lazy { lazyProjectPath.toFile().mkdirs(); lazyProjectPath }
    val string = StringPropertyHandler()
    val int = IntPropertyHandler()
    val boolean = BooleanPropertyHandler()

    fun configure(config: SettingsConfiguration) {
        lazyProjectPath = Paths.get(config.workingDirPath, config.projectName)
    }

    fun clear() = properties.clear()

    fun load() {
        FileReader(saveFile).forEachLine {
            val parts = it.split(',')
            properties[parts[0]] = parts[1]
        }
    }

    fun save() {
        FileWriter(saveFile).use {
            properties.forEach { key, value -> it.appendln("$key,$value") }
        }
    }

    internal operator fun get(name: String) = properties[name]
    internal operator fun set(name: String, value: String): String {
        properties[name] = value
        return value
    }
}