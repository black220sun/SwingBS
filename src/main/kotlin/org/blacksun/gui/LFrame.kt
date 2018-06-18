package org.blacksun.gui

import org.blacksun.settings.Settings
import java.awt.event.ComponentEvent
import java.awt.event.ComponentListener
import java.awt.event.WindowEvent
import java.awt.event.WindowListener
import javax.swing.JComponent
import javax.swing.JFrame
import javax.swing.JOptionPane
import javax.swing.WindowConstants

class LFrame(name: String, panel: JComponent? = null, val id: String = "__frm__$name"):
        JFrame(Settings.lang[name]), WindowListener, ComponentListener {
    override fun componentHidden(p0: ComponentEvent?) = Unit
    override fun componentShown(p0: ComponentEvent?) = Unit
    override fun windowDeiconified(p0: WindowEvent?) = Unit
    override fun windowActivated(p0: WindowEvent?) = Unit
    override fun windowDeactivated(p0: WindowEvent?) = Unit
    override fun windowOpened(p0: WindowEvent?) = Unit
    override fun windowIconified(p0: WindowEvent?) = Unit
    override fun windowClosed(p0: WindowEvent?) = Unit
    override fun windowClosing(p0: WindowEvent?) {
        if (!Settings.boolean.getOrDefault("forceQuit", false)) {
            val text = Settings.lang["Quit?"]
            val title = Settings.lang["Quit"]
            val result = JOptionPane.showConfirmDialog(this, text, title, JOptionPane.YES_NO_OPTION)
            if (result != JOptionPane.YES_OPTION)
                return
        }
        Settings.save()
        dispose()
    }
    override fun componentMoved(p0: ComponentEvent?) {
        Settings.int["$id#X"] = x
        Settings.int["$id#Y"] = y
    }
    override fun componentResized(p0: ComponentEvent?) {
        Settings.int["$id#height"] = height
        Settings.int["$id#width"] = width
    }

    init {
        Settings.register(id, this)
        if (panel != null)
            contentPane = panel
        isVisible = true
        defaultCloseOperation = WindowConstants.DO_NOTHING_ON_CLOSE
        addWindowListener(this)
        addComponentListener(this)
    }

    fun restoreSize() {
        val height = Settings.int.getOrDefault("$id#height", height)
        val width = Settings.int.getOrDefault("$id#width", width)
        val x = Settings.int.getOrDefault("$id#X", 100)
        val y = Settings.int.getOrDefault("$id#Y", 100)
        setBounds(x, y, width, height)
    }

    operator fun plusAssign(component: JComponent) {
        contentPane.add(component)
    }
    operator fun plus(component: JComponent): LFrame {
        contentPane.add(component)
        return this
    }
}