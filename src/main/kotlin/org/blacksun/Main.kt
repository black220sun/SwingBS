package org.blacksun

import org.blacksun.gui.LCalendar
import org.blacksun.gui.LFrame
import org.blacksun.settings.Settings
import org.blacksun.settings.SettingsConfiguration

fun main(args: Array<String>) {
    Settings.configure(SettingsConfiguration("SwingBS"))
    LFrame("test", LCalendar(save = true, load = true)).pack()
}