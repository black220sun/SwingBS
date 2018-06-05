package com.blacksun.gui

import com.blacksun.settings.Settings
import java.awt.event.WindowEvent
import java.awt.event.WindowListener
import javax.swing.JComponent
import javax.swing.JFrame
import javax.swing.JOptionPane
import javax.swing.WindowConstants

class LFrame(name: String, panel: JComponent? = null, val id: String = "__frm__$name"):
        JFrame(Settings.lang[name]), WindowListener {
    override fun windowDeiconified(p0: WindowEvent?) = Unit
    override fun windowActivated(p0: WindowEvent?) = Unit
    override fun windowDeactivated(p0: WindowEvent?) = Unit
    override fun windowOpened(p0: WindowEvent?) = Unit
    override fun windowIconified(p0: WindowEvent?) = Unit
    override fun windowClosed(p0: WindowEvent?) = Unit
    override fun windowClosing(p0: WindowEvent?) {
        if (!Settings.boolean["forceQuit"]) {
            val text = Settings.lang["Quit?"]
            val title = Settings.lang["Quit"]
            val result = JOptionPane.showConfirmDialog(this, text, title, JOptionPane.YES_NO_OPTION)
            if (result != JOptionPane.YES_OPTION)
                return
        }
        Settings.save()
        dispose()
    }

    init {
        Settings.register(id, this)
        if (panel != null)
            contentPane = panel
        isVisible = true
        defaultCloseOperation = WindowConstants.DO_NOTHING_ON_CLOSE
        addWindowListener(this)
    }

    operator fun plusAssign(component: JComponent) {
        contentPane.add(component)
    }
    operator fun plus(component: JComponent): JFrame {
        contentPane.add(component)
        return this
    }
}