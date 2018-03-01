@file:Suppress("ClassName", "SimplifyAssertNotNull")


package hu.rdl.rdl.language.componentTests.sizetypetest

import hu.rdl.rdl.language.SizeType
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertFalse
import kotlin.test.assertNull

class `Size Tests` {

    @Nested
    inner class `Inputs are correct` {

        @Test
        @ExtendWith(CorrectDataResolver::class)
        @RepeatedTest(correctDataIterationTime)
        fun `result is number`(input: Pair<SizeType, String>) {
            val sizeType: SizeType = input.first
            val value: String = input.second
            val result = sizeType.parse.invoke(value)

            assert(result is Number)
        }

        @Test
        @ExtendWith(CorrectDataResolver::class)
        @RepeatedTest(correctDataIterationTime)
        fun `result is not null`(input: Pair<SizeType, String>) {
            val sizeType: SizeType = input.first
            val value: String = input.second
            val result = sizeType.parse.invoke(value)
            assert(result != null)
        }

        @Test
        @ExtendWith(CorrectDataResolver::class)
        @RepeatedTest(correctDataIterationTime)
        fun `type detection returns true`(input: Pair<SizeType, String>) {
            val sizeType: SizeType = input.first
            val value: String = input.second
            val result1 = sizeType.ifSizeIsThis(value)
            assert(result1)
        }
    }

    @Nested
    inner class `Inputs are incorrect` {
        @Test
        @ExtendWith(IncorrectDataResolver::class)
        @RepeatedTest(inCorrectDataIterationTime)
        fun `result is null`(input: Pair<SizeType, String>) {
            val sizeType: SizeType = input.first
            val value: String = input.second
            val result = sizeType.parse.invoke(value)
            assertNull(result,"${sizeType.marker},$value = $result")
        }

        @Test
        @ExtendWith(IncorrectDataResolver::class)
        @RepeatedTest(inCorrectDataIterationTime)
        fun `type detection returns false`(input: Pair<SizeType, String>) {
            val sizeType: SizeType = input.first
            val value: String = input.second
            val result = sizeType.ifSizeIsThis(value)

            assertFalse(result,"${sizeType.marker},$value = $result")
        }
    }
}
