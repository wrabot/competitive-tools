package tools.ai.common

import kotlin.random.Random

abstract class AbstractGenetic<G>(
    val genesSize: Int,
    val random: Random = Random.Default,
    val fitness: (List<G>) -> Float,
) {
    inner class Individual(val genes: List<G>) {
        init {
            require(genes.size == genesSize) { "Invalid gene size" }
        }

        val fitness = fitness(genes)
    }

    fun evolve(
        population: List<Individual>,
        epochs: Int,
        keepBest: Boolean = true,
        mutationProbability: Float = 0.1f
    ): List<Individual> {
        var current = population.toMutableList()
        var next = mutableListOf<Individual>()
        repeat(epochs) {
            if (keepBest) next.add(current.maxBy { it.fitness })
            val select = selection(current, random)
            while (next.size < population.size) crossover(select().toMutableList(), select().toMutableList(), random) {
                if (random.nextFloat() < mutationProbability) mutate(it, random)
                val child = Individual(it)
                if (child.fitness > 0) next.add(child)
            }
            current = next.also { next = current }
            next.clear()
        }
        return current
    }

    open fun selection(population: List<Individual>, random: Random): () -> List<G> =
        rouletteWheelSelection(population, random)

    abstract fun individual(): Individual
    abstract fun crossover(a: MutableList<G>, b: MutableList<G>, random: Random, child: (MutableList<G>) -> Unit)
    abstract fun mutate(genes: MutableList<G>, random: Random)

    private fun rouletteWheelSelection(population: List<Individual>, random: Random): () -> List<G> {
        val levels = population.map { it.fitness }.runningReduce { acc, f -> acc + f }
        val total = levels.last()
        return {
            val level = total * random.nextFloat()
            population[-levels.binarySearch { if (it < level) -1 else 1 } - 1].genes
        }
    }
}
