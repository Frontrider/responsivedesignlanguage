package hu.rdl.rdl.language.componentTests.sizetypetest

import hu.rdl.rdl.language.SizeType
import hu.rdl.rdl.language.data.sizeTypeFraction
import hu.rdl.rdl.language.data.sizeTypeHeight
import hu.rdl.rdl.language.data.sizeTypePixel
import hu.rdl.rdl.language.data.sizeTypeWidth
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolutionException
import org.junit.jupiter.api.extension.ParameterResolver

const val correctDataIterationTime = 3
const val inCorrectDataIterationTime = 19*4

class CorrectDataResolver : ParameterResolver {

    private val data = arrayOf(
            Pair(sizeTypePixel, "10px"),
            Pair(sizeTypeFraction, "2/3"),
            Pair(sizeTypeHeight, "10h"),
            Pair(sizeTypeWidth, "10w")
    ).toList().iterator()

    @Throws(ParameterResolutionException::class)
    override fun resolveParameter(parameterContext: ParameterContext,
                                  extensionContext: ExtensionContext): Any {
        return data.next()
    }

    @Throws(ParameterResolutionException::class)
    override fun supportsParameter(parameterContext: ParameterContext,
                                   extensionContext: ExtensionContext): Boolean {
        return parameterContext.parameter.type == Pair(sizeTypeFraction, "")::class.java
    }

}
class IncorrectDataResolver : ParameterResolver {
    private val data:Iterator<Pair<SizeType,String>>

    init {
        val incorrectData = arrayListOf(
                "10", "asd", "10g", "678qw", "1/", "/1", "asd/1", "asd/2",
                "asd/2px","asd/2w","asd/2h", "3/asdh","3/asd","3/asdw","3/asdpx",
                "asd/asd","asd/asdh","asd/asdw","asd/asdpx")
        val providers = arrayOf(sizeTypePixel, sizeTypeWidth, sizeTypeHeight, sizeTypeFraction)

        val result = ArrayList<Pair<SizeType,String>>()

        for(s in incorrectData)
            providers.mapTo(result) { Pair(it,s) }

        data = result.iterator()
    }


    override fun supportsParameter(parameterContext: ParameterContext, p1: ExtensionContext?): Boolean {
        return parameterContext.parameter.type == Pair(sizeTypeFraction, "")::class.java
    }

    override fun resolveParameter(parameterContext: ParameterContext, p1: ExtensionContext?): Any {

        return data.next()

    }
}