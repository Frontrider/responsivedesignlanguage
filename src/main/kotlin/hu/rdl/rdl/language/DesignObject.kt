package hu.rdl.rdl.language


data class DesignObject(val width: SizeStorage,
                        val height: SizeStorage,
                        val left: SizeStorage,
                        val top: SizeStorage,
                        val tag: Tag,
                        val text: String = "",
                        val children: List<DesignObject> = listOf(),
                        val id: String = "",
                        val classes: List<String> = listOf()) {
    override fun toString(): String {
        return if (!tag.hasSize)
            "<${tag.name}>${children.print()}\n</${tag.name}>\n"
        else
            "<${tag.name}:width=$width:height=$height:left=$left:top=$top>${children.print()}\n</${tag.name}>\n"
    }
}

//extension for the list containing the child elements, so we can print it properly
fun <DesignObject> List<DesignObject>.print(): String {
    var result = ""
    this.forEach {
        result += it.toString()
    }
    return result
}

data class MediaObject(
        val name: String = "",
        val width: SizeStorage,
        val height: SizeStorage,
        val left: SizeStorage,
        val top: SizeStorage,
        val widthParameter: Int = 0,
        val heightParameter: Int = 0) {
    override fun toString(): String {
        return "<m:$name:${widthParameter}x$heightParameter:$width:$height:$left:$top>"
    }
}

data class SizeStorage(val value: Double,
                       val type: SizeType
) {
    override fun toString(): String {
        return type.toString.invoke(value)
    }
}