package com.fachrizalmrsln.githubuserapp.model

sealed class Message {
    class Toast(val message: String?) : Message()
    class Dialog(val message: String?) : Message()
}