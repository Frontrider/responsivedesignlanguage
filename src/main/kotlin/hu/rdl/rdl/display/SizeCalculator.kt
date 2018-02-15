package hu.rdl.rdl.display

object SizeCalculator {

    fun calculate(value: String): Double {
        var result = 0.0
        if (value.isNotEmpty()) {
            if (value[value.length - 1].toLowerCase() == 'w') {
                val sizeValue = value.removeRange(value.length - 1 until value.length).toDouble()
                result = screenWidth / 100 * sizeValue
            }
            if(result != 0.0)
                return result
            if (value[value.length - 1].toLowerCase() == 'h') {
                val sizeValue = value.removeRange(value.length - 1 until value.length).toDouble()
                result = screenHeight / 100 * sizeValue
            }
            if(result != 0.0)
                return result
            if (value.length > 3) {
                if (value[value.length - 2].toString() + value[value.length - 1].toLowerCase() == "px") {
                    val sizeValue = value.removeRange(value.length - 2 until value.length).toDouble()
                    result = sizeValue
                }
            }
            if(result != 0.0)
                return result
            if (value.length > 2) {
                if (value.contains("/")) {
                    if ((value.length - value.replace("/", "").length) == 1) {
                        val fractionData = value.split("/")
                        val singleUnit = screenWidth / fractionData[1].toDouble()
                        result = singleUnit * fractionData[0].toDouble()
                    }
                }
            }
        }
        return result
    }
}