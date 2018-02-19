package hu.rdl.rdl.language.parser

import hu.rdl.rdl.language.objects.DesignObject
import hu.rdl.rdl.language.objects.SizeStorage
import hu.rdl.rdl.language.objects.SizeTypeEnum
import hu.rdl.rdl.language.objects.TagEnum

private data class ContentHolder(val text: String = "", val designs: List<DesignObject>)

private val startOfTag = Regex("^\\<")
private val endOfTag = Regex("(\\<\\/\\>)\$")
private val delimiterMatcher = Regex("\\>")
private val dataDelimiter = Regex(":", RegexOption.DOT_MATCHES_ALL)

fun parseElement(obj: String): DesignObject {
    var card = obj
    card = card.replace(startOfTag, "")
    card = card.replace(endOfTag, "")
    val cardData = card.split(delimiterMatcher,2)
    val properties = cardData[0].split(dataDelimiter)

    val tagName = properties[0]

    val width = if (properties.size > 1)
        parseSize(properties[1])
    else
        SizeStorage()

    val height = if (properties.size > 2)
        parseSize(properties[2])
    else
        SizeStorage(10.0, SizeTypeEnum.PIXEL)

    val left = if (properties.size > 3)
        parseSize(properties[3])
    else
        SizeStorage(10.0, SizeTypeEnum.PIXEL)

    val top = if (properties.size > 4)
        parseSize(properties[4])
    else
        SizeStorage(0.0, SizeTypeEnum.PIXEL)

    val children = getChildren(cardData[1])

    return DesignObject(width, height, left, top, parseTag(tagName), children.text, children.designs)
}

private fun parseSize(size: String): SizeStorage {
    var result = 0.0
    var sizeType = SizeTypeEnum.PIXEL
    var mediaName = ""
    if (size.isNotEmpty()) {
        if (size[0] == '@') {
            mediaName = size.removePrefix("@")
            sizeType = SizeTypeEnum.MEDIA
        }

        if (size[size.length - 1].toLowerCase() == 'w' ||size[size.length - 1].toLowerCase() == '%') {
            result = size.removeRange(size.length - 1 until size.length).toDouble()
            sizeType = SizeTypeEnum.SCREEN_WIDTH
        }
        if (size[size.length - 1].toLowerCase() == 'h') {
            result = size.removeRange(size.length - 1 until size.length).toDouble()
            sizeType = SizeTypeEnum.SCREEN_HEIGHT
        }
        if (size.length > 3) {
            if (size[size.length - 2].toString() + size[size.length - 1].toLowerCase() == "px") {
                result = size.removeRange(size.length - 2 until size.length).toDouble()
                sizeType = SizeTypeEnum.PIXEL
            }
        }
        if (size.length > 2) {
            if (size.contains("/")) {
                if ((size.length - size.replace("/", "").length) == 1) {
                    val fractionData = size.split("/")
                    result = fractionData[0].toDouble() / fractionData[1].toDouble()
                    sizeType = SizeTypeEnum.COLUMN
                }
            }
        }
    }
    return SizeStorage(result, sizeType, mediaName)
}

//not a stateless part of it!
private fun parseTag(tagName: String): TagEnum {
    return when (tagName) {
        "c", "content" -> TagEnum.CONTENT
        "l", "layer" -> TagEnum.LAYER
        "u", "unit" -> TagEnum.UNIT
        "doc"->TagEnum.DOCUMENT
        else -> TagEnum.UNIT
    }
}

private val tagPattern = Regex("<[a-zA-Z]:+(.*?)>(.*?)</>")
private fun getChildren(script: String): ContentHolder {
    var text = ""
    val designs = tagPattern.findAll(script).map {
        parseElement(it.value)
    }.toMutableList()
    if (designs.isEmpty()) {
        text = script
    }

    return ContentHolder(text, designs)
}


