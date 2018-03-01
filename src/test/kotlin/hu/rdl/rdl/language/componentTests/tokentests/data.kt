package hu.rdl.rdl.language.componentTests.tokentests

import hu.rdl.rdl.language.data.*
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolutionException
import org.junit.jupiter.api.extension.ParameterResolver

const val correctDataIterationTime = 3

class TagNameResolver : ParameterResolver {

    private val data = arrayOf(
            Pair(documentTag, arrayOf("document","doc")),
            Pair(unitTag, arrayOf("unit","u")),
            Pair(contentTag, arrayOf("content","c","cont")),
            Pair(layerTag, arrayOf("layer","l"))
    ).toList().iterator()

    @Throws(ParameterResolutionException::class)
    override fun resolveParameter(parameterContext: ParameterContext,
                                  extensionContext: ExtensionContext): Any {
        return data.next()
    }

    @Throws(ParameterResolutionException::class)
    override fun supportsParameter(parameterContext: ParameterContext,
                                   extensionContext: ExtensionContext): Boolean {
        return parameterContext.parameter.type ==  Pair(layerTag, arrayOf("l","layer"))::class.java
    }
}

class HasContentResolver : ParameterResolver {

    private val data = arrayOf(
            Pair(documentTag, false),
            Pair(unitTag, false),
            Pair(contentTag, true),
            Pair(layerTag, false)
    ).toList().iterator()

    @Throws(ParameterResolutionException::class)
    override fun resolveParameter(parameterContext: ParameterContext,
                                  extensionContext: ExtensionContext): Any {
        return data.next()
    }

    @Throws(ParameterResolutionException::class)
    override fun supportsParameter(parameterContext: ParameterContext,
                                   extensionContext: ExtensionContext): Boolean {
        return parameterContext.parameter.type ==  Pair(layerTag, arrayOf("l","layer"))::class.java
    }
}

class HasSizeResolver : ParameterResolver {

    private val data = arrayOf(
            Pair(documentTag, false),
            Pair(unitTag, true),
            Pair(contentTag, true),
            Pair(layerTag, false)
    ).toList().iterator()

    @Throws(ParameterResolutionException::class)
    override fun resolveParameter(parameterContext: ParameterContext,
                                  extensionContext: ExtensionContext): Any {
        return data.next()
    }

    @Throws(ParameterResolutionException::class)
    override fun supportsParameter(parameterContext: ParameterContext,
                                   extensionContext: ExtensionContext): Boolean {
        return parameterContext.parameter.type ==  Pair(layerTag, arrayOf("l","layer"))::class.java
    }
}
