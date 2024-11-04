package tools.ai

import tools.ai.common.CrossoverSelector
import kotlin.test.Test
import kotlin.test.assertEquals

class IndependentGeneticTests {
    private val target = "target"

    @Test
    fun testGeneticMono() {
        assertEquals(target, genetic(CrossoverSelector.Mono))
    }

    @Test
    fun testGeneticMulti() {
        assertEquals(target, genetic(CrossoverSelector.Multi))
    }

    @Test
    fun testGeneticUniform() {
        assertEquals(target, genetic(CrossoverSelector.Uniform))
    }

    @Test
    fun testGeneticShift() {
        val genetic = IndependentGenetic(10, gene = { nextInt() }) { 1f }
        val i = genetic.individual()
        val shift = genetic.shift(i, 2)
        assertEquals(i.genes.drop(2), shift.genes.dropLast(2))
    }

    private fun genetic(crossoverType: CrossoverSelector): String {
        val genetic = IndependentGenetic(target.length, gene = { nextInt(26) }, crossoverType) { genes ->
            target.zip(genes.toResult()).count { it.first == it.second }.toFloat()
        }
        val population = List(100) { genetic.individual() }
        return genetic.evolve(population, 100).maxBy { it.fitness }.genes.toResult()
    }

    private fun List<Int>.toResult() = map { 'a' + it }.toCharArray().concatToString()
}
