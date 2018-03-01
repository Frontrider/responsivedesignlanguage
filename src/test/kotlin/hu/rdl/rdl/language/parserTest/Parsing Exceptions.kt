@file:Suppress("ClassName")

package hu.rdl.rdl.language.parserTest

import hu.rdl.rdl.language.data.contentTag
import hu.rdl.rdl.language.data.documentTag
import hu.rdl.rdl.language.data.layerTag
import hu.rdl.rdl.language.data.unitTag
import hu.rdl.rdl.language.start
import hu.rdl.rdl.language.util.MultipleTokenException
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class `Parsing Exceptions` {

    @Test
    fun `Multiple tokens of the same signature registered`() {
        val defaultTags = listOf(documentTag, unitTag, layerTag, contentTag, documentTag)
        assertThrows(MultipleTokenException::class.java,{
            start(file,defaultTags)
        })
    }
}