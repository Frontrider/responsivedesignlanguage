package hu.rdl.rdl.language.parserTest

import hu.rdl.rdl.language.data.contentTag
import hu.rdl.rdl.language.data.documentTag
import hu.rdl.rdl.language.data.layerTag
import hu.rdl.rdl.language.data.unitTag
import hu.rdl.rdl.language.start

fun main(args: Array<String>) {

    val defaultTags = listOf(documentTag, unitTag, layerTag, contentTag)
    start(file,defaultTags)
}