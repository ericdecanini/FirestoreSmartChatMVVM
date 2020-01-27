package com.example.firestoresmartchatmvvm.mvvm.activities.register

import com.example.firestoresmartchatmvvm.mvvm.activities.base.ViewState
import kotlin.reflect.KClass

class RegisterViewState(
    var submitEnabled: Boolean = true,
    newActivity: KClass<*>? = null,
    clearActivityOnIntent: Boolean = false,
    var errorMessage: String = ""
): ViewState(
    newActivity, clearActivityOnIntent
)
