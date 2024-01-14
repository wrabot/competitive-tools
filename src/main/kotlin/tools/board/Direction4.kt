package tools.board

enum class Direction4(val xy: Board.XY, val c: Char) {
    East(Board.XY(1, 0), '>'),
    North(Board.XY(0, -1), '^'),
    West(Board.XY(-1, 0), '<'),
    South(Board.XY(0, 1), 'v');

    // TODO replace values with entries and check without get()
    fun turn(step: Int) = values().let { it[(ordinal + step).mod(it.size)] }

    val left get() = turn(1)
    val right get() = turn(-1)
}
