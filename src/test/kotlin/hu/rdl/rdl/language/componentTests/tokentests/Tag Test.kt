@file:Suppress("ClassName")

package hu.rdl.rdl.language.componentTests.tokentests

import hu.rdl.rdl.language.Tag
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertEquals

//no reason to test for incorrect information, would not be effective.
class `Tag Test` {

        @Test
        @ExtendWith(TagNameResolver::class)
        @RepeatedTest(correctDataIterationTime)
        fun `Tag is recognised`(input: Pair<Tag, Array<String>>) {
            val token = input.first
            val names = input.second
            for (name in names) {
                assert(token.isTagIsThis(name))
            }
        }

        @Test
        @ExtendWith(TagNameResolver::class)
        @RepeatedTest(correctDataIterationTime)
        fun `Tag prints the correct name`(input: Pair<Tag, Array<String>>) {
            val token = input.first
            val names = input.second

            assertEquals(names[0],token.name)
        }
        @Test
        @ExtendWith(HasContentResolver::class)
        @RepeatedTest(correctDataIterationTime)
        fun `Tag has content tag is correct`(input: Pair<Tag,Boolean>) {
            val token = input.first
            val hasContent = input.second

            assertEquals(hasContent,token.hasContent)
        }
        @Test
        @ExtendWith(HasSizeResolver::class)
        @RepeatedTest(correctDataIterationTime)
        fun `Tag has size tag is correct`(input: Pair<Tag,Boolean>) {
            val token = input.first
            val hasContent = input.second

            assertEquals(hasContent,token.hasSize)
        }

}