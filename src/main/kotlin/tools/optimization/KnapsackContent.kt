package tools.optimization

fun knapsackContent(items: List<Pair<Int, Double>>, capacity: Int): List<Int> {
    val m = Array(items.size + 1) { DoubleArray(capacity + 1) }
    items.forEachIndexed { i, (w, v) ->
        for (c in 0..capacity) m[i + 1][c] = if (c < w) m[i][c] else m[i][c].coerceAtLeast(m[i][c - w] + v)
    }
    var c = capacity
    return items.indices.reversed().filter {
        if (m[it + 1][c] == m[it][c]) return@filter false
        c -= items[it].first
        true
    }
}
