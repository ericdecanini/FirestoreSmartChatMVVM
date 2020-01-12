package com.example.firestoresmartchatmvvm.mvvm.activities.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firestoresmartchatmvvm.injection.scope.AppScoped
import com.example.firestoresmartchatmvvm.mvvm.activities.register.RegisterActivity
import com.example.firestoresmartchatmvvm.mvvm.repository.FirebaseRepository
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject
import kotlin.reflect.KClass

@AppScoped
class LoginViewModel @Inject constructor(var firebaseRepository: FirebaseRepository): ViewModel() {

    val activityToStart = MutableLiveData<KClass<*>>()

    fun getUser(): FirebaseUser? = firebaseRepository.user()

    fun handleSubmitButtonClicked() {

    }

    fun handleRegisterTextviewClicked() {
        activityToStart.postValue(RegisterActivity::class)
    }

    fun resetActivityToStart() {
        activityToStart.postValue(null)
    }

}