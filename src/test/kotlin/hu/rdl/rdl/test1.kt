package hu.rdl.rdl

import hu.rdl.rdl.language.RDLParser

fun main(args: Array<String>) {
    //card parameter order: width,height:left:top
    val file = "<l>\n" +
            "<c:45%:20px:5%>card1</c>\n" +
            "<c:45%:20px:5%>card1.2</c>\n" +
            "<c:90%:20px:5%>cardwide/*comment*/</c>\n" +
            "</l>\n/*comment!*/"+
            "<l>\n" +
            "<c:100%:40px></c>\n" +
            "</l>"
    val parser = RDLParser(file)
    val document = parser.parse()
    println(document)
    
}