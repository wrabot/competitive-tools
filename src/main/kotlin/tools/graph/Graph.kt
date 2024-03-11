package tools.graph

fun bfs(size: Int, start: Int, block: (Int) -> List<Int>) {
    val todo = mutableListOf(start)
    val discovered = BooleanArray(size)
    discovered[start] = true
    while (true) {
        val current = todo.removeFirstOrNull() ?: break
        for (next in block(current)) {
            if (!discovered[next]) {
                discovered[next] = true
                todo.add(next)
            }
        }
    }
}

fun dfs(size: Int, start: Int, block: (Int) -> List<Int>) {
    val todo = mutableListOf(start)
    val visited = BooleanArray(size)
    while (true) {
        val current = todo.removeLastOrNull() ?: break
        if (visited[current]) continue
        visited[current] = true
        todo.addAll(block(current))
    }
}
