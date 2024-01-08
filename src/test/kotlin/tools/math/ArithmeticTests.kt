package tools.math

import kotlin.test.Test
import kotlin.test.assertEquals

class ArithmeticTests {
    @Test
    fun testGcd() {
        assertEquals(12, gcd(36, 60))
    }

    @Test
    fun testLcm() {
        assertEquals(180, lcm(36, 60))
    }
}
