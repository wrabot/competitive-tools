package tools.ai

import tools.ai.common.AbstractGenetic
import tools.range.size
import java.util.*
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

class PermutationGenetic(
    private val geneRange: IntRange,
    random: Random = Random.Default,
    fitness: (List<Int>) -> Float,
) : AbstractGenetic<Int>(geneRange.size, random, fitness) {
    fun individual() = Individual(geneRange.shuffled(random))

    override fun crossover(a: MutableList<Int>, b: MutableList<Int>, random: Random, child: (MutableList<Int>) -> Unit) {
        val i = random.nextInt(genesSize)
        val j = random.nextInt(genesSize)
        val range = min(i, j)..max(i, j)
        val aMiddle = b.filter { a.indexOf(it) in range }
        val bMiddle = a.filter { b.indexOf(it) in range }
        range.forEach {
            a[it] = aMiddle[it - range.first]
            b[it] = bMiddle[it - range.first]
        }
        child(a)
        child(b)
    }

    override fun mutate(genes: MutableList<Int>, random: Random) {
        Collections.swap(genes, random.nextInt(genes.size), random.nextInt(genes.size))
    }
}
