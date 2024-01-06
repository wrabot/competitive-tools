package tools.math

class DoubleMatrix(val height: Int, val width: Int = height) {
    private val cells = DoubleArray(height * width)

    val heightRange = 0 until height
    val widthRange = 0 until width

    override fun toString(): String {
        val strings = cells.map { it.toString() }
        val padding = strings.maxOf { it.length }
        return strings.chunked(width).joinToString("\n") { row ->
            row.joinToString(" ") { it.padStart(padding) }
        }
    }

    fun init(vararg values: Double) = apply {
        require(values.size == width * height) { "Invalid init: ${values.size} == ${height * width}" }
        values.forEachIndexed { index, value -> cells[index] = value }
    }

    fun onEach(block: (r: Int, c: Int, v: Double) -> Unit) = apply {
        repeat(height) { r ->
            repeat(width) { c ->
                block(r, c, this[r, c])
            }
        }
    }

    // https://en.wikipedia.org/wiki/Bareiss_algorithm
    fun det(): Double {
        require(width == height) { "det: invalid matrix $height $width" }
        var sign = 1
        var d = 1.0
        val m = copy()
        val rows = IntArray(height) { it }
        repeat(height - 1) { k ->
            if (m[rows[k], k] == 0.0) {
                var l = k + 1
                while (true) {
                    if (m[rows[l], k] != 0.0) {
                        rows[l] = rows[k].also { rows[k] = rows[l] }
                        sign = -sign
                        break
                    }
                    if (++l == height) return 0.0
                }
            }
            val p = m[rows[k], k]
            for (i in k + 1 until height)
                for (j in k + 1 until height)
                    m[rows[i], j] = (p * m[rows[i], j] - m[rows[i], k] * m[rows[k], j]) / d
            d = p
        }
        return sign * m[rows[height - 1], height - 1]
    }


    fun copy() = DoubleMatrix(width, height).also { matrix ->
        cells.indices.forEach { matrix.cells[it] = cells[it] }
    }

    fun isValid(r: Int, c: Int) = r in heightRange && c in widthRange

    operator fun get(r: Int, c: Int): Double {
        checkRC(r, c)
        return cells[r * width + c]
    }

    operator fun set(r: Int, c: Int, v: Double) {
        checkRC(r, c)
        cells[r * width + c] = v
    }

    operator fun plus(other: DoubleMatrix) = DoubleMatrix(height, width).also { matrix ->
        checkHW(other.height, other.width)
        cells.indices.forEach { matrix.cells[it] = cells[it] + other.cells[it] }
    }

    operator fun minus(other: DoubleMatrix) = DoubleMatrix(width, height).also { matrix ->
        checkHW(other.height, other.width)
        cells.indices.forEach { matrix.cells[it] = cells[it] - other.cells[it] }
    }

    operator fun unaryMinus() = DoubleMatrix(width, height).also { matrix ->
        cells.indices.forEach { matrix.cells[it] = -cells[it] }
    }

    operator fun plusAssign(other: DoubleMatrix) {
        checkHW(other.height, other.width)
        cells.indices.forEach { cells[it] += other.cells[it] }
    }

    operator fun minusAssign(other: DoubleMatrix) {
        checkHW(other.height, other.width)
        cells.indices.forEach { cells[it] -= other.cells[it] }
    }

    operator fun times(other: DoubleMatrix) = DoubleMatrix(width, height).also { matrix ->
        require(width == other.height) { "mul: incompatible matrix" }
        for (r in heightRange) for (c in other.widthRange)
            matrix[r, c] = widthRange.fold(0.0) { acc, index -> acc + get(r, index) * other[index, c] }
    }

    private fun checkRC(row: Int, column: Int) =
        require(isValid(row, column)) { "invalid row or column: $row < $height && $column < $width" }

    private fun checkHW(h: Int, w: Int) = require(height == h && width == w) { "incompatible matrix" }
}
