package tools

import kotlin.test.Test
import kotlin.test.assertEquals

class CombinationsTests {
    @Test
    fun testCombinationsFull() {
        assertEquals(
            listOf(
                listOf(1, 2, 3),
                listOf(1, 3, 2),
                listOf(2, 1, 3),
                listOf(2, 3, 1),
                listOf(3, 1, 2),
                listOf(3, 2, 1),
            ),
            listOf(1, 2, 3).combinations()
        )
    }

    @Test
    fun testCombinationsNotFull() {
        assertEquals(
            listOf(
                listOf(1, 2),
                listOf(1, 3),
                listOf(2, 1),
                listOf(2, 3),
                listOf(3, 1),
                listOf(3, 2),
            ),
            listOf(1, 2, 3).combinations(2)
        )
    }

    @Test
    fun testEnumerate() {
        val result = enumerate(2, 3) { it }.toList()
        assertEquals(
            listOf(
                listOf(0, 0, 0),
                listOf(0, 0, 1),
                listOf(0, 1, 0),
                listOf(0, 1, 1),
                listOf(1, 0, 0),
                listOf(1, 0, 1),
                listOf(1, 1, 0),
                listOf(1, 1, 1),
            ),
            result
        )
    }

    @Test
    fun testSubListsAll() {
        assertEquals(
            listOf(
                listOf(),
                listOf(3),
                listOf(2),
                listOf(2, 3),
                listOf(1),
                listOf(1, 3),
                listOf(1, 2),
                listOf(1, 2, 3),
            ),
            listOf(1, 2, 3).subLists().toList()
        )
    }

    @Test
    fun testSubListsBlock() {
        val subLists = mutableListOf<List<Int>>()
        listOf(1, 2, 3).subLists { subLists.add(it) }
        assertEquals(
            listOf(
                listOf(),
                listOf(3),
                listOf(2),
                listOf(2, 3),
                listOf(1),
                listOf(1, 3),
                listOf(1, 2),
                listOf(1, 2, 3),
            ),
            subLists
        )
    }


    @Test
    fun testSelect() {
        assertEquals(
            listOf(
                listOf(1, 2),
                listOf(1, 3),
                listOf(2, 3),
            ),
            listOf(1, 2, 3).select(2).toList()
        )
    }
}
