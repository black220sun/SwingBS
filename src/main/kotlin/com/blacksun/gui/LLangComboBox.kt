package com.blacksun.gui

import com.blacksun.settings.Settings
import com.blacksun.settings.handler.LanguagePropertyHandler
import javax.swing.JComboBox

class LLangComboBox(name: String = "lang", val id: String = "__lcmb__$name"):
        JComboBox<String>(Settings.lang.getLanguages().toTypedArray()) {
    init {
        Settings.register(id, this)
        selectedItem = Settings.lang.getActiveLang()
        addActionListener {
            Settings.lang.setActiveLang(selectedItem as String)
        }
    }
}