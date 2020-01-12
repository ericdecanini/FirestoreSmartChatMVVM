package com.example.firestoresmartchatmvvm.mvvm.activities.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firestoresmartchatmvvm.mvvm.activities.login.LoginActivity
import com.example.firestoresmartchatmvvm.mvvm.repository.FirebaseRepository
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject
import kotlin.reflect.KClass

class RegisterViewModel @Inject constructor(var firebaseRepository: FirebaseRepository): ViewModel() {

    val activityToStart = MutableLiveData<KClass<*>>()

    fun getUser(): FirebaseUser? = firebaseRepository.user()

    fun handleSubmitButtonClicked() {

    }

    fun handleLoginTextviewClicked() {
        activityToStart.postValue(LoginActivity::class)
    }

    fun resetActivityToStart() {
        activityToStart.postValue(null)
    }

}