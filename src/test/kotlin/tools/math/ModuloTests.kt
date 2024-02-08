package tools.math

import java.math.BigInteger
import kotlin.test.Test
import kotlin.test.assertEquals

class ModuloTests {
    @Test
    fun testPlus() {
        val plus = Modular(133.bi).run { 122.bi + 31.bi }
        assertEquals(20, plus.intValueExact())
    }

    @Test
    fun testMinus() {
        val plus = Modular(133.bi).run { 20.bi - 31.bi }
        assertEquals(122, plus.intValueExact())
    }

    @Test
    fun testTimes() {
        val times = Modular(7645435225654.bi).run { 56353336663.bi * 454352252.bi }
        assertEquals(1021806769582L, times.longValueExact())
    }

    @Test
    fun testDiv() {
        val div = Modular(7645435225654.bi).run { 1.bi / 56353336663.bi }
        assertEquals(3032969833125L, div.longValueExact())
    }

    @Test
    fun testPow() {
        val pow = Modular(7645435225654.bi).run { 56353336663.bi.pow(454352252.bi) }
        assertEquals(4117203498237L, pow.longValueExact())
    }

    @Test
    fun testChineseRemainder() {
        val chineseRemainder = chineseRemainder(listOf(2.bi to 3.bi, 3.bi to 5.bi, 2.bi to 7.bi))
        assertEquals(23, chineseRemainder.toInt())
    }
}
