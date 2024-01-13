package tools.graph

fun bfs(size: Int, start: Int, block: (Int) -> List<Int>) =
    search(size, start, MutableList<Int>::removeFirstOrNull, block)

fun dfsLenient(size: Int, start: Int, block: (Int) -> List<Int>) =
    search(size, start, MutableList<Int>::removeLastOrNull, block)

fun dfsStrict(size: Int, start: Int, block: (Int) -> List<Int>) {
    val todo = mutableListOf(start)
    val visited = BooleanArray(size)
    while (true) {
        val current = todo.removeLastOrNull() ?: break
        if (visited[current]) continue
        visited[current] = true
        todo.addAll(block(current))
    }
}

fun search(size: Int, start: Int, removeNextOrNull: MutableList<Int>.() -> Int?, block: (Int) -> List<Int>) {
    val todo = mutableListOf(start)
    val discovered = BooleanArray(size)
    discovered[start] = true
    while (true) {
        val current = todo.removeNextOrNull() ?: break
        for (next in block(current)) {
            if (!discovered[next]) {
                discovered[next] = true
                todo.add(next)
            }
        }
    }
}
