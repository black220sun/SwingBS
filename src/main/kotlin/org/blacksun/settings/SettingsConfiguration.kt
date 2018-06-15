package org.blacksun.settings

class SettingsConfiguration(
        val projectName: String,
        val workingDirPath: String = System.getProperty("user.home")
)
