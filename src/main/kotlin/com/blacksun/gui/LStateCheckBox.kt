package com.blacksun.gui

import com.blacksun.settings.Settings
import javax.swing.JCheckBox

class LStateCheckBox(name: String, state: Boolean = false, id: String = "__schb__$name"):
        JCheckBox(Settings.lang[name], Settings.boolean.getOrPut(name, state)) {
    init {
        Settings.register(id, this)
        addActionListener {
            Settings.boolean[name] = isSelected
        }
    }
}