package tools.graph

fun <T> intGraph(vertices: Array<T>, edges: Map<T, Collection<T>>) =
    Array(vertices.size) { edges[vertices[it]].orEmpty().map(vertices::indexOf).toSet() }
