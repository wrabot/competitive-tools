package tools.board

fun <T, U> Board<T>.toGraph(
    isStart: T.() -> Boolean,
    isEnd: T.() -> Boolean,
    isWall: T.() -> Boolean,
    transform: (List<Int>) -> U,
): MutableMap<T, MutableMap<T, U>> {
    val links = mutableMapOf<T, MutableMap<T, U>>()
    cells.forEachIndexed { index, cell ->
        if (!cell.isStart()) return@forEachIndexed
        links[cell] = mutableMapOf()
        val paths = arrayOfNulls<List<Int>?>(cells.size)
        paths[index] = emptyList()
        val todo = mutableListOf(index)
        while (true) {
            val current = todo.removeFirstOrNull() ?: break
            for (next in neighbors4(xy[current]).mapNotNull { indexOf(it) }) {
                val c = cells[next]
                if (c.isWall()) continue
                var nextPath = paths[next]
                val newPath = paths[current]!! + next
                if (nextPath == null || newPath.size < nextPath.size) {
                    nextPath = newPath
                    paths[next] = newPath
                    todo.add(next)
                }
                if (c.isEnd()) links[cell]!![c] = transform(nextPath)
            }
        }
    }
    return links
}
