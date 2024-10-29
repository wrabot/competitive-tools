package tools.math.gj

import tools.math.DoubleMatrix
import tools.math.double.isNotNul
import tools.math.double.isNul
import kotlin.math.abs
import kotlin.math.min

fun DoubleMatrix.det() = gaussJordan(true)

fun DoubleMatrix.gaussJordan(partial: Boolean = false): Double {
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