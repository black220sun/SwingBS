package com.blacksun

import com.blacksun.gui.LCalendar
import com.blacksun.gui.LFrame
import com.blacksun.settings.Settings
import com.blacksun.settings.SettingsConfiguration

fun main(args: Array<String>) {
    Settings.configure(SettingsConfiguration("SwingBS"))
    LFrame("test", LCalendar(save = true, load = true)).pack()
}