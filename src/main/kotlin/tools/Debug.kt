package tools

fun debug(message: Any?) = System.err.println(message)
fun <T> T.debug() = apply { debug(this) }

fun List<Triple<Any, Any, Any?>>.debugGraph() = joinToString("\n", "digraph {\n", "\n}") {
    "${it.first} -> ${it.second}" + if (it.third == null) "" else " [label=\"${it.third}\"]"
}.debug()
