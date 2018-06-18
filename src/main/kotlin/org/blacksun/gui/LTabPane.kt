package org.blacksun.gui

import org.blacksun.settings.Settings
import java.awt.Component
import javax.swing.JTabbedPane

class LTabbedPane(val id: String = ""): JTabbedPane() {
    private val saved = Settings.int.getOrDefault("$id#index", 0)
    init {
        Settings.register(id, this)
        if (id.isNotBlank())
            addChangeListener {
                Settings.int["$id#index"] = selectedIndex
            }
    }

    override fun addTab(name: String, component: Component) {
        super.addTab(Settings.lang[name], component)
    }

    fun restoreSelected(index: Int = 0) {
        if (tabCount == 0)
            return
        selectedIndex =
                if (saved >= tabCount)
                    index
                else
                    saved
        Settings.int["$id#index"] = selectedIndex
    }
}