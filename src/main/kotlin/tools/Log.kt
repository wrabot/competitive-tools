package tools

fun log(message: Any?) = System.err.println(message)
fun <T> T.log() = apply { log(this) }

fun List<Triple<Any, Any, Any?>>.logGraph() = joinToString("\n", "digraph {\n", "\n}") {
    "${it.first} -> ${it.second}" + if (it.third == null) "" else " [label=\"${it.third}\"]"
}.log()
