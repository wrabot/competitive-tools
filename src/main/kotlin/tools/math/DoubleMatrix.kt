package tools.math

import tools.math.double.isNotNul
import tools.math.double.isNul
import kotlin.math.abs
import kotlin.math.min

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

    fun init(vararg values: Double) = init(values.toList())
    fun init(values: List<Double>) = apply {
        require(values.size == width * height) { "Invalid init: ${values.size} == ${height * width}" }
        values.forEachIndexed { index, value -> cells[index] = value }
    }

    fun init(action: (r: Int, c: Int) -> Double) = apply {
        repeat(height) { r ->
            repeat(width) { c ->
                this[r, c] = action(r, c)
            }
        }
    }

    fun onEach(action: (r: Int, c: Int, v: Double) -> Unit) = apply {
        repeat(height) { r ->
            repeat(width) { c ->
                action(r, c, this[r, c])
            }
        }
    }

    fun det() = gaussJordan(true)

    fun gaussJordan(partial: Boolean = false): Double {
        var det = 1.0
        for (i in 0 until min(height, width)) {
            val m = (i until height).maxBy { abs(get(it, i)) }
            if (i != m) {
                det = -det
                for (k in i until width) set(i, k, get(m, k).also { set(m, k, get(i, k)) })
            }
            val a = get(i, i)
            if (a.isNul()) continue
            if ((a - 1.0).isNotNul()) {
                det *= a
                for (k in i until width) set(i, k, get(i, k) / a)
            }
            val r = if (partial) i + 1 until height else heightRange
            for (j in r) {
                if (j == i) continue
                val b = get(j, i)
                if (b.isNotNul()) for (k in i until width) set(j, k, get(j, k) - b * get(i, k))
            }
        }
        return det
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
