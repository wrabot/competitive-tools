package tools.math

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DoubleMatrixTests {
    @Test
    fun testDeterminant() {
        assertEquals(
            1.0,
            DoubleMatrix(2).init(
                1.0, 4.0,
                2.0, 9.0,
            ).det()
        )
        assertEquals(
            -19.0,
            DoubleMatrix(3).init(
                4.0, 5.0, 1.0,
                9.0, 8.0, 2.0,
                7.0, 6.0, 3.0,
            ).det()
        )
        assertEquals(
            81.0,
            DoubleMatrix(3).init(
                9.0, 8.0, 2.0,
                0.0, 5.0, 1.0,
                0.0, 6.0, 3.0,
            ).det()
        )
        assertEquals(
            -81.0,
            DoubleMatrix(3).init(
                0.0, 5.0, 1.0,
                9.0, 8.0, 2.0,
                0.0, 6.0, 3.0,
            ).det()
        )
        assertEquals(
            81.0,
            DoubleMatrix(3).init(
                0.0, 5.0, 1.0,
                0.0, 6.0, 3.0,
                9.0, 8.0, 2.0,
            ).det()
        )
        assertEquals(
            DoubleMatrix(4).init(
                2.0, 4.0, 5.0, 6.0,
                -1.0, 5.0, 6.0, 9.0,
                3.0, 7.0, 1.0, -6.0,
                4.0, -2.0, 3.0, 5.0,
            ).det(),
            108.0
        )
    }

    private val a = DoubleMatrix(3).init(
        4.0, 5.0, 1.0,
        9.0, 8.0, 2.0,
        7.0, 6.0, 3.0,
    )

    private val b = DoubleMatrix(3).init(
        2.0, 7.0, 6.0,
        4.0, 3.0, 2.0,
        3.0, 6.0, 5.0,
    )

    @Test
    fun testPlus() {
        val result = a + b
        result.onEach { r, c, v -> assertEquals(a[r, c] + b[r, c], v) }
    }

    @Test
    fun testMinus() {
        val result = a - b
        result.onEach { r, c, v -> assertEquals(a[r, c] - b[r, c], v) }
    }

    @Test
    fun testUnaryMinus() {
        val result = -a
        result.onEach { r, c, v -> assertEquals(-a[r, c], v) }
    }

    @Test
    fun testTime() {
        val result = a * b
        val expected = DoubleMatrix(3).init(
            31.0, 49.0, 39.0,
            56.0, 99.0, 80.0,
            47.0, 85.0, 69.0,
        )
        result.onEach { r, c, v -> assertEquals(expected[r, c], v) }
    }

    @Test
    fun testCopy() {
        a.copy().onEach { r, c, v -> assertEquals(a[r, c], v) }
    }

    @Test
    fun testValid() {
        DoubleMatrix(4, 3).run {
            heightRange.onEach { r -> widthRange.onEach { c -> assertTrue(isValid(r, c)) } }
            assertFalse(isValid(height, 0))
            assertFalse(isValid(0, width))
        }
    }
}