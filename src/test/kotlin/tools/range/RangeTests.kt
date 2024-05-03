package tools.range

import kotlin.test.Test
import kotlin.test.assertEquals

class RangeTests {
    @Test
    fun testRange() {
        assertEquals(5, (8..12).size)
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
        val merge = listOf(10L..3, 1L..3, 7L..8, 3L..6, 12L..18, 12L..15).merge()
        assertEquals(listOf(1L..8, 12L..18), merge)
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
