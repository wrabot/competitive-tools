package tools.graph

class FloydWarshall(size: Int, weights: Map<Pair<Int, Int>, Double>) {
    private val prev = Array(size) { Array<Int?>(size) { null } }
    val dist = Array(size) { DoubleArray(size) { Double.POSITIVE_INFINITY } }

    override fun toString() = dist.toList().map { it.toList() }.toString()

    init {
        weights.forEach { (u, v), w ->
            dist[u][v] = w
            prev[u][v] = u
        }
        val indices = dist.indices
        for (v in indices) {
            dist[v][v] = 0.0
            prev[v][v] = v
        }
        for (k in indices)
            for (i in indices)
                for (j in indices) {
                    val d = dist[i][k] + dist[k][j]
                    if (dist[i][j] > d) {
                        dist[i][j] = d
                        prev[i][j] = prev[k][j]
                    }
                }
    }

    fun path(u: Int, v: Int) = mutableListOf<Int>().apply { path(u, v) }.reversed()

    private tailrec fun MutableList<Int>.path(u: Int, v: Int?) {
        if (v != null && u != v) {
            add(v)
            path(u, prev[u][v])
        }
    }
}
