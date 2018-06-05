package com.blacksun.gui

import com.blacksun.settings.Settings
import javax.swing.JLabel

class LLabel(name: String, val id: String = "__lbl__$name"): JLabel(Settings.lang[name]) {
    init {
        Settings.register(id, this)
    }
}