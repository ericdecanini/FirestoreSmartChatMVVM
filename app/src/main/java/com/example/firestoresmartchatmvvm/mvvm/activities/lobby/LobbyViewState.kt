package com.example.firestoresmartchatmvvm.mvvm.activities.lobby

import com.example.firestoresmartchatmvvm.mvvm.activities.base.ViewState

class LobbyViewState(
    var errorMessage: String = "",
    var enterBtnEnabled: Boolean = true,
    var clearRoomTextfield: Boolean = false
): ViewState()