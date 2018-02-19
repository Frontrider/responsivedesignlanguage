package hu.rdl.rdl.display

import hu.rdl.rdl.language.objects.DesignObject
import hu.rdl.rdl.language.parser.RDLParser
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
        val file =
                """
                <m:col-1-4:x100*:100w:200px>
                <m:col-1-4:m100*:100w:1/2>
                <m:col-1-4:m200*:100w:1/4>
                <l>
                <c:100px:200px:10px>card1</c>
                <c:100px:200px>card1.2</c>
                <c:100px:200px>card1.3</c>
                /*<c:100w:200px::10px>cardwide</c>*/
                </l>
                <l>
                <c:1/2:100px></c>
                <c:1/2:150px></c>
                </l>
                """
        val parser = RDLParser()
        val document = parser.parse(file)

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

        var layerCount = 0
        document.forEach {
            gc.stroke =
                    if (layerCount > 0)
                        Color.BLACK
                    else
                        Color.AQUA
            layerCount++
            var widthCache = 0.0
            var maxHeight = 0.0
            var heightCache = 0.0
            it.forEach {
                println("height cache before calculation: $heightCache")
                val positionData = PositionCalculator.calculate(it.height, it.width, it.left, it.top, widthCache, heightCache, maxHeight)
                widthCache = positionData.widthCache
                gc.strokeRect(positionData.x, positionData.y + heightCache, positionData.width, positionData.height)
                widthCache += positionData.width
                heightCache += positionData.heightCache
                println("height cache after calculation: $heightCache")
                maxHeight = positionData.maxHeight

            }
        }
    }
}

fun main(args: Array<String>) {
    Application.launch(DisplayDemo::class.java, *args)
}

