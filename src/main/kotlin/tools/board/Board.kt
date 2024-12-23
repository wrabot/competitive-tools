package tools.board

import tools.XY

class Board<T>(val width: Int, val height: Int, val cells: List<T>) {
    constructor(width: Int, height: Int, cell: (x: Int, y: Int) -> T) :
            this(width, height, List(height) { y -> List(width) { x -> cell(x, y) } }.flatten())

    val xRange = 0 until width
    val yRange = 0 until height
    val xy = yRange.flatMap { y -> xRange.map { x -> XY(x, y) } }

    init {
        if (cells.size != width * height) throw Error("invalid board ${cells.size} !=  $width * $height (${width * height})")
    }

    fun toLines() = cells.chunked(width) { it.joinToString("") }
    override fun toString() = toLines().joinToString("\n")
    fun toString(start: XY, end: XY) = cells.chunked(width) {
        it.subList(start.x, end.x + 1).joinToString("")
    }.subList(start.y, end.y + 1).joinToString("\n")

    inline fun forEach(action: (xy: XY, cell: T) -> Unit) = xy.forEachIndexed { index, xy -> action(xy, cells[index]) }

    fun isValid(x: Int, y: Int) = x in xRange && y in yRange
    fun indexOf(x: Int, y: Int) = if (isValid(x, y)) y * width + x else null
    fun getOrNull(x: Int, y: Int) = if (isValid(x, y)) cells[y * width + x] else null
    operator fun get(x: Int, y: Int) =
        getOrNull(x, y) ?: throw IllegalArgumentException("invalid cell : x=$x y=$y width=$width height=$height")

    fun isValid(xy: XY) = isValid(xy.x, xy.y)
    fun indexOf(xy: XY) = indexOf(xy.x, xy.y)
    fun getOrNull(xy: XY) = getOrNull(xy.x, xy.y)
    operator fun get(xy: XY) = get(xy.x, xy.y)

    fun neighbors4(xy: XY) = xy.neighbors4().filter { isValid(it.x, it.y) }
    fun neighbors8(xy: XY) = xy.neighbors8().filter { isValid(it.x, it.y) }
}

fun <T> List<String>.toBoard(cell: (Char) -> T) = Board(get(0).length, size, flatMap { it.map(cell) })

fun <T> List<String>.toBoardXY(cell: (x: Int, y: Int, c: Char) -> T) =
    Board(get(0).length, size, flatMapIndexed { y, line -> line.mapIndexed { x, c -> cell(x, y, c) } })
