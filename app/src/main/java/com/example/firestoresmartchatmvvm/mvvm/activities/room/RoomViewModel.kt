package com.example.firestoresmartchatmvvm.mvvm.activities.room

import com.example.firestoresmartchatmvvm.injection.scope.AppScoped
import com.example.firestoresmartchatmvvm.mvvm.activities.base.BaseViewModel
import com.example.firestoresmartchatmvvm.mvvm.repository.FirebaseRepository
import javax.inject.Inject

@AppScoped
class RoomViewModel @Inject constructor(
    firebaseRepository: FirebaseRepository,
    viewState: RoomViewState
): BaseViewModel<RoomViewState>(firebaseRepository, viewState) {

    init {
        viewState.firebaseUser = firebaseRepository.user()
    }

    fun setRoomId(roomId: String) {
        firebaseRepository.roomId = roomId
        getChatMessages()
    }

    fun handleSendButton(message: String) {
        sendMessage(message)
        viewState.clearEdittextChat = true
        updateUi()
    }

    private fun sendMessage(message: String) {
        firebaseRepository.sendChatMessage(message)
    }

    private fun getChatMessages() {
        firebaseRepository.observeChatMessages()
        viewState.messagesLiveData = firebaseRepository.chatMessagesLiveData
        updateUi()
    }

}