package tools.turing

import kotlin.test.Test
import kotlin.test.assertEquals

class BrainfuckTests {
    @Test
    fun testBrainfuck() {
        val buffer = StringBuilder()
        brainfuck(
            """
            ++++++++++[>+>+++>+++++++>++++++++++<<<<-]>>>++++++++++++++.>+++++++++++++++++.<<
            ++.>+++++++++++++.>--.<<.>+++.+.--.>----.++++++.+.<++.>----.++.<<.>>+.-------.<<.
            >>++.<.>+++++.<<.>-.+.<.>---.>---.<-.++++++++.>----.<---.>+++++++.<---.++++++++.
        """
        ) {
            buffer.append(it.toChar())
        }
        assertEquals("Tu as decouvert un peu de brainfuck", buffer.toString())
    }

    @Test
    fun testAddition() {
        var out = 0
        val input = listOf('3', '5').map { it.code }.toMutableList()
        brainfuck(",>++++++[<-------->-],[<+>-]<.", input = input::removeLast) { out = it }
        assertEquals('8', out.toChar())
    }

    @Test
    fun testMultiplication() {
        var out = 0
        val input = listOf('2', '4').map { it.code }.toMutableList()
        brainfuck(",>,>>++++++++++++++++[-<+++<---<--->>>]<<[<[>>+>+<<<-]>>>[<<<+>>>-]<<-]>.", input = input::removeLast) { out = it }
        assertEquals('8', out.toChar())
    }
}
