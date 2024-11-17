package tools.math.double

import java.util.*

fun Double.toString(decimals : Int) = "%.${decimals}f".format(Locale.US,this)
