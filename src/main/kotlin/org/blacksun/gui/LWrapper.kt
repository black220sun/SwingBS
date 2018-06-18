package org.blacksun.gui

import org.blacksun.settings.Settings
import javax.swing.JComponent

class LWrapper<T>(val value: T, val id: String): JComponent() {
    init {
        Settings.register(id, this)
    }
}