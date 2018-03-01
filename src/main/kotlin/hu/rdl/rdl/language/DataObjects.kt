package hu.rdl.rdl.language


//Implementation for state full tag storage

private typealias stringToBoolean = (String) -> Boolean

//lambdas to put functions into the descriptor
private typealias sizeToString = (value: Double) -> String

//should return null, if parsing failed
//returning null, means that the value does not fit into this type
private typealias parseNumber = (data: String) -> Double?

private typealias calculateSize = (Double, Double, Double) -> Double

data class Tag(val name: String,
               val hasSize: Boolean,
               val hasContent:Boolean,
               val isTagIsThis: stringToBoolean
)


//state full size storage method
//replaces the enum I used to store the size types
//can also handle the media type
data class SizeType(
        val marker: String,
        val toString: sizeToString,
        val parse: parseNumber,
        val calculateSize: calculateSize,
        val ifSizeIsThis: stringToBoolean
)