package tools.graph

fun bfs(size: Int, start: Int, neighbors: (Int) -> List<Int>?) {
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

fun dfs(size: Int, start: Int, neighbors: (Int) -> List<Int>?) {
    val todo = mutableListOf(start)
    val visited = BooleanArray(size)
    while (true) {
        val current = todo.removeLastOrNull() ?: break
        if (visited[current]) continue
        visited[current] = true
        todo.addAll(neighbors(current) ?: break)
    }
}

fun mcs(size: Int, start: Int, neighbors: (Int) -> List<Int>) {
    val weights = IntArray(size)
    weights[start] = 1
    repeat(size) {
        val v = weights.indices.maxBy { weights[it] }
        weights[v] = -1
        neighbors(v).forEach { if (weights[it] != -1) weights[it]++ }
    }
}
