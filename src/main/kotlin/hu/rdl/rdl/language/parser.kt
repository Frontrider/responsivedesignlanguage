package hu.rdl.rdl.language

val layerPattern = Regex("<l>(.*?)</l>")
val cardPattern = Regex("<c:+(.*?)>(.*?)</c>")
val commentPattern = Regex("/\\*(.*?)\\*/")

class RDLParser(private val code: String) {
    fun parse(): List<List<DesignObject>> {

        val cleanCode = code.replace("\n", "").replace(commentPattern, "")

        val cards = ArrayList<List<DesignObject>>()
        layerPattern.findAll(cleanCode).iterator().forEach {
            var foundLayer = it.value
            foundLayer = foundLayer.replace("<l>", "")
            foundLayer = foundLayer.replace("</l>", "")
            cards.add(parseCards(foundLayer))
        }
        return cards
    }


    private fun parseCards(layer: String): ArrayList<DesignObject> {
        val result = ArrayList<DesignObject>()
        cardPattern.findAll(layer).forEach {
            var card = it.value

            card = card.replace("<c:", "")
            card = card.replace("</c>", "")
            val cardData = card.split(">")
            val properties = cardData[0].split(":")
            val width = if (properties.isNotEmpty())
                properties[0]
            else
                "25w"

            val height = if (properties.size > 1)
                properties[1]
            else
                "10px"

            val left = if (properties.size > 2)
                properties[2]
            else
                "0w"

            val top = if (properties.size > 3)
                properties[3]
            else
                "0w"

            val cardObject = DesignObject(width, height, left, top, cardData[1])
            result.add(cardObject)
        }

        return result
    }

}