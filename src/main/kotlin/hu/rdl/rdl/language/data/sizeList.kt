package hu.rdl.rdl.language.data

import hu.rdl.rdl.language.SizeType


//this is the default implementation
val sizeTypePixel = SizeType(
        marker = "px",
        parse = {
            when {
                it.length < 2 -> null
                it[it.length - 2].toString() + it[it.length - 1] == "px" -> it.removeRange(it.length - 2 until it.length).toDoubleOrNull()
                else -> null
            }
        },
        calculateSize = { _, _, value ->
            value
        },
        ifSizeIsThis = {
            "${it[it.length - 2]}${it[it.length - 1]}" == "px"
                    && it.removeRange(it.length-2 until it.length).toDoubleOrNull() != null
        },
        toString = {
            it.toString() + "px"
        }
)


val sizeTypeWidth = SizeType(
        marker = "w",
        toString = {
            it.toString()+"w"
        },
        parse = {
            if (it.length < 2 || it.last() != 'w')
                null
            else
                it.removeRange(it.length - 1 until it.length).toDoubleOrNull()
        },
        calculateSize = { width, _, value ->
            width / 100 * value
        },
        ifSizeIsThis = {
            it[it.lastIndex] == 'w'
                    && it.removeRange(it.length-1 until it.length).toDoubleOrNull() != null
        }

)
val sizeTypeHeight = SizeType(
        marker = "h",
        toString = {
          it.toString()+"h"
        },
        parse = {
            if (it.length < 2 || it.last() != 'h')
                null
            else
                it.removeRange(it.length - 1 until it.length).toDoubleOrNull()
        },
        calculateSize = { _, height, value ->
            height / 100 * value
        },
        ifSizeIsThis = {
            it[it.lastIndex] == 'h'
                    && it.removeRange(it.length-1 until it.length).toDoubleOrNull() != null
        }
)
val sizeTypeFraction = SizeType(
        marker = "/",
        toString = {
            it.toString() + "/100"
        },
        parse = {
            if (it.length < 3)
                null
            else {
                val data = it.split("/")
                try {
                    (data[0].toDouble() / data[1].toDouble()) * 100
                } catch (e: Exception) {
                    null
                }
            }
        },
        calculateSize = { _, height, value ->
            height / 100 * value
        },
        ifSizeIsThis = {
            val split = it.split("/")

            split.size == 2
                    && split[0].toDoubleOrNull() != null && split[0].isNotEmpty()
                    && split[1].toDoubleOrNull() != null && split[1].isNotEmpty()
        }
)
