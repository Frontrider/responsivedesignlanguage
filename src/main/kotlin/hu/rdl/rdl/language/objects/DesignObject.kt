package hu.rdl.rdl.language.objects

import hu.rdl.rdl.language.parser.percentToFraction

data class DesignObject(val width: SizeStorage,
                        val height: SizeStorage,
                        val left: SizeStorage,
                        val top: SizeStorage,
                        val tag: TagEnum,
                        val text: String = "",
                        val children: List<DesignObject> = listOf()) {
    override fun toString(): String {
        return "<$tag:$width:$height:$left:$top>${children.print()}</>\n"
    }
}

//extension for the list containing the child elements, so we can print it properly
fun <DesignObject> List<DesignObject>.print(): String {
    var result = ""
    this.forEach{
        result += it.toString()
    }
    return result
}

data class MediaObject(
        val name:String,
        val width: SizeStorage,
        val height: SizeStorage,
        val left: SizeStorage,
        val top: SizeStorage,
        val widthParameter: Int,
        val heightParameter: Int) {
    override fun toString(): String {
        return "<m:$name:${widthParameter}x$heightParameter:$width:$height:$left:$top>"
    }
}

data class SizeStorage(val value: Double=0.0, val type: SizeTypeEnum =SizeTypeEnum.PIXEL, val mediaName: String = "") {
    override fun toString(): String {
            return when(type)
            {
                SizeTypeEnum.MEDIA->{
                    "@$mediaName"
                }
                SizeTypeEnum.COLUMN->{
                    val fraction = percentToFraction(value)
                     "${fraction.first}/${fraction.second}"
                }
                else ->"$value$type"
            }
    }
}

enum class SizeTypeEnum {
    PIXEL, SCREEN_WIDTH, SCREEN_HEIGHT, COLUMN, MEDIA;

    override fun toString(): String {
        return when (ordinal) {
            PIXEL.ordinal -> "px"
            SCREEN_WIDTH.ordinal -> "w"
            SCREEN_HEIGHT.ordinal -> "h"
            COLUMN.ordinal -> "/"
            MEDIA.ordinal -> "@"
            else -> "px"
        }
    }
}

//Not a stateless element!
enum class TagEnum {
    CONTENT,
    LAYER,
    DOCUMENT,
    UNIT;

    override fun toString(): String {

        return when (this.ordinal) {
            CONTENT.ordinal -> "content"
            UNIT.ordinal -> "unit"
            LAYER.ordinal -> "layer"
            DOCUMENT.ordinal->"document"
            else -> "content"
        }
    }
}