package com.fachrizalmrsln.githubuserapp.utils.strings

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class StringsUnitTest {

    @Test
    fun checkNullOrEmpty_isCorrect() {
        val mGivenData1 = "This is test"
        val mExpectedData1 = "This is test"
        val mResult1 = mGivenData1.checkNullOrEmpty()
        assertEquals(mExpectedData1, mResult1)

        val mGivenData2: String? = null
        val mExpectedData2 = "-"
        val mResult2 = mGivenData2.checkNullOrEmpty()
        assertEquals(mExpectedData2, mResult2)
    }

    @Test
    fun checkNullOrEmpty_isInCorrect() {
        val mGivenData1 = "This is test"
        val mExpectedData1 = 23
        val mResult1 = mGivenData1.checkNullOrEmpty()
        assertNotEquals(mExpectedData1, mResult1)

        val mGivenData2: String? = null
        val mExpectedData2 = null
        val mResult2 = mGivenData2.checkNullOrEmpty()
        assertNotEquals(mExpectedData2, mResult2)
    }

    @Test
    fun isUserName_isCorrect() {
        val mGivenData1 = "fachrizalmrsln"
        val mExpectedData1 = "@fachrizalmrsln"
        val mResult1 = mGivenData1.isUserName()
        assertEquals(mExpectedData1, mResult1)

        val mGivenData2: String? = null
        val mExpectedData2 = "-"
        val mResult2 = mGivenData2.isUserName()
        assertEquals(mExpectedData2, mResult2)
    }

    @Test
    fun isUserName_isInCorrect() {
        val mGivenData1 = "fachrizalmrsln"
        val mExpectedData1 = "fachrizalmrsln"
        val mResult1 = mGivenData1.isUserName()
        assertNotEquals(mExpectedData1, mResult1)

        val mGivenData2: String? = null
        val mExpectedData2 = null
        val mResult2 = mGivenData2.isUserName()
        assertNotEquals(mExpectedData2, mResult2)
    }

    @Test
    fun checkNullOrEmptyInt_isCorrect() {
        val mGivenData1 = 12
        val mExpectedData1 = "12"
        val mResult1 = mGivenData1.checkNullOrEmpty()
        assertEquals(mExpectedData1, mResult1)

        val mGivenData2: Int? = null
        val mExpectedData2 = "0"
        val mResult2 = mGivenData2.checkNullOrEmpty()
        assertEquals(mExpectedData2, mResult2)
    }

    @Test
    fun checkNullOrEmptyInt_isInCorrect() {
        val mGivenData1 = 12
        val mExpectedData1 = 12
        val mResult1 = mGivenData1.checkNullOrEmpty()
        assertNotEquals(mExpectedData1, mResult1)

        val mGivenData2: Int? = null
        val mExpectedData2 = 0
        val mResult2 = mGivenData2.checkNullOrEmpty()
        assertNotEquals(mExpectedData2, mResult2)
    }

}