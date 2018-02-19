package hu.rdl.rdl.language.parser

import hu.rdl.rdl.language.objects.DesignObject
import hu.rdl.rdl.language.objects.MediaObject

val commentPattern = Regex("/\\*(.*?)\\*/")
val mediaPattern = Regex("<m:+(.*?)>")
val mediaNameFilterRegex = Regex("")

class RDLParser {

    private val mediaQuarry = HashMap<String, MediaObject>()
    fun parse(code: String): List<DesignObject> {
        val cards = ArrayList<DesignObject>()
        val cleanCode = code.replace("\n", "").replace(commentPattern, "")

        mediaQuarry.clear()
        println(cleanCode)
        println(code + "\n")
        val design = parseElement(cleanCode)
        cards.add(design)

        return cards
    }


}