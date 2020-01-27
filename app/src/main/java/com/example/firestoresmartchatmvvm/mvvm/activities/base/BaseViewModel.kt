package com.example.firestoresmartchatmvvm.mvvm.activities.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firestoresmartchatmvvm.mvvm.activities.lobby.LobbyActivity
import com.example.firestoresmartchatmvvm.mvvm.activities.login.LoginActivity
import com.example.firestoresmartchatmvvm.mvvm.repository.FirebaseRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class BaseViewModel<S: ViewState>(
    val firebaseRepository: FirebaseRepository,
    var viewState: S): ViewModel() {

    protected val stateLiveData = MutableLiveData<ViewState>()

    private val networkJob = Job()
    protected val networkScope = CoroutineScope(Dispatchers.IO + networkJob)

    open fun checkUserLoggedIn() {

        if (firebaseRepository.user() == null) {
            viewState.newActivity = LoginActivity::class
            viewState.clearActivityOnIntent = true
            updateUi()
        }
    }

    fun handleSignOut() {
        firebaseRepository.auth.signOut()
        checkUserLoggedIn()
    }

    fun getState(): MutableLiveData<ViewState> {
        return this.stateLiveData
    }

    fun resetNewActivity() {
        viewState.newActivity = null
        updateUi()
    }

    fun updateUi() {
        stateLiveData.postValue(viewState)
    }

}