package com.fachrizalmrsln.githubuserapp.utils.data

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class DataUnitTest {

    @Test
    fun chuckedList_isCorrect() {
        val mGivenData1A = listOf("a", "b", "c", "d", "e", "f", "g")
        val mGivenData1B = 2
        val mExpectedData1 = "[[a, b], [c, d], [e, f], [g]]"

        val mResult1 = mGivenData1A.chunkedList(mGivenData1B)
        assertEquals(mExpectedData1, mResult1.toString())
        assertEquals(4, mResult1.size)

        val mGivenData2A = listOf("a", "b", "c", "d", "e", "f", "g")
        val mGivenData2B = 10
        val mExpectedData2 = "[[a, b, c, d, e, f, g]]"

        val mResult2 = mGivenData2A.chunkedList(mGivenData2B)
        assertEquals(mExpectedData2, mResult2.toString())
        assertEquals(1, mResult2.size)
    }

    @Test
    fun chuckedList_isInCorrect() {
        val mGivenData1A = listOf("a", "b", "c", "d", "e", "f", "g")
        val mGivenData1B = 2
        val mExpectedData1 = "[[a, b], [c, d], [e, f]]"

        val mResult1 = mGivenData1A.chunkedList(mGivenData1B)
        assertNotEquals(mExpectedData1, mResult1.toString())
        assertNotEquals(2, mResult1.size)

        val mGivenData2A = listOf("a", "b", "c", "d", "e", "f", "g")
        val mGivenData2B = 10
        val mExpectedData2 = null

        val mResult2 = mGivenData2A.chunkedList(mGivenData2B)
        assertNotEquals(mExpectedData2, mResult2.toString())
        assertNotEquals(10, mResult2.size)
    }


}