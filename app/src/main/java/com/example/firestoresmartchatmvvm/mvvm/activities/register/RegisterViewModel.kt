package com.example.firestoresmartchatmvvm.mvvm.activities.register

import com.example.firestoresmartchatmvvm.injection.scope.AppScoped
import com.example.firestoresmartchatmvvm.mvvm.activities.base.BaseViewModel
import com.example.firestoresmartchatmvvm.mvvm.activities.lobby.LobbyActivity
import com.example.firestoresmartchatmvvm.mvvm.activities.login.LoginActivity
import com.example.firestoresmartchatmvvm.mvvm.repository.FirebaseRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

@AppScoped
class RegisterViewModel @Inject constructor(
    firebaseRepository: FirebaseRepository,
    viewState: RegisterViewState
): BaseViewModel<RegisterViewState>(firebaseRepository, viewState) {

    override fun checkUserLoggedIn() {

        if (firebaseRepository.user() != null) {
            viewState.newActivity = LobbyActivity::class
            viewState.clearActivityOnIntent = true
            updateUi()
        }
    }

    fun handleSubmitButtonClicked(username: String, password: String, confirmPassword: String) {
        registerWithEmailAndPassword(username, password, confirmPassword)
    }

    fun handleLoginTextviewClicked() {
        viewState.newActivity = LoginActivity::class
        updateUi()
    }

    private fun registerWithEmailAndPassword(username: String, password: String, confirmPassword: String) {

        if (!validate(username, password, confirmPassword)) {
            updateUi()
            return
        }

        viewState.submitEnabled = false
        updateUi()

        val errorHandler = CoroutineExceptionHandler { _, exception ->
            exception.printStackTrace()
            viewState.errorMessage = "Account associated with this email already exists"
            viewState.submitEnabled = true
            updateUi()
        }

        networkScope.launch(errorHandler) {
            firebaseRepository.register(username, password)
            viewState.newActivity = LobbyActivity::class
            viewState.clearActivityOnIntent = true
            updateUi()
        }
    }

    private fun validate(email: String, password: String, confirmPassword: String): Boolean {

        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
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

        if (password != confirmPassword) {
            this.viewState.errorMessage = "Password confirmation doesn't match"
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