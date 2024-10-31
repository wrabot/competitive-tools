package tools.optimization

import tools.Point
import kotlin.math.sqrt
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals

class SimulatedAnnealingTests {
    val towns = listOf(
        Point(565.0, 575.0),
        Point(25.0, 185.0),
        Point(345.0, 750.0),
        Point(945.0, 685.0),
        Point(845.0, 655.0),
        Point(880.0, 660.0),
    )

    private val distances = towns.indices.associateWith { i ->
        towns.indices.associateWith { j ->
            sqrt((towns[i] - towns[j]).norm2())
        }
    }

    inner class State(val towns: List<Int>) : SimulatedAnnealing.State<State> {
        override val energy: Double = (towns + towns.first()).zipWithNext { a, b -> distances[a]!![b]!! }.sum()

        override fun move(random: Random): State {
            val i = random.nextInt(1, towns.size - 1)
            val j = random.nextInt(i + 1, towns.size)
            return State(towns.subList(0, i) + towns[j] + towns.subList(i + 1, j) + towns[i] + towns.subList(j + 1, towns.size))
        }
    }

    @Test
    fun testValue() {
        val random = Random(0)
        val simulatedAnnealing = SimulatedAnnealing<State>(random = random)
        val state = simulatedAnnealing.run(State(towns.indices.shuffled(random)), 10.0, 100)
        assertEquals(2315.146912868563, state.energy, 0.000000001)
    }
}
