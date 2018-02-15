package hu.rdl.rdl.display

object PositionCalculator {

    fun calculate(height: String, width: String, left: String, top: String, widthCache: Double, heightCache: Double, maxHeight: Double): PositionData {
        val widthValue = SizeCalculator.calculate(width)
        val heightValue = SizeCalculator.calculate(height)
        val topValue = SizeCalculator.calculate(top)
        val leftValue = SizeCalculator.calculate(left)
        var localWidthCache = widthCache
        var localHeightCache = heightCache
        var localMaxHeight = maxHeight
        if (maxHeight < heightValue)
            localMaxHeight = heightValue

        localWidthCache += leftValue

        println("position calculator height cache: $localHeightCache")
        if (localWidthCache + widthValue > screenWidth) {
            localHeightCache += maxHeight
            localWidthCache = 0.0
            localMaxHeight = 0.0
        }

        return PositionData(
                localWidthCache,
                localHeightCache + topValue,
                widthValue,
                heightValue,
                localWidthCache,
                localHeightCache,
                localMaxHeight
        )
    }
}
