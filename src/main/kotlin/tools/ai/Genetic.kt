package tools.ai

import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

@Suppress("MemberVisibilityCanBePrivate")
open class Genetic<G>(
    val genesSize: Int,
    val gene: Random.(geneIndex: Int) -> G,
    val crossoverOperator: CrossoverOperator = CrossoverOperator.Mono,
    val crossover: (G, G) -> Pair<G, G> = { a, b -> b to a },
    val random: Random = Random.Default,
    val fitness: (List<G>) -> Float,
) {
    inner class Individual(val genes: List<G>) {
        constructor() : this(List(genesSize) { random.gene(it) })

        init {
            require(genes.size == genesSize) { "Invalid gene size" }
        }

        val fitness = fitness(genes)

        fun shift(n: Int) = Individual(genes.indices.map { if (it < genesSize - n) genes[it + n] else random.gene(it) })
    }

    enum class CrossoverOperator(val selector: (Int, Random) -> Selector) {
        Mono({ size, random ->
            val index = random.nextInt(size)
            Selector { it < index }
        }),
        Multi({ size, random ->
            val i = random.nextInt(size)
            val j = random.nextInt(size)
            val range = min(i, j)..max(i, j)
            Selector { it in range }
        }),
        Uniform({ _, random -> Selector { random.nextBoolean() } });

        fun interface Selector {
            operator fun invoke(index: Int): Boolean
        }
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

    open fun crossover(a: MutableList<G>, b: MutableList<G>, random: Random, child: (MutableList<G>) -> Unit) {
        val selector = crossoverOperator.selector(genesSize, random)
        for (i in a.indices) if (selector(i)) {
            val (ga, gb) = crossover(a[i], b[i])
            a[i] = ga
            b[i] = gb
        }
        child(a)
        child(b)
    }

    open fun mutate(genes: MutableList<G>, random: Random) {
        val index = random.nextInt(genesSize)
        genes[index] = gene(random, index)
    }

    private fun rouletteWheelSelection(population: List<Individual>, random: Random): () -> List<G> {
        val levels = population.map { it.fitness }.runningReduce { acc, f -> acc + f }
        val total = levels.last()
        return {
            val level = total * random.nextFloat()
            population[-levels.binarySearch { if (it < level) -1 else 1 } - 1].genes
        }
    }
}
