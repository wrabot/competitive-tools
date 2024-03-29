package tools.board

import kotlin.test.Test
import kotlin.test.assertEquals

class GraphTests {
    private val board = """
#################
#i.G..c...e..H.p#
########.########
#j.A..b...f..D.o#
########@########
#k.E..a...g..B.n#
########.########
#l.F..d...h..C.m#
#################
    """.trimIndent().lines().toBoard { it }

    private val result = """
(i, {i=[0], c=[5, G], e=[9, G], f=[11, G], b=[11, G], g=[13, G], a=[13, G], p=[14, G, H], h=[15, G], d=[15, G], o=[16, G, D], j=[16, G, A], n=[18, G, B], k=[18, G, E], m=[20, G, C], l=[20, G, F]})
(c, {c=[0], e=[4], i=[5, G], f=[6], b=[6], g=[8], a=[8], p=[9, H], h=[10], d=[10], o=[11, D], j=[11, A], n=[13, B], k=[13, E], m=[15, C], l=[15, F]})
(e, {e=[0], c=[4], p=[5, H], f=[6], b=[6], g=[8], a=[8], i=[9, G], h=[10], d=[10], o=[11, D], j=[11, A], n=[13, B], k=[13, E], m=[15, C], l=[15, F]})
(p, {p=[0], e=[5, H], c=[9, H], f=[11, H], b=[11, H], g=[13, H], a=[13, H], i=[14, H, G], h=[15, H], d=[15, H], o=[16, H, D], j=[16, H, A], n=[18, H, B], k=[18, H, E], m=[20, H, C], l=[20, H, F]})
(j, {j=[0], b=[5, A], f=[9, A], e=[11, A], c=[11, A], g=[11, A], a=[11, A], h=[13, A], d=[13, A], o=[14, A, D], p=[16, A, H], i=[16, A, G], n=[16, A, B], k=[16, A, E], m=[18, A, C], l=[18, A, F]})
(b, {b=[0], f=[4], j=[5, A], e=[6], c=[6], g=[6], a=[6], h=[8], d=[8], o=[9, D], p=[11, H], i=[11, G], n=[11, B], k=[11, E], m=[13, C], l=[13, F]})
(f, {f=[0], b=[4], o=[5, D], e=[6], c=[6], g=[6], a=[6], h=[8], d=[8], j=[9, A], p=[11, H], i=[11, G], n=[11, B], k=[11, E], m=[13, C], l=[13, F]})
(o, {o=[0], f=[5, D], b=[9, D], e=[11, D], c=[11, D], g=[11, D], a=[11, D], h=[13, D], d=[13, D], j=[14, D, A], p=[16, D, H], i=[16, D, G], n=[16, D, B], k=[16, D, E], m=[18, D, C], l=[18, D, F]})
(@, {f=[3], b=[3], g=[3], a=[3], e=[5], c=[5], h=[5], d=[5], o=[8, D], j=[8, A], n=[8, B], k=[8, E], p=[10, H], i=[10, G], m=[10, C], l=[10, F]})
(k, {k=[0], a=[5, E], g=[9, E], f=[11, E], b=[11, E], h=[11, E], d=[11, E], e=[13, E], c=[13, E], n=[14, E, B], o=[16, E, D], j=[16, E, A], m=[16, E, C], l=[16, E, F], p=[18, E, H], i=[18, E, G]})
(a, {a=[0], g=[4], k=[5, E], f=[6], b=[6], h=[6], d=[6], e=[8], c=[8], n=[9, B], o=[11, D], j=[11, A], m=[11, C], l=[11, F], p=[13, H], i=[13, G]})
(g, {g=[0], a=[4], n=[5, B], f=[6], b=[6], h=[6], d=[6], e=[8], c=[8], k=[9, E], o=[11, D], j=[11, A], m=[11, C], l=[11, F], p=[13, H], i=[13, G]})
(n, {n=[0], g=[5, B], a=[9, B], f=[11, B], b=[11, B], h=[11, B], d=[11, B], e=[13, B], c=[13, B], k=[14, B, E], o=[16, B, D], j=[16, B, A], m=[16, B, C], l=[16, B, F], p=[18, B, H], i=[18, B, G]})
(l, {l=[0], d=[5, F], h=[9, F], g=[11, F], a=[11, F], f=[13, F], b=[13, F], m=[14, F, C], e=[15, F], c=[15, F], n=[16, F, B], k=[16, F, E], o=[18, F, D], j=[18, F, A], p=[20, F, H], i=[20, F, G]})
(d, {d=[0], h=[4], l=[5, F], g=[6], a=[6], f=[8], b=[8], m=[9, C], e=[10], c=[10], n=[11, B], k=[11, E], o=[13, D], j=[13, A], p=[15, H], i=[15, G]})
(h, {h=[0], d=[4], m=[5, C], g=[6], a=[6], f=[8], b=[8], l=[9, F], e=[10], c=[10], n=[11, B], k=[11, E], o=[13, D], j=[13, A], p=[15, H], i=[15, G]})
(m, {m=[0], h=[5, C], d=[9, C], g=[11, C], a=[11, C], f=[13, C], b=[13, C], l=[14, C, F], e=[15, C], c=[15, C], n=[16, C, B], k=[16, C, E], o=[18, C, D], j=[18, C, A], p=[20, C, H], i=[20, C, G]})
    """.trimIndent()

    @Test
    fun testGraph() {
        val graph = board.toGraph(
            isStart = { this == '@' || isLowerCase() },
            isEnd = Char::isLowerCase,
            isWall = { this == '#' }
        ) { path -> listOf(path.size) + path.map { board.cells[it] }.filter { it.isUpperCase() } }
        assertEquals(result, graph.toList().joinToString("\n"))
    }
}
