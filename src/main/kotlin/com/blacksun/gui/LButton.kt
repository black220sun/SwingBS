package com.blacksun.gui

import com.blacksun.settings.Settings
import java.awt.event.ActionEvent
import javax.swing.JButton

class LButton(name: String, action: ((ActionEvent) -> Unit)? = null,
              val id: String = "__btn__$name"): JButton(Settings.lang[name]) {
    init {
        Settings.register(id, this)
        if (action != null)
            addActionListener(action)
    }

    constructor(value: Any, action: ((ActionEvent) -> Unit)? = null,
                id: String = "__btn__$value"): this(value.toString(), action, id)
}