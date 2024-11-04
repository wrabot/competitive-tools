package tools.ai

import tools.ai.common.AbstractGenetic
import tools.ai.common.CrossoverSelector
import tools.range.size
import java.util.*
import kotlin.random.Random

class PermutationGenetic(
    private val geneRange: IntRange,
    val crossoverSelector: CrossoverSelector = CrossoverSelector.Multi,
    random: Random = Random.Default,
    fitness: (List<Int>) -> Float,
) : AbstractGenetic<Int>(geneRange.size, random, fitness) {
    override fun individual() = Individual(geneRange.shuffled(random))

    override fun crossover(a: MutableList<Int>, b: MutableList<Int>, random: Random, child: (MutableList<Int>) -> Unit) {
        val selected = crossoverSelector(genesSize, random)
        val aMiddle = b.filter { a.indexOf(it) in selected }
        val bMiddle = a.filter { b.indexOf(it) in selected }
        selected.forEachIndexed { index, i ->
            a[i] = aMiddle[index]
            b[i] = bMiddle[index]
        }
        child(a)
        child(b)
    }

    override fun mutate(genes: MutableList<Int>, random: Random) {
        Collections.swap(genes, random.nextInt(genes.size), random.nextInt(genes.size))
    }
}
