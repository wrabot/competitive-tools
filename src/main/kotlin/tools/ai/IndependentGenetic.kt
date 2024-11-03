package tools.ai

import tools.ai.common.AbstractGenetic
import kotlin.random.Random

class IndependentGenetic<G>(
    genesSize: Int,
    val gene: Random.(geneIndex: Int) -> G,
    val crossoverSelector: (Int, Random) -> Selector,
    val crossoverOperator: (G, G) -> Pair<G, G> = { a, b -> b to a },
    random: Random = Random.Default,
    fitness: (List<G>) -> Float,
) : AbstractGenetic<G>(genesSize, random, fitness) {
    fun interface Selector {
        operator fun invoke(index: Int): Boolean
    }

    fun individual() = Individual(List(genesSize) { random.gene(it) })
    fun shift(individual: Individual, n: Int) = Individual(
        (0 until genesSize).map { if (it < genesSize - n) individual.genes[it + n] else random.gene(it) }
    )

    override fun crossover(a: MutableList<G>, b: MutableList<G>, random: Random, child: (MutableList<G>) -> Unit) {
        val selector = crossoverSelector(genesSize, random)
        for (i in a.indices) if (selector(i)) {
            val (ga, gb) = crossoverOperator(a[i], b[i])
            a[i] = ga
            b[i] = gb
        }
        child(a)
        child(b)
    }

    override fun mutate(genes: MutableList<G>, random: Random) {
        val index = random.nextInt(genesSize)
        genes[index] = gene(random, index)
    }
}
