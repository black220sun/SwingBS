package com.blacksun.gui

import com.blacksun.settings.Settings
import java.awt.Dimension
import java.awt.event.ActionEvent
import java.nio.file.Paths
import javax.swing.ImageIcon
import javax.swing.JButton

class LIconButton(path: String, action: ((ActionEvent) -> Unit)? = null,
                  name: String? = null, id: String? = null): JButton() {
    init {
        val file = Paths.get(Settings.resourceDir, path).toFile()
        val realName = name ?: file.nameWithoutExtension
                .map { if (it in 'A'..'Z') " ${it.toLowerCase()}" else "$it"}
                .joinToString("")
                .capitalize()
        val realId = id ?: "__ibtn__$realName"
        Settings.register(realId, this)
        if (action != null)
            addActionListener(action)
        if (file.exists()) {
            icon = ImageIcon(file.absolutePath)
            preferredSize = Dimension(icon.iconWidth, icon.iconHeight)
            maximumSize = preferredSize
            minimumSize = preferredSize
        } else
            text = Settings.lang[realName]
    }
}