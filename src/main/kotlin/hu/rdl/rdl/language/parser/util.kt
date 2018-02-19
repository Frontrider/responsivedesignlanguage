package hu.rdl.rdl.language.parser

/**
 * @param percent can not be infinite!
 * @return a pair of int
 * @throws ArithmeticException when the input is not a valid percentage
 * */
fun percentToFraction(percent: Double): Pair<Int, Int> {
    if (percent > 100)
        throw ArithmeticException("The percentage can not be greater than a 100!")
    if (percent < 0)
        throw ArithmeticException("The percentage can not be lower than 0!")
    var numerator = percent
    var denumerator = 100

    while (numerator != numerator.toInt().toDouble()) {
        numerator *= 10
        denumerator *= 10
    }
    var numeratorInt = numerator.toInt()

    var divider = 1

    while (divider != 0) {
        divider = smallestDivider(numeratorInt, denumerator)
        numeratorInt /= divider
        denumerator /= divider
    }

    return Pair(numeratorInt, denumerator)
}

private fun smallestDivider(p: Int, q: Int): Int {
    return if (q == 0)
        p
    else
        smallestDivider(q, p % q)
}