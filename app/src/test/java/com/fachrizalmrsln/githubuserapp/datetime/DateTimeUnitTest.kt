package com.fachrizalmrsln.githubuserapp.datetime

import com.fachrizalmrsln.githubuserapp.utils.datetime.formatDateAndTime
import com.fachrizalmrsln.githubuserapp.utils.datetime.timeAgoTimestamp
import org.junit.Assert.*
import org.junit.Test
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

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

    @Test
    fun timeAgoTimestamp_isCorrect() {
        val mFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val mDateTimeGiven = Calendar.getInstance()

        mDateTimeGiven.add(Calendar.MILLISECOND, 1000)
        mDateTimeGiven.add(Calendar.SECOND, 0)
        mDateTimeGiven.add(Calendar.MINUTE, 0)
        mDateTimeGiven.add(Calendar.HOUR, 0)
        mDateTimeGiven.add(Calendar.DAY_OF_WEEK, 2)

        val mDate = mFormat.format(mDateTimeGiven.time)
        val mDateExpected = "Updated 2 days ago"
        val mResult = mDate.timeAgoTimestamp()
        assertEquals(mDateExpected, mResult)
    }

    @Test
    fun timeAgoTimestamp_isInCorrect() {
        val mFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val mDateTimeGiven = Calendar.getInstance()

        mDateTimeGiven.add(Calendar.MILLISECOND, 1000)
        mDateTimeGiven.add(Calendar.SECOND, 0)
        mDateTimeGiven.add(Calendar.MINUTE, 0)
        mDateTimeGiven.add(Calendar.HOUR, 0)
        mDateTimeGiven.add(Calendar.DAY_OF_WEEK, 32)

        val mDate = mFormat.format(mDateTimeGiven.time)
        val mDateExpected = "Updated 32 days ago"
        val mResult = mDate.timeAgoTimestamp()
        assertNotEquals(mDateExpected, mResult)
    }

    @Test
    fun timeAgoTimestamp_isInCorrect_exception() {
        val mFormat = SimpleDateFormat("yyyy")
        val mDateTimeGiven = Calendar.getInstance()

        mDateTimeGiven.add(Calendar.MILLISECOND, 1000)
        mDateTimeGiven.add(Calendar.SECOND, 0)
        mDateTimeGiven.add(Calendar.MINUTE, 0)
        mDateTimeGiven.add(Calendar.HOUR, 0)
        mDateTimeGiven.add(Calendar.DAY_OF_WEEK, 32)

        val mDate = mFormat.format(mDateTimeGiven.time)
        val mDateExpected = "Unparseable date: \"2022\""
        val mResult = assertThrows(ParseException::class.java) {
            mDate.timeAgoTimestamp()
        }
        assertEquals(mDateExpected, mResult.message)
    }

}