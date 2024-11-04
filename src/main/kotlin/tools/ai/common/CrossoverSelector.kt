package tools.ai.common

import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

enum class CrossoverSelector(private val selector: (Int, Random) -> List<Int>) {
    Mono({ size, random -> (0..random.nextInt(size)).toList() }),
    Multi({ size, random ->
        val i = random.nextInt(size)
        val j = random.nextInt(size)
        (min(i, j)..max(i, j)).toList()
    }),
    Uniform({ size, random -> (0 until size).filter { random.nextBoolean() } });

    operator fun invoke(size: Int, random: Random) = selector(size, random)
}
