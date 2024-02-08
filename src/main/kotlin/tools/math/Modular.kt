package tools.math

import java.math.BigInteger

val Long.bi get() = toBigInteger()
val Int.bi get() = toBigInteger()

data class Modular(val modulus: BigInteger) {
    operator fun BigInteger.plus(rhs: BigInteger): BigInteger = add(rhs).mod(modulus)
    operator fun BigInteger.minus(rhs: BigInteger): BigInteger = subtract(rhs).mod(modulus)
    operator fun BigInteger.times(rhs: BigInteger): BigInteger = multiply(rhs).mod(modulus)
    operator fun BigInteger.div(rhs: BigInteger): BigInteger = this * rhs.modInverse(modulus)
    fun BigInteger.pow(rhs: BigInteger): BigInteger = modPow(rhs, modulus)
}

fun chineseRemainder(input: List<Pair<BigInteger, BigInteger>>): BigInteger {
    val modular = Modular(input.fold(BigInteger.ONE) { acc, i -> acc * i.second })
    return input.fold(BigInteger.ZERO) { acc, (v, n) ->
        val nc = modular.modulus / n
        modular.run { acc + v * nc.modInverse(n) * nc }
    }
}
