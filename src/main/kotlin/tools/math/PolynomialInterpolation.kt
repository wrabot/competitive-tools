package tools.math

class PolynomialInterpolation(vararg values: Double) {
    private val coefficients = values.toList().coefficients().reversed()

    operator fun invoke(x: Double) = coefficients.foldRightIndexed(0.0) { i, c, acc -> acc * (x + i) / (i + 1) + c }

    private fun List<Double>.coefficients(): List<Double> =
        if (all { it == 0.0 }) emptyList() else zipWithNext().map { it.second - it.first }.coefficients() + last()
}
