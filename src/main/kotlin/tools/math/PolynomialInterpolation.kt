package tools.math

data class PolynomialInterpolation(val values: List<Double>) {
    constructor(vararg values: Double) : this(values.toList())

    operator fun invoke(x: Double) = coefficients.foldRightIndexed(0.0) { i, c, acc -> acc * (x + i) / (i + 1) + c }

    private val coefficients = values.coefficients().reversed()
    private fun List<Double>.coefficients(): List<Double> =
        if (all { it == 0.0 }) emptyList() else zipWithNext().map { it.second - it.first }.coefficients() + last()
}
