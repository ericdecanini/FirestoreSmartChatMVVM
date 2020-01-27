package com.example.firestoresmartchatmvvm.mvvm.activities.login

import com.example.firestoresmartchatmvvm.injection.scope.AppScoped
import com.example.firestoresmartchatmvvm.mvvm.activities.base.BaseViewModel
import com.example.firestoresmartchatmvvm.mvvm.activities.lobby.LobbyActivity
import com.example.firestoresmartchatmvvm.mvvm.activities.register.RegisterActivity
import com.example.firestoresmartchatmvvm.mvvm.repository.FirebaseRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

@AppScoped
class LoginViewModel @Inject constructor(
    firebaseRepository: FirebaseRepository,
    viewState: LoginViewState
): BaseViewModel<LoginViewState>(firebaseRepository, viewState) {

    override fun checkUserLoggedIn() {

        if (firebaseRepository.user() != null) {
            viewState.newActivity = LobbyActivity::class
            viewState.clearActivityOnIntent = true
            updateUi()
        }
    }

    fun handleSubmitButtonClicked(username: String, password: String) {
        loginWithEmailAndPassword(username, password)
    }

    fun handleRegisterTextviewClicked() {
        viewState.newActivity = RegisterActivity::class
        updateUi()
    }

    private fun loginWithEmailAndPassword(email: String, password: String) {

        if (!validate(email, password)) {
            updateUi()
            return
        }

        viewState.submitEnabled = false
        updateUi()

        val errorHandler = CoroutineExceptionHandler { _, exception ->
            exception.printStackTrace()
            viewState.errorMessage = "Please enter the correct email and password"
            viewState.submitEnabled = true
            updateUi()
        }

        networkScope.launch(errorHandler) {
            firebaseRepository.login(email, password)
            viewState.newActivity = LobbyActivity::class
            viewState.clearActivityOnIntent = true
            updateUi()
        }
    }

    private fun validate(email: String, password: String): Boolean {

        if (email.isEmpty() || password.isEmpty()) {
            this.viewState.errorMessage = "Please fill out all the fields"
            return false
        }

        if (!isEmailValid(email)) {
            this.viewState.errorMessage = "Please enter a valid email"
            return false
        }

        if (password.length < 6) {
            this.viewState.errorMessage = "Password must be at least 6 characters long"
            return false
        }

        viewState.errorMessage = ""
        return true
    }

    private fun isEmailValid(email: String): Boolean {
        return Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matcher(email).matches()
    }


}