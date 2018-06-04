package com.blacksun.gui

import com.blacksun.settings.Settings
import com.blacksun.settings.handler.LanguagePropertyHandler
import javax.swing.JComboBox

class LLangComboBox(name: String = "lang", id: String = "__scmb__$name"):
        JComboBox<String>((Settings.lang as LanguagePropertyHandler).getLanguages().toTypedArray()) {
    init {
        Settings.register(id, this)
        selectedItem = Settings.getActiveLang()
        addActionListener {
            Settings.setActiveLang(selectedItem as String)
        }
    }
}