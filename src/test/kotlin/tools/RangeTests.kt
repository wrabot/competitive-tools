package tools

import kotlin.test.Test
import kotlin.test.assertEquals

class RangeTests {
    @Test
    fun testRange() {
        assertEquals(5, (8..12).size())
        assertEquals(11..15, (8..12).move(3))
        assertEquals(9..12, intRange(8.9..12.7))
        assertEquals(5..9, rangeMinMax(5, 9))
        assertEquals(5..9, rangeMinMax(9, 5))
    }

    @Test
    fun testIntersection() {
        assertEquals(true, (8..12).hasIntersection(10..16))
        assertEquals(false, (8..12).hasIntersection(14..16))
        assertEquals(false, (8..12).hasIntersection(4..6))
    }

    @Suppress("EmptyRange")
    @Test
    fun testMerge() {
        assertEquals(listOf(1..8, 12..18), listOf(10..3, 1..3, 7..8, 3..6, 12..18, 12..15).merge())
    }

    @Test
    fun testSplitWith() {
        assertEquals(Triple(null, 1L..16, null), (1L..16).splitWith(0L..20))
        assertEquals(Triple(1L..2, 3L..16, null), (1L..16).splitWith(3L..20))
        assertEquals(Triple(null, 1L..2, 3L..16), (1L..16).splitWith(0L..2))
        assertEquals(Triple(null, null, 6L..16), (6L..16).splitWith(2L..5))
        assertEquals(Triple(1L..16, null, null), (1L..16).splitWith(18L..20))
        assertEquals(Triple(1L..2, 3L..6L, 7L..16), (1L..16).splitWith(3L..6))
    }
}
