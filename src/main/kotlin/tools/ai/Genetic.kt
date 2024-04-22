package tools.ai

import kotlin.random.Random

interface GAIndividual<T> {
    val fitness: Float
    fun crossover(other: T, random: Random): List<T>
    fun mutate(random: Random): T
}

fun <T : GAIndividual<T>> List<T>.evolve(
    epochs: Int,
    keepBest: Boolean = true,
    mutationProbability: Float = 0.1f,
    selection: (List<T>, Random) -> () -> T = ::rouletteWheelSelection,
    random: Random = Random.Default,
): List<T> {
    var sortedPopulation = sortedByDescending { it.fitness }
    val next = mutableListOf<T>()
    repeat(epochs) {
        if (keepBest) next.add(sortedPopulation.first())
        val select = selection(sortedPopulation, random)
        while (next.size < size)
            next.addAll(select().crossover(select(), random).map {
                if (random.nextFloat() <= mutationProbability) it.mutate(random) else it
            })
        next.sortByDescending { it.fitness }
        sortedPopulation = next.take(sortedPopulation.size)
        next.clear()
    }
    return sortedPopulation
}

private fun <T : GAIndividual<T>> rouletteWheelSelection(sortedPopulation: List<T>, random: Random): () -> T {
    val levels = sortedPopulation.map { it.fitness }.runningReduce { acc, f -> acc + f }
    return {
        val level = levels.last() * random.nextFloat()
        sortedPopulation[-levels.binarySearch { if (level <= it) 1 else -1 } - 1]
    }
}
