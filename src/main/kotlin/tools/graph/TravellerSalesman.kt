package tools.graph

import java.math.BigInteger

fun travellerSalesman(distances: Array<DoubleArray>): Double {
    val range = 1 until distances.size
    val cache = List(distances.size) { mutableMapOf<BigInteger, Double>() }
    fun BigInteger.best(i: Int): Double = cache[i].getOrPut(this) {
        if (bitCount() == distances.size - 2) distances[0][i] else range.fold(Double.POSITIVE_INFINITY) { acc, j ->
            if (j == i || testBit(j)) acc else acc.coerceAtMost(setBit(i).best(j) + distances[j][i])
        }
    }
    return range.fold(Double.POSITIVE_INFINITY) { acc, i ->
        acc.coerceAtMost(BigInteger.ZERO.best(i) + distances[i][0])
    }
}
