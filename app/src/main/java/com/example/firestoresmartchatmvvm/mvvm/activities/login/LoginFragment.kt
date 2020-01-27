package com.example.firestoresmartchatmvvm.mvvm.activities.login

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.firestoresmartchatmvvm.R
import com.example.firestoresmartchatmvvm.injection.scope.ActivityScoped
import com.example.firestoresmartchatmvvm.mvvm.activities.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

@ActivityScoped
class LoginFragment @Inject constructor(override var viewModel: LoginViewModel): BaseFragment<LoginViewModel, LoginViewState>(viewModel) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
    }

    override fun getLayoutResourceFile(): Int {
        return R.layout.fragment_login
    }

    override fun updateUi(state: LoginViewState) {

        button_login.isEnabled = state.submitEnabled
        state.newActivity?.start(state.clearActivityOnIntent)
        textview_error_login.text = state.errorMessage
    }

    override fun attachClickListeners() {

        button_login.setOnClickListener { viewModel.handleSubmitButtonClicked(edittext_email.text.toString(), edittext_password.text.toString()) }
        textview_register.setOnClickListener { viewModel.handleRegisterTextviewClicked() }
    }



}