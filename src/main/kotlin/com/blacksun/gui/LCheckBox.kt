package com.blacksun.gui

import com.blacksun.settings.Settings
import java.awt.event.ActionEvent
import javax.swing.JCheckBox

class LCheckBox(name: String, action: ((ActionEvent)->Unit)? = null, state: Boolean = false,
                id: String = "__chb__$name"): JCheckBox(Settings.lang[name], state) {
    init {
        Settings.register(id, this)
        if (action != null)
            addActionListener(action)
    }
}