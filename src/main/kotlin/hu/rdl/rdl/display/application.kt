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
        val file = """"<l>
                <c:1/5:200px:1/10>card1</c>
                <c:2/5:200px>card1.2</c>
                <c:1/5:200px>card1.3</c>
                <c:100w:200px::10px>cardwide</c>
                </l>
               """
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
                val width = SizeCalculator.calculate(it.width)
                val height = SizeCalculator.calculate(it.height)
                val top = SizeCalculator.calculate(it.top)
                val left = SizeCalculator.calculate(it.left)

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

