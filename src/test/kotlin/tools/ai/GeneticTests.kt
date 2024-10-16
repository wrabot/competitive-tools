package tools.ai

import kotlin.test.Test
import kotlin.test.assertEquals

class GeneticTests {
    private val target = "target"

    @Test
    fun testGeneticMono() {
        assertEquals(target, genetic(Genetic.CrossoverOperator.Mono))
    }

    @Test
    fun testGeneticMulti() {
        assertEquals(target, genetic(Genetic.CrossoverOperator.Multi))
    }

    @Test
    fun testGeneticUniform() {
        assertEquals(target, genetic(Genetic.CrossoverOperator.Uniform))
    }

    private fun genetic(crossoverType: Genetic.CrossoverOperator): String {
        val genetic = Genetic(target.length, gene = { nextInt(26) }, crossoverType) { genes ->
            target.zip(genes.toResult()).count { it.first == it.second }.toFloat()
        }
        val population = List(100) { genetic.Individual() }
        return genetic.evolve(population, 100).maxBy { it.fitness }.genes.toResult()
    }

    private fun List<Int>.toResult() = map { 'a' + it }.toCharArray().concatToString()
}
