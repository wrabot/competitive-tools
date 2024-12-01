package tools.graph

/**
 * A Floyd-Warshall algorithm that finds the shortest paths in a directed weighted graph with positive or negative edge weights (but with no negative cycles).
 * https://en.wikipedia.org/wiki/Floyd-Warshall_algorithm
 *
 * @param size the number of vertices.
 * @param weights the cost of existing edges (origin, destination).
 */
class ShortestPaths(size: Int, weights: Map<Pair<Int, Int>, Double>) {
    private val prev = Array(size) { Array<Int?>(size) { null } }
    val distances = Array(size) { DoubleArray(size) { Double.POSITIVE_INFINITY } }

    override fun toString() = distances.toList().map { it.toList() }.toString()

    init {
        weights.forEach { (u, v), w ->
            distances[u][v] = w
            prev[u][v] = u
        }
        val indices = distances.indices
        for (v in indices) {
            distances[v][v] = 0.0
            prev[v][v] = v
        }
        for (k in indices)
            for (i in indices)
                for (j in indices) {
                    val d = distances[i][k] + distances[k][j]
                    if (distances[i][j] > d) {
                        distances[i][j] = d
                        prev[i][j] = prev[k][j]
                    }
                }
    }

    fun path(u: Int, v: Int): List<Int>? {
        val path = mutableListOf<Int>()
        var current = v
        while (true) {
            path.add(current)
            if (current == u) return path.reversed()
            current = prev[u][current] ?: return null
        }
    }
}
