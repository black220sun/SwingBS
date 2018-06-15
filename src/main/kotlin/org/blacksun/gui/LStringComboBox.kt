package org.blacksun.gui

import org.blacksun.settings.Settings
import javax.swing.JComboBox

class LStringComboBox(name: String, values: Collection<String>, index: Int = 0,
                      val id: String = "__scmb__$name"):
        JComboBox<String>(values.map { Settings.lang[it] }.toTypedArray()) {
    init {
        Settings.register(id, this)
        val realIndex = if (index >= values.size) 0 else index
        selectedIndex = Settings.int.getOrPut(id, realIndex)
        addActionListener {
            Settings.int[id] = selectedIndex
        }
    }
}