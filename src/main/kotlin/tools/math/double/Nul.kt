package tools.math.double

import kotlin.math.abs

var tolerance = 0.0000000000001
fun Double.isNul() = abs(this) < tolerance
fun Double.isNotNul() = !isNul()
