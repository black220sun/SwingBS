package com.blacksun.gui

import com.blacksun.settings.Settings
import java.awt.Color
import java.awt.GridLayout
import java.util.Calendar
import javax.swing.*

class LCalendar(name: String = "calendar", private val save: Boolean = false, load: Boolean = false,
                val id: String = "__cld__$name"): JPanel() {
    val calendar = Calendar.getInstance()!!
    private val vgap = Settings.int.getOrPut("$id#vgap", 8)
    private val hgap = Settings.int.getOrPut("$id#hgap", 8)
    private val panel = JPanel()
    private var day = DayButton(0, this)
    private val months = arrayListOf(
            "January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December"
    ).map { Settings.lang[it] }
    private val days = arrayListOf(
            "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
    ).map { Settings.lang[it] }

    init {
        Settings.register(id, this)
        if (load) {
            try {
                calendar.set(Calendar.YEAR, Settings.int["$id#year"])
                calendar.set(Calendar.MONTH, Settings.int["$id#month"])
                calendar.set(Calendar.DAY_OF_MONTH, Settings.int["$id#day"])
            } catch (ignored: NullPointerException) {
                calendar.timeInMillis = System.currentTimeMillis()
            }
        } else
            calendar.timeInMillis = System.currentTimeMillis()
        add(panel)
        panel.layout = BoxLayout(panel, BoxLayout.Y_AXIS)
        updatePanel()
    }

    private fun updatePanel() {
        panel.removeAll()
        panel.add(genToolbar())
        panel.add(genDayBar())
        panel.add(genView())
        panel.revalidate()
        if (save) {
            Settings.int["$id#year"] = calendar.get(Calendar.YEAR)
            Settings.int["$id#month"] = calendar.get(Calendar.MONTH)
        }
    }

    private fun genDayBar(): JPanel {
        val panel = JPanel()
        with (panel) {
            layout = GridLayout(1, 7, hgap, 0)
            val offset = calendar.firstDayOfWeek - 1
            for (i in 0..6)
                add(JLabel(days[(i + offset) % 7]))
        }
        return panel
    }

    private fun genToolbar(): JPanel {
        val panel = JPanel()
        panel.layout = GridLayout(1, 7, hgap, 0)
        val month = JLabel(months[calendar.get(Calendar.MONTH)])
        val year = JLabel(calendar.get(Calendar.YEAR).toString())

        panel.add(LButton("Today", {
            calendar.timeInMillis = System.currentTimeMillis()
            updatePanel()
        }, id=""))
        panel.add(LButton("<", {
            calendar.add(Calendar.MONTH, -1)
            updatePanel()
        }, id=""))
        panel.add(month)
        panel.add(LButton(">", {
            calendar.add(Calendar.MONTH, 1)
            updatePanel()
        }, id=""))
        panel.add(LButton("<", {
            calendar.add(Calendar.YEAR, -1)
            updatePanel()
        }, id=""))
        panel.add(year)
        panel.add(LButton(">", {
            calendar.add(Calendar.YEAR, 1)
            updatePanel()
        }, id=""))
        return panel
    }

    private fun genView(): JPanel {
        val panel = JPanel()
        val weeks = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH)
        val days = arrayListOf<JComponent>()
        panel.layout = GridLayout(weeks, 7, hgap, vgap)
        val old = calendar.get(Calendar.DAY_OF_MONTH)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        for (i in 3..calendar.get(Calendar.DAY_OF_WEEK))
            days += LEmptyLabel()
        calendar.set(Calendar.DAY_OF_MONTH, old)
        for (i in 1..calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
            days += DayButton(i, this)
        for (i in 0..6)
            days += LEmptyLabel()
        for (i in 0 until weeks)
            for (j in 0..6)
                panel.add(days[i * 7 + j])
        return panel
    }

    class DayButton(i: Int, parent: LCalendar): JButton(i.toString()) {
        init {
            if (i == parent.calendar.get(Calendar.DAY_OF_MONTH)) {
                background = Color.LIGHT_GRAY
                parent.day = this
            }
            addActionListener {
                with(parent) {
                    day.background = background
                    if (save)
                        Settings.int["$id#day"] = i
                    calendar.set(Calendar.DAY_OF_MONTH, i)
                }
                parent.day = this
                background = Color.LIGHT_GRAY
            }
        }
    }
}