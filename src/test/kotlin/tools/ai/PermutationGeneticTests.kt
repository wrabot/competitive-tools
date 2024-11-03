package tools.ai

import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals

class PermutationGeneticTests {
    private val range = 1..10
    private val target = range.toList()

    @Test
    fun testGenetic() {
        val genetic = PermutationGenetic(range, random = Random(0)) { genes ->
            1 / genes.zipWithNext { a, b -> 1 + a.compareTo(b) }.sum().toFloat()
        }
        val population = List(100) { genetic.individual() }
        assertEquals(target, genetic.evolve(population, 1000).maxBy { it.fitness }.genes)
    }
}
