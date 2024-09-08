package tools

import kotlin.test.Test
import kotlin.test.assertEquals

class XYTests {
    @Test
    fun testXY() {
        val xy1 = "3 -7".toXY(" ")
        val xy2 = XY(3, 8)
        assertEquals(10, xy1.manhattan())
        assertEquals(XY(6, 1), xy1 + xy2)
        assertEquals(XY(0, -15), xy1 - xy2)
        assertEquals(XY(9, -21), xy1 * 3)
    }
}
