package tools.math

import org.junit.jupiter.api.assertThrows
import tools.math.gj.det
import kotlin.test.Test
import kotlin.test.assertEquals

class DoubleMatrixTests {
    private val tolerance = 0.0000000000001

    @Test
    fun testDeterminant() {
        assertEquals(
            1.0,
            DoubleMatrix(2).init(
                1.0, 4.0,
                2.0, 9.0,
            ).det(),
            tolerance
        )
        assertEquals(
            -19.0,
            DoubleMatrix(3).init(
                4.0, 5.0, 1.0,
                9.0, 8.0, 2.0,
                7.0, 6.0, 3.0,
            ).det(),
            tolerance
        )
        assertEquals(
            81.0,
            DoubleMatrix(3).init(
                9.0, 8.0, 2.0,
                0.0, 5.0, 1.0,
                0.0, 6.0, 3.0,
            ).det(),
            tolerance
        )
        assertEquals(
            -81.0,
            DoubleMatrix(3).init(
                0.0, 5.0, 1.0,
                9.0, 8.0, 2.0,
                0.0, 6.0, 3.0,
            ).det(),
            tolerance
        )
        assertEquals(
            81.0,
            DoubleMatrix(3).init(
                0.0, 5.0, 1.0,
                0.0, 6.0, 3.0,
                9.0, 8.0, 2.0,
            ).det(),
            tolerance
        )
        assertEquals(
            108.0,
            DoubleMatrix(4).init(
                2.0, 4.0, 5.0, 6.0,
                -1.0, 5.0, 6.0, 9.0,
                3.0, 7.0, 1.0, -6.0,
                4.0, -2.0, 3.0, 5.0,
            ).det(),
            tolerance
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
    fun testWidth() {
        assertThrows<IllegalArgumentException> {
            DoubleMatrix(listOf(DoubleArray(2), DoubleArray(1)))
        }
        assertEquals(3, DoubleMatrix(10,4).widthRange.last)
    }
}
