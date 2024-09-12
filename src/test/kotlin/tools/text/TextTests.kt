package tools.text

import kotlin.test.Test
import kotlin.test.assertEquals


class TextTests {
    @Test
    fun testInts() {
        val integers = " 4 8 2 6 1 ".toInts()
        assertEquals(listOf(4, 8, 2, 6, 1), integers)
    }

    @Test
    fun testWords() {
        val words = " abc_def! hi-jkl \tMoù1p!345   qRSt\n1Z ".toWords()
        assertEquals(listOf("abc_def!", "hi-jkl", "Moù1p!345", "qRSt", "1Z"), words)
    }

    @Test
    fun testMatch() {
        val matchOK = "ABCD = 123->45".match("(.*) = (\\d+)->(\\d+)".toRegex())
        assertEquals(listOf("ABCD", "123", "45"), matchOK)

        val matchKO = "ABCD = 123 -> 45".match("(.*) = (\\d+)->(\\d+)".toRegex())
        assertEquals(null, matchKO)
    }
}
