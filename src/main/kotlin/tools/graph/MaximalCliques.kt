package tools.graph

/**
 * A Bron-Kerbosch algorithm that finding all the maximal cliques.
 * https://en.wikipedia.org/wiki/Bron-Kerbosch_algorithm
 *
 * @param graph the edges for each vertex.
 * @param withPivot if true, more efficient for graphs with many non-maximal cliques.
 */
fun findAllMaximalCliques(graph: Array<Set<Int>>, withPivot: Boolean) = mutableListOf<Set<Int>>().apply {
    bronKerbosch(graph, withPivot, emptySet(), graph.indices.toSet(), emptySet())
}

private fun MutableList<Set<Int>>.bronKerbosch(
    graph: Array<Set<Int>>,
    withPivot: Boolean,
    r: Set<Int>,
    p: Set<Int>,
    x: Set<Int>
) {
    if (p.isEmpty() && x.isEmpty()) add(r)
    val pm = p.toMutableSet()
    val xm = x.toMutableSet()
    val c = if (withPivot) (p + x).map { p - graph[it] }.minByOrNull { it.size }.orEmpty() else p
    for (v in c) {
        val n = graph[v]
        bronKerbosch(graph, withPivot, r + v, pm intersect n, xm intersect n)
        pm.remove(v)
        xm.add(v)
    }
}
