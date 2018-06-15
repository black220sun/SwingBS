package org.blacksun.gui

import org.blacksun.settings.Settings
import java.awt.event.ActionEvent
import javax.swing.JRadioButton

class LRadioButton(name: String, action: ((ActionEvent)->Unit)? = null, state: Boolean = false,
                   val id: String = "__rbtn__$name"): JRadioButton(Settings.lang[name], state) {
    init {
        Settings.register(id, this)
        if (action != null)
            addActionListener(action)
    }
}