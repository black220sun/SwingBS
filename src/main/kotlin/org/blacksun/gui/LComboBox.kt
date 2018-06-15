package org.blacksun.gui

import org.blacksun.settings.Settings
import javax.swing.JComboBox

class LComboBox<T>(name: String, values: Array<T>, index: Int = 0, val id: String = "__cmb__$name"):
        JComboBox<T>(values) {
    init {
        Settings.register(id, this)
        val realIndex = if (Settings.int.getOrDefault(id, index) >= values.size) 0 else index
        Settings.int[id] = realIndex
        selectedIndex = realIndex
        addActionListener {
            Settings.int[id] = selectedIndex
        }
    }

    fun setItems(items: Collection<T>) {
        removeAllItems()
        items.forEach(::addItem)
        revalidate()
    }
}