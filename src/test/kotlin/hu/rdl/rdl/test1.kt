package hu.rdl.rdl

import hu.rdl.rdl.language.objects.print
import hu.rdl.rdl.language.parser.RDLParser

fun main(args: Array<String>) {
    //card parameter order: width,height:left:top
    val file = "<doc>\n" +
            "<l>\n" +
            "<c:45%:20px:5%>card1</>\n" +
            "<c:45%:20px:5%>card1.2</>\n" +
            "<c:90%:20px:5%>cardwide/*comment*/</>\n" +
            "</>\n/*comment!*/"+
            "<l>\n" +
            "<c:100%:40px></>\n" +
            "</></>"
    val parser = RDLParser()
    val document = parser.parse(file)
    println(document.print())
}
