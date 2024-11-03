package tools.ai

import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

enum class CrossoverSelector(val selector: (Int, Random) -> IndependentGenetic.Selector) {
    Mono({ size, random ->
        val index = random.nextInt(size)
        IndependentGenetic.Selector { it < index }
    }),
    Multi({ size, random ->
        val i = random.nextInt(size)
        val j = random.nextInt(size)
        val range = min(i, j)..max(i, j)
        IndependentGenetic.Selector { it in range }
    }),
    Uniform({ _, random -> IndependentGenetic.Selector { random.nextBoolean() } })
}
