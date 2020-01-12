package com.example.firestoresmartchatmvvm.mvvm.activities.register

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
import kotlinx.android.synthetic.main.fragment_register.*
import javax.inject.Inject
import javax.inject.Named

@ActivityScoped
class RegisterFragment @Inject constructor(): Fragment(), BaseView {

    private lateinit var viewModel: RegisterViewModel

    @Inject
    @Named("RegisterFragment")
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun bindViewModel() {
        observeNewActivity()
    }

    override fun unbindViewModel() {

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(RegisterViewModel::class.java)

        attachClickListeners()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_register, container, false)
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
        button_register.setOnClickListener { viewModel.handleSubmitButtonClicked() }
        textview_login.setOnClickListener { viewModel.handleLoginTextviewClicked() }
    }

}