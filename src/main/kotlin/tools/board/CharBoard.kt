package tools.board

import tools.XY

typealias CharBoard = List<CharArray>

fun List<String>.toCharBoard() = map { it.toCharArray() }
fun CharBoard.toLines() = map { it.concatToString() }
fun CharBoard.toText() = joinToString("\n") { it.concatToString() }

inline fun CharBoard.forEach(action: (x: Int, y: Int, c: Char) -> Unit) =
    forEachIndexed { y, row -> row.forEachIndexed { x, c -> action(x, y, c) } }

fun CharBoard.getOrNull(xy: XY) = getOrNull(xy.y)?.getOrNull(xy.x)
operator fun CharBoard.get(xy: XY): Char = get(xy.y)[xy.x]
operator fun CharBoard.set(xy: XY, c: Char) = get(xy.y).set(xy.x, c)

fun CharBoard.neighbors4(xy: XY) = xy.neighbors4().filter { getOrNull(it) != null }
fun CharBoard.neighbors8(xy: XY) = xy.neighbors8().filter { getOrNull(it) != null }
