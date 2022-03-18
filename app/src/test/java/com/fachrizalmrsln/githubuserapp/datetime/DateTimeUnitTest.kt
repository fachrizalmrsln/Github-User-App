package com.fachrizalmrsln.githubuserapp.datetime

import com.fachrizalmrsln.githubuserapp.utils.datetime.formatDateAndTime
import org.junit.Assert.*
import org.junit.Test
import java.text.ParseException

class DateTimeUnitTest {

    @Test
    fun formatDateAndTime_isCorrect() {
        val mDateTimeGiven = "2020-05-23T19:08:43Z"
        val mDateExpected = 1590232123000
        val mResult = mDateTimeGiven.formatDateAndTime()
        assertEquals(mResult, mDateExpected)
    }

    @Test
    fun formatDateAndTime_isInCorrect() {
        val mDateTimeGiven = "2020-05-23T19:08:43Z"
        val mDateExpected = 1213033243000
        val mResult = mDateTimeGiven.formatDateAndTime()
        assertNotEquals(mResult, mDateExpected)
    }

    @Test
    fun formatDateAndTime_isInCorrect_exception() {
        val mDateTimeGiven = "2020-05-23"
        val mDateExpected = "Unparseable date: \"$mDateTimeGiven\""
        val mResult = assertThrows(ParseException::class.java) {
            mDateTimeGiven.formatDateAndTime()
        }
        assertEquals(mResult.message, mDateExpected)
    }

}