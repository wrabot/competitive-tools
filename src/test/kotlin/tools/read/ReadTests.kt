package tools.read

import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals


class ReadTests {
    private val stdin = System.`in`
    private val text = """
            hello
            world
            and
            co
        """.trimIndent()
    
    @AfterTest
    fun after() {
        System.setIn(stdin)
    }

    @Test
    fun testReadAllLines() {
        System.setIn(text.byteInputStream())
        assertEquals(text.lines(), readAllLines())
    }

    @Test
    fun testReadLines() {
        System.setIn(text.byteInputStream())
        assertEquals(text.lines().take(2), readLines(2))
    }
}
