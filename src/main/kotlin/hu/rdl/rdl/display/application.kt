package hu.rdl.rdl.display

import hu.rdl.rdl.language.DesignObject
import hu.rdl.rdl.language.RDLParser
import javafx.application.Application
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.scene.paint.Color
import javafx.stage.Stage

var screenWidth = 300.0
var screenHeight = 250.0

class DisplayDemo : Application() {

    override fun start(primaryStage: Stage) {
        val file = "<l>\n" +
                "<c:100px:200px>card1</c>\n" +
                "<c:100px:200px>card1.2</c>\n" +
                "<c:100px:200px>card1.3</c>\n" +
                "<c:100w:200px::10px>cardwide</c>\n" +
                "</l>\n"
        val parser = RDLParser(file)
        val document = parser.parse()

        primaryStage.title = "RDL display test"
        val root = Group()
        val canvas = Canvas(screenWidth, screenHeight)
        val gc = canvas.graphicsContext2D
        gc.stroke = Color.BLACK
        gc.fill = Color.BEIGE
        gc.stroke()
        gc.lineWidth = 3.0
        drawCards(document, gc)
        root.children.add(canvas)
        primaryStage.scene = Scene(root)

        primaryStage.widthProperty().addListener({ _, _, newValue ->
            run {
                screenWidth = newValue as Double
                canvas.width = screenWidth
                drawCards(document, gc)
            }
        })
        primaryStage.heightProperty().addListener({ _, _, newValue ->
            run {
                screenHeight = newValue as Double
                canvas.height = screenHeight
                drawCards(document, gc)
            }
        })

        primaryStage.show()
    }


    private fun drawCards(document: List<List<DesignObject>>, gc: GraphicsContext) {
        gc.clearRect(0.0, 0.0, screenWidth, screenHeight)
        var widthCache = 0.0
        var heightCache = 0.0
        var maxHeight = 0.0
        document.forEach {
            it.forEach {
                var width = 0.0
                var height = 0.0
                var top = 0.0
                var left = 0.0

                if (it.width.isNotEmpty()) {
                    if (it.width.length > 3)
                        if (it.width[it.width.length - 2].toString() + it.width[it.width.length - 1] == "px") {
                            val widthData = it.width.removeRange(it.width.length - 2 until it.width.length).toDouble()
                            width = widthData
                        }
                    if (width == 0.0 && it.width[it.width.length - 1] == 'w') {
                        val widthData = it.width.removeRange(it.width.length - 1 until it.width.length).toDouble()
                        width = screenWidth / 100 * widthData
                    }


                }
                if (it.left.isNotEmpty()) {
                    if (it.left.length > 2)
                        if (it.left[it.left.length - 2].toString() + it.left[it.left.length - 1] == "px") {
                            val leftData = it.left.removeRange(it.left.length - 2 until it.left.length)
                            left = leftData.toDouble()
                        }
                    if (left == 0.0 && it.left[it.left.length - 1] == 'w') {
                        val leftData = it.left.removeRange(it.left.length - 1 until it.left.length)
                        left = screenWidth / 100 * leftData.toDouble()
                    }
                }
                if (it.height.isNotEmpty()) {

                    if (it.height.length > 3)
                        if (it.height[it.height.length - 2].toString() + it.height[it.height.length - 1] == "px") {
                            val heightData = it.height.removeRange(it.height.length - 2 until it.height.length).toDouble()
                            height = heightData
                        }
                    if (height == 0.0 && it.height[it.height.length - 1] == 'w') {
                        val heightData = it.height.removeRange(it.height.length - 1 until it.height.length)
                        height = screenWidth / 100 * heightData.toDouble()
                    }
                }

                if (it.top.isNotEmpty()) {
                    if (it.top.length > 2)
                        if (it.top[it.top.length - 2].toString() + it.top[it.top.length - 1] == "px") {
                            val topData = it.top.removeRange(it.top.length - 2 until it.top.length).toDouble()
                            top = topData
                        }
                }


                if (maxHeight < height)
                    maxHeight = height

                if (widthCache + width > screenWidth) {
                    heightCache += maxHeight
                    widthCache = 0.0
                    maxHeight = 0.0
                }
                gc.strokeRect(widthCache + left, heightCache + top, width, height)
                widthCache += width + left

            }
        }
    }
}

fun main(args: Array<String>) {
    Application.launch(DisplayDemo::class.java, *args)
}

