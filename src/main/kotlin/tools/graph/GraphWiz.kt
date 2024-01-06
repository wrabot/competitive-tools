package tools.graph

@JvmName("valueEdgeToGraphWiz")
fun List<ValuedEdge>.toGraphWiz() = map { Triple(it.source, it.destination, it.value) }.toGraphWiz()

@JvmName("tripleToGraphWiz")
fun List<Triple<Any, Any, Any?>>.toGraphWiz() = joinToString("\n", "digraph {\n", "\n}") {
    "${it.first} -> ${it.second}" + if (it.third == null) "" else " [label=${it.third}]"
}
