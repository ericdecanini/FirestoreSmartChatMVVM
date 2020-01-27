package com.example.firestoresmartchatmvvm.mvvm.activities.register

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.firestoresmartchatmvvm.R
import com.example.firestoresmartchatmvvm.injection.scope.ActivityScoped
import com.example.firestoresmartchatmvvm.mvvm.activities.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_register.*
import javax.inject.Inject

@ActivityScoped
class RegisterFragment @Inject constructor(override var viewModel: RegisterViewModel): BaseFragment<RegisterViewModel, RegisterViewState>(viewModel) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(RegisterViewModel::class.java)

        setHasOptionsMenu(true)
    }

    override fun getLayoutResourceFile(): Int {
        return R.layout.fragment_register
    }

    override fun updateUi(state: RegisterViewState)
    {
        button_register.isEnabled = state.submitEnabled
        state.newActivity?.start(state.clearActivityOnIntent)
        textview_error_register.text = state.errorMessage
    }

    override fun attachClickListeners() {

        button_register.setOnClickListener { viewModel.handleSubmitButtonClicked(
            edittext_email.text.toString(),
            edittext_password.text.toString(),
            edittext_confirm_password.text.toString()
        ) }

        textview_login.setOnClickListener { viewModel.handleLoginTextviewClicked() }
    }

}