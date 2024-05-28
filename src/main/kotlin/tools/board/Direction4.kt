package tools.board

enum class Direction4(val xy: XY, val c: Char) {
    East(XY(1, 0), '>'),
    North(XY(0, -1), '^'),
    West(XY(-1, 0), '<'),
    South(XY(0, 1), 'v');

    // TODO replace values with entries and check without get()
    fun turn(step: Int) = values().let { it[(ordinal + step).mod(it.size)] }

    val left get() = turn(1)
    val right get() = turn(-1)
}
