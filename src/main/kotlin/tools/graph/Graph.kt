package tools.graph

/**
 * Breadth First Search
 *
 * @param size number of vertices.
 * @param start starting vertex.
 * @param neighbors the edges from one vertex.
 */
fun bfs(size: Int, start: Int, neighbors: (Int) -> Collection<Int>?) {
    val todo = mutableListOf(start)
    val discovered = BooleanArray(size)
    discovered[start] = true
    while (true) {
        val current = todo.removeFirstOrNull() ?: break
        for (next in neighbors(current) ?: break) {
            if (!discovered[next]) {
                discovered[next] = true
                todo.add(next)
            }
        }
    }
}

/**
 * Depth First Search
 *
 * @param size number of vertices.
 * @param start starting vertex.
 * @param neighbors the edges from one vertex.
 */
fun dfs(size: Int, start: Int, neighbors: (Int) -> Collection<Int>?) {
    val todo = mutableListOf(start)
    val visited = BooleanArray(size)
    while (true) {
        val current = todo.removeLastOrNull() ?: break
        if (visited[current]) continue
        visited[current] = true
        todo.addAll(neighbors(current) ?: break)
    }
}

/**
 * Maximum Cardinality Search
 * visits the vertices in such an order that every time the vertex with the most already visited neighbors is visited
 * The algorithm provides a simple basis for deciding whether a graph is chordal
 *
 * @param size number of vertices.
 * @param start starting vertex.
 * @param neighbors the edges from one vertex.
 */
fun mcs(size: Int, start: Int, neighbors: (Int) -> Collection<Int>) {
    val weights = IntArray(size)
    weights[start] = 1
    repeat(size) {
        val v = weights.indices.maxBy { weights[it] }
        weights[v] = -1
        neighbors(v).forEach { if (weights[it] != -1) weights[it]++ }
    }
}
