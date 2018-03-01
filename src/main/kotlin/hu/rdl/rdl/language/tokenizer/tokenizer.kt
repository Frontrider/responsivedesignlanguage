package hu.rdl.rdl.language.tokenizer

import hu.rdl.rdl.language.tagList
import hu.rdl.rdl.language.util.MultipleTokenException

fun tokenize(token: String) {

    val validTags = tagList.filter {
        it.isTagIsThis(token)
    }

    var (width, heigth, left, top) = arrayOf(0, 0, 0, 0)

    when {
        validTags.size == 1 -> {

        }
        validTags.isNotEmpty() -> {
            throw MultipleTokenException("Multiple options found for token $token!")
        }
        token == "/" -> {

        }
        token.startsWith("val-") -> {

        }
        else -> {

        }
    }
}