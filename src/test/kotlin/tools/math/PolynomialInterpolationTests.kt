package tools.math

import kotlin.test.Test
import kotlin.test.assertEquals

class PolynomialInterpolationTests {
    @Test
    fun testInterpolation() {
        val interpolation = PolynomialInterpolation(14524.0, 16778.0, 19194.0)
        assertEquals(14524.0, interpolation(-2.0))
        assertEquals(16778.0, interpolation(-1.0))
        assertEquals(19194.0, interpolation(0.0))
        assertEquals(44354.0, interpolation(8.0))
    }
}
