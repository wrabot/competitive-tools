package tools.math.tools

import tools.toWords
import kotlin.test.Test
import kotlin.test.assertEquals


class TextTests {
    @Test
    fun testText() {
        val words = "abc_def! hi-jkl \tMoù1p!345   qRSt\n1Z".toWords().toList()
        assertEquals(listOf("abc_def!", "hi-jkl", "Moù1p!345", "qRSt", "1Z"), words)
    }
}
