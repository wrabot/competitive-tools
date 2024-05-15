package tools.optimization

fun knapsackValue(items: List<Pair<Int, Double>>, capacity: Int): Double {
    val m = DoubleArray(capacity + 1)
    items.forEach { (w, v) -> for (c in 0..capacity - w) m[c] = m[c].coerceAtLeast(m[c + w] + v) }
    return m[0]
}
