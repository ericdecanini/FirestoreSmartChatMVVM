package com.example.firestoresmartchatmvvm.mvvm.activities.login

import com.example.firestoresmartchatmvvm.mvvm.activities.base.ViewState
import kotlin.reflect.KClass

class LoginViewState (
    var submitEnabled: Boolean = true,
    var errorMessage: String = ""
): ViewState()

