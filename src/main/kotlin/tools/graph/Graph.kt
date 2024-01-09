package tools.graph

fun bfs(size: Int, start: Int, block: (Int) -> List<Int>) {
    val todo = mutableListOf(start)
    val explored = BooleanArray(size)
    explored[start] = true
    while (true) {
        val current = todo.removeFirstOrNull() ?: break
        for (next in block(current)) {
            if (!explored[next]) {
                explored[next] = true
                todo.add(next)
            }
        }
    }
}

fun dfs(size: Int, start: Int, block: (Int) -> List<Int>) {
    val todo = mutableListOf(start)
    val discovered = BooleanArray(size)
    while (true) {
        val current = todo.removeLastOrNull() ?: break
        if (discovered[current]) continue
        discovered[current] = true
        todo.addAll(block(current))
    }
}

