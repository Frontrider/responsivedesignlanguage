package hu.rdl.rdl.display

data class PositionData(
        val x: Double,
        val y: Double,
        val width: Double,
        val height: Double,
        val widthCache: Double = 0.0,
        val heightCache: Double = 0.0,
        val maxHeight: Double = 0.0
)