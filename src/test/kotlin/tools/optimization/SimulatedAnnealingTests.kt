package tools.optimization

import tools.Point
import java.util.*
import kotlin.math.sqrt
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals

class SimulatedAnnealingTests {
    private val towns = listOf(
        Point(565.0, 575.0),
        Point(25.0, 185.0),
        Point(345.0, 750.0),
        Point(945.0, 685.0),
        Point(845.0, 655.0),
        Point(880.0, 660.0),
    )

    private val distances = 
        List(towns.size) { a -> DoubleArray(towns.size) { b -> sqrt((towns[a] - towns[b]).norm2()) } }

    inner class State(private val path: List<Int>) : SimulatedAnnealing.State<State> {
        override val energy: Double = 
            distances[0][path.first()] + path.zipWithNext { a, b -> distances[a][b] }.sum() + distances[path.last()][0]

        override fun move(random: Random) = State(path.toMutableList().apply {
            Collections.swap(this, random.nextInt(path.size), random.nextInt(path.size))
        })
    }

    @Test
    fun testValue() {
        val random = Random(0)
        val simulatedAnnealing = SimulatedAnnealing<State>(random = random)
        val state = simulatedAnnealing.run(State(towns.indices.shuffled(random)), 10.0, 100)
        assertEquals(2315.146912868563, state.energy, 0.000000001)
    }
}
