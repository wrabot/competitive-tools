package tools.optimization

import kotlin.test.Test
import kotlin.test.assertEquals

class KnapsackTests {
    private val items = listOf(2 to 5.0, 8 to 3.0, 4 to 2.0, 2 to 7.0, 5 to 4.0)
    private val capacity = 10

    @Test
    fun testValue() {
        assertEquals(16.0, knapsackValue(items, capacity))
    }

    @Test
    fun testContent() {
        assertEquals(listOf(4, 3, 0), knapsackContent(items, capacity))
    }
}