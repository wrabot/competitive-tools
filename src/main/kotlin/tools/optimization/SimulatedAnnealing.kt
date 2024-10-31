package tools.optimization

import kotlin.math.exp
import kotlin.random.Random

class SimulatedAnnealing<T : SimulatedAnnealing.State<T>>(
    private val random: Random = Random.Default,
    private val cooling: (Double) -> Double = { it * 0.99 },
) {
    interface State<T> {
        val energy: Double
        fun move(random: Random): T
    }

    fun run(initialState: T, initialTemperature: Double, count: Int): T {
        var currentState = initialState
        var bestState = initialState
        var temperature = initialTemperature
        repeat(count) {
            val nextState = currentState.move(random)
            if (nextState.energy == Double.NEGATIVE_INFINITY) return nextState
            val dE = nextState.energy - currentState.energy
            if (dE < 0 || random.nextDouble() < exp(-dE / temperature)) {
                currentState = nextState
                if (currentState.energy < bestState.energy) bestState = currentState
            }
            temperature = cooling(temperature)
        }
        return bestState
    }
}
