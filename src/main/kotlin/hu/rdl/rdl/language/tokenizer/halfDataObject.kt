package hu.rdl.rdl.language.tokenizer

import hu.rdl.rdl.language.DesignObject
import hu.rdl.rdl.language.SizeStorage
import hu.rdl.rdl.language.Tag
import hu.rdl.rdl.language.print

data class DesignObject(val width: SizeStorage,
                       val height: SizeStorage,
                       val left: SizeStorage,
                       val top: SizeStorage,
                       val tag: Tag,
                       val id: String = "",
                       val classes: List<String> = listOf())