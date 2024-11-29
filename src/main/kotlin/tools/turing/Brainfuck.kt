package tools.turing

fun brainfuck(
    program: String,
    memorySize: Int = 30000,
    mod: Int = 256,
    input: () -> Int? = { null },
    output: (Int) -> Unit = ::print,
) {
    val memory = IntArray(memorySize)
    var mi = 0
    var index = 0
    while (index in program.indices) {
        when (program[index++]) {
            '.' -> output(memory[mi])
            ',' -> input()?.let { memory[mi] = it }
            '<' -> mi = (mi - 1).mod(memory.size)
            '>' -> mi = (mi + 1).mod(memory.size)
            '+' -> memory[mi] = (memory[mi] + 1).mod(mod)
            '-' -> memory[mi] = (memory[mi] - 1).mod(mod)
            '[' -> if (memory[mi] == 0) index = program.jump(index, 1, '[', ']')
            ']' -> if (memory[mi] != 0) index = program.jump(index - 2, -1, ']', '[')
        }
    }
}

private fun String.jump(index: Int, direction: Int, start: Char, end: Char): Int {
    var jump = index
    var level = 1
    while (level > 0 && jump in indices) {
        when (get(jump)) {
            start -> ++level
            end -> if (--level == 0) break
        }
        jump += direction
    }
    return jump
}
