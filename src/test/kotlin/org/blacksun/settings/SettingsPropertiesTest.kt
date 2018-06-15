package org.blacksun.settings

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class SettingsPropertiesTest {
    companion object {
        @Before
        fun cleanup() = Settings.clear()
    }

    @Test
    fun testString() {
        val expected1 = "test1"
        val expected2 = "test2"
        Settings.string["a"] = expected1

        assertEquals(expected1, Settings.string["a"])
        assertEquals(expected1, Settings.string.getOrNull("a"))
        assertEquals(expected1, Settings.string.getOrPut("a", expected2))
        assertEquals(expected1, Settings.string.getOrPut("a", {expected2}))
        assertEquals(expected1, Settings.string.getOrDefault("a", expected2))
        assertEquals(expected1, Settings.string.getOrDefault("a", {expected2}))

        assertEquals(expected2, Settings.string.getOrPut("b", expected2))
        assertEquals(expected2, Settings.string.getOrPut("c", {expected2}))
        assertEquals(expected2, Settings.string.getOrDefault("d", expected2))
        assertEquals(expected2, Settings.string.getOrDefault("d", {expected2}))
        assertEquals(null, Settings.string.getOrNull("d"))
    }

    @Test
    fun testInt() {
        val expected1 = 1
        val expected2 = 2
        Settings.int["a1"] = expected1

        assertEquals(expected1, Settings.int["a1"])
        assertEquals(expected1, Settings.int.getOrNull("a1"))
        assertEquals(expected1, Settings.int.getOrPut("a1", expected2))
        assertEquals(expected1, Settings.int.getOrPut("a1", {expected2}))
        assertEquals(expected1, Settings.int.getOrDefault("a1", expected2))
        assertEquals(expected1, Settings.int.getOrDefault("a1", {expected2}))

        assertEquals(expected2, Settings.int.getOrPut("b1", expected2))
        assertEquals(expected2, Settings.int.getOrPut("c1", {expected2}))
        assertEquals(expected2, Settings.int.getOrDefault("d1", expected2))
        assertEquals(expected2, Settings.int.getOrDefault("d1", {expected2}))
        assertEquals(null, Settings.int.getOrNull("d1"))
    }

    @Test
    fun testBoolean() {
        val expected1 = true
        val expected2 = false
        Settings.boolean["a2"] = expected1

        assertEquals(expected1, Settings.boolean["a2"])
        assertEquals(expected1, Settings.boolean.getOrNull("a2"))
        assertEquals(expected1, Settings.boolean.getOrPut("a2", expected2))
        assertEquals(expected1, Settings.boolean.getOrPut("a2", {expected2}))
        assertEquals(expected1, Settings.boolean.getOrDefault("a2", expected2))
        assertEquals(expected1, Settings.boolean.getOrDefault("a2", {expected2}))

        assertEquals(expected2, Settings.boolean.getOrPut("b2", expected2))
        assertEquals(expected2, Settings.boolean.getOrPut("c2", {expected2}))
        assertEquals(expected2, Settings.boolean.getOrDefault("d2", expected2))
        assertEquals(expected2, Settings.boolean.getOrDefault("d2", {expected2}))
        assertEquals(null, Settings.boolean.getOrNull("d2"))
    }
}
