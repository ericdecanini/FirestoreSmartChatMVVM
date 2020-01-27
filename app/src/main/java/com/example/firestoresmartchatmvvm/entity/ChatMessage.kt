package com.example.firestoresmartchatmvvm.entity

import java.util.*

data class ChatMessage(
    val text: String,
    val user: String,
    val timestamp: Date
)