package com.blacksun.settings.handler

import com.blacksun.settings.Settings
import java.io.FileReader
import java.io.FileWriter
import java.nio.file.Paths

class LanguagePropertyHandler: PropertyHandler<String> {
    private val lang = LinkedHashMap<String, String>()
    private val langDir = Paths.get(Settings.projectPath.toString(), "lang").toFile()
    private val activeLangFile = Paths.get(langDir.absolutePath, getActiveLang()).toFile()

    init {
        langDir.mkdirs()
        if (activeLangFile.exists())
            FileReader(activeLangFile).forEachLine {
                val parts = it.split(',')
                lang[parts[0]] = parts[1]
            }
    }

    fun getActiveLang() = Settings["__activeLang__"] ?: Settings.set("__activeLang__", "English")
    fun setActiveLang(name: String) = Settings.set("__activeLang__", name)

    fun getLanguages() = langDir.listFiles().map { it.name }

    fun changeLang(name: String) {
        if (name in getLanguages())
            setActiveLang(name)
    }

    fun save() {
        FileWriter(activeLangFile).use {
            lang.forEach { key, value -> it.appendln("$key,$value") }
        }
    }

    override fun set(name: String, value: String): String {
        lang["__lang__$name"] = value
        return value
    }
    override fun getOrNull(name: String) = lang["__lang__$name"] ?: set(name, name)
}
