package com.blacksun.gui

import com.blacksun.settings.Settings
import javax.swing.JComboBox

class LComboBox<T>(name: String, values: Array<T>, index: Int = 0, val id: String = "__cmb__$name"):
        JComboBox<T>(values) {
    init {
        Settings.register(id, this)
        val realIndex = if (index >= values.size) 0 else index
        selectedIndex = Settings.int.getOrPut(id, realIndex)
        addActionListener {
            Settings.int[id] = selectedIndex
        }
    }
}