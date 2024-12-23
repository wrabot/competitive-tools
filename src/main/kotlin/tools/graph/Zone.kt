package tools.graph

fun <T : Comparable<T>> zone(start: T, neighbors: (T) -> Collection<T>): List<T> = mutableListOf(start).apply {
    val todo = mutableListOf(start)
    while (true) neighbors(todo.removeFirstOrNull() ?: break).forEach {
        val search = binarySearch(it)
        if (search < 0) {
            add(-search - 1, it)
            todo.add(it)
        }
    }
}
