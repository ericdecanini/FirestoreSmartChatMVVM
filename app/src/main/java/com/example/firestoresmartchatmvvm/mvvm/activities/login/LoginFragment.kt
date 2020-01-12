package com.example.firestoresmartchatmvvm.mvvm.activities.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.firestoresmartchatmvvm.R
import com.example.firestoresmartchatmvvm.injection.scope.ActivityScoped
import com.example.firestoresmartchatmvvm.mvvm.activities.base.BaseView
import com.example.firestoresmartchatmvvm.mvvm.activities.lobby.LobbyActivity
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

@ActivityScoped
class LoginFragment @Inject constructor(): Fragment(), BaseView {

    private lateinit var viewModel: LoginViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun bindViewModel() {
        checkUserLoggedIn()
        observeNewActivity()
    }

    override fun unbindViewModel() {

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)

        attachClickListeners()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onResume() {
        super.onResume()
        bindViewModel()
    }

    override fun onPause() {
        super.onPause()
        unbindViewModel()
    }

    private fun observeNewActivity() {
        viewModel.activityToStart.observe(this, Observer { activityToStart ->

            activityToStart?.let {
                viewModel.resetActivityToStart()
                startActivity(Intent(activity, activityToStart.java))
            }
        })
    }

    private fun attachClickListeners() {
        button_login.setOnClickListener { viewModel.handleSubmitButtonClicked() }
        textview_register.setOnClickListener { viewModel.handleRegisterTextviewClicked() }
    }

    private fun checkUserLoggedIn() {

        if (viewModel.getUser() != null) {
            startActivity(Intent(activity, LobbyActivity::class.java))
        }
    }



}