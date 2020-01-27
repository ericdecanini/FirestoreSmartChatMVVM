package com.example.firestoresmartchatmvvm.mvvm.activities.lobby

import com.example.firestoresmartchatmvvm.injection.scope.AppScoped
import com.example.firestoresmartchatmvvm.mvvm.activities.base.BaseViewModel
import com.example.firestoresmartchatmvvm.mvvm.activities.room.RoomActivity
import com.example.firestoresmartchatmvvm.mvvm.repository.FirebaseRepository
import com.example.firestoresmartchatmvvm.util.AppConstants
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@AppScoped
class LobbyViewModel @Inject constructor(
    firebaseRepository: FirebaseRepository,
    viewState: LobbyViewState
): BaseViewModel<LobbyViewState>(firebaseRepository, viewState) {

    fun handleEnterButton(roomId: String) {

        if (!validate(roomId)) {
            updateUi()
            return
        }

        viewState.enterBtnEnabled = false
        updateUi()

        val errorHandler = CoroutineExceptionHandler { _, exception ->

            exception.printStackTrace()
            viewState.errorMessage = "Something went wrong. Please try again"
            viewState.enterBtnEnabled = true
            updateUi()
        }

        networkScope.launch(errorHandler) {

            firebaseRepository.addUserToRoom(roomId)
            launchRoomActivity(roomId)
        }

    }

    private fun launchRoomActivity(roomId: String) {
        viewState.enterBtnEnabled = true
        viewState.clearRoomTextfield = true
        viewState.newActivity = RoomActivity::class
        viewState.clearActivityOnIntent = false
        viewState.intentExtras.putString(AppConstants.INTENT_EXTRA_ROOMID, roomId)
        updateUi()
    }

    private fun validate(roomId: String): Boolean {

        if (roomId.isEmpty()) {
            viewState.errorMessage = "Please enter a Room ID"
            return false
        }

        viewState.errorMessage = ""
        return true
    }

    fun checkRoomIdPreference(roomId: String?) {
        roomId?.let { launchRoomActivity(it) }
    }


}