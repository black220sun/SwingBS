package org.blacksun.gui

import org.blacksun.settings.Settings
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