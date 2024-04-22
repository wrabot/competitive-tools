package tools.ai

import tools.log
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals

class Genetic {
    private data class Individual(val text: String) : GAIndividual<Individual> {
        constructor(random: Random) : this(String(CharArray(TARGET.length) { char(random) }))

        override val fitness = text.zip(TARGET) { a, b -> a == b }.count { it }.toFloat()
        override fun mutate(random: Random) =
            Individual(StringBuilder(text).apply {
                setCharAt(random.nextInt(TARGET.length), char(random))
            }.toString())

        override fun crossover(other: Individual, random: Random) =
            text.zip(other.text) { a, b -> if (random.nextBoolean()) a to b else b to a }.unzip().toList().map {
                Individual(it.joinToString(""))
            }

        companion object {
            private fun char(random: Random) = 'a' + random.nextInt(26)
            const val TARGET = "target"
        }
    }

    @Test
    fun testGenetic() {
        val target = List(100) { Individual(Random.Default) }.evolve(100).first().text
        assertEquals(Individual.TARGET, target)
    }
}


