package com.example.firestoresmartchatmvvm.mvvm.activities.room

import androidx.lifecycle.LiveData
import com.example.firestoresmartchatmvvm.entity.ChatMessage
import com.example.firestoresmartchatmvvm.mvvm.activities.base.ViewState
import com.google.firebase.auth.FirebaseUser
import kotlin.reflect.KClass

class RoomViewState(
    var messagesLiveData: LiveData<List<ChatMessage>>? = null,
    var clearEdittextChat: Boolean = false,
    var firebaseUser: FirebaseUser? = null
): ViewState()
