package tools.math

class DoubleMatrix(private val cells: List<DoubleArray>) {
    constructor(height: Int, width: Int = height) : this(List(height) { DoubleArray(width) })
    constructor(height: Int, width: Int = height, init: (r: Int, c: Int) -> Double) : this(List(height) { r -> DoubleArray(width) { c -> init(r, c) } })

    val height = cells.size
    val heightRange = cells.indices
    val width = cells.asSequence().map { it.size }.distinct().single()
    val widthRange = 0 until width
    
    fun init(vararg values: Double) = apply { values.forEachIndexed { index, value -> cells[index / width][index % width] = value } }
    fun copy() = DoubleMatrix(height, width) { r, c -> cells[r][c] }
    fun onEach(action: (r: Int, c: Int, v: Double) -> Unit) = repeat(height) { r -> repeat(width) { c -> action(r, c, cells[r][c]) } }
    
    operator fun get(r: Int, c: Int) = cells[r][c]
    operator fun set(r: Int, c: Int, v: Double) = cells[r].set(c, v)
    operator fun plus(other: DoubleMatrix) = DoubleMatrix(height, width) { r, c -> cells[r][c] + other.cells[r][c] }
    operator fun minus(other: DoubleMatrix) = DoubleMatrix(height, width) { r, c -> cells[r][c] - other.cells[r][c] }
    operator fun unaryMinus() = DoubleMatrix(height, width) { r, c -> -cells[r][c] }
    operator fun plusAssign(other: DoubleMatrix) = onEach { r, c, _ -> cells[r][c] += other.cells[r][c] }
    operator fun minusAssign(other: DoubleMatrix) = onEach { r, c, _ -> cells[r][c] -= other.cells[r][c] }
    operator fun times(other: DoubleMatrix): DoubleMatrix {
        require(width == other.height)
        return DoubleMatrix(height, other.width) { r, c -> widthRange.sumOf { cells[r][it] * other.cells[it][c] } }
    }

    override fun toString(): String {
        val strings = cells.flatMap { row -> row.map { it.toString() } }
        val padding = strings.maxOf { it.length }
        return strings.chunked(width).joinToString("\n") { row ->
            row.joinToString(" ") { it.padStart(padding) }
        }
    }
}
