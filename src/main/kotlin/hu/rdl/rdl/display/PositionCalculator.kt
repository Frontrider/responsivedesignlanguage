package hu.rdl.rdl.display

import hu.rdl.rdl.language.objects.SizeStorage

object PositionCalculator {

    fun calculate(height: SizeStorage, width: SizeStorage, left: SizeStorage, top: String, widthCache: Double, heightCache: Double, maxHeight: Double): PositionData {
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
