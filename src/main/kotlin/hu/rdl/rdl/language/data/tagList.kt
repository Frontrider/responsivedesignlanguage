package hu.rdl.rdl.language.data

import hu.rdl.rdl.language.Tag


private const val docname = "document"
val documentTag = Tag(name = docname,
        hasSize = false,
        hasContent = false,
        isTagIsThis = {
            it == docname || it == "d" || it == "doc"
        })
private const val unitname = "unit"
val unitTag = Tag(name = unitname,
        hasSize = true,
        hasContent = false,
        isTagIsThis = {
            it == unitname || it == "u"
        })
private const val layername = "layer"
val layerTag = Tag(name = "layer",
        hasSize = false,
        hasContent = false,
        isTagIsThis = {
            it == layername || it == "l"
        })
private const val contentname = "content"

val contentTag = Tag(name = contentname,
        hasSize = true,
        hasContent = true,
        isTagIsThis = {
            it == "c" || it == contentname || it == "cont"
        })

val defaultTags = listOf(documentTag, unitTag, layerTag, contentTag)