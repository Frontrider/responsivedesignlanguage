package hu.rdl.rdl.language

import hu.rdl.rdl.language.data.defaultTags
import hu.rdl.rdl.language.tokenizer.tokenize

private val commentRegex = Regex("/\\*(.*?)\\*/")
private val contentRegex = Regex("[^/]>(.*?)</")

internal val contentContainer = HashMap<Int, String>()
private var contentContainerIndex = 0

lateinit var tagList: List<Tag>

fun start(code: String, tags: List<Tag> = defaultTags) {
    contentContainer.clear()
    contentContainerIndex = 0
    var cleanCode = code
            .replace(commentRegex, "")

    contentRegex.findAll(cleanCode).forEach {
        val content = it.value.removeSuffix("</").removeRange(0..1)
        if (content.isNotEmpty()) {
            contentContainer[contentContainerIndex] = content
            contentContainerIndex++
            val contentIndex = cleanCode.indexOf(content)
            cleanCode = cleanCode.replaceRange(contentIndex..contentIndex + content.length, "val-$contentContainerIndex")
        }
    }

    println()
    cleanCode = cleanCode
            .replace("\n", "")
            .replace("\t", "")
            .replace(" ", "")
            //breaking it into lines
            .replace(Regex(">[ ]+<|><|:"), "\n")
            //breaking it along tag ends
            .replace('>', '\n')
            .substring(1)

    tagList = tags
    println(cleanCode)
    val tokens = cleanCode.split("\n")
    parse(tokens)
}


private var index = 0
fun parse(code: List<String>) {
    mainLoop@ while (code.size > index) {
        tokenize(code[index])
        index++
    }
}



