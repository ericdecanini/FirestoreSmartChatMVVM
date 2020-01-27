package com.example.firestoresmartchatmvvm.mvvm.activities.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class BaseFragment<V: BaseViewModel<S>, S: ViewState>(open var viewModel: V): Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected open fun onBindViewModel() {
        observeState()
        viewModel.checkUserLoggedIn()
    }

    protected open fun onUnbindViewModel() {
        // Empty lifecycle function to be overridden
    }

    abstract fun updateUi(state: S)

    abstract fun attachClickListeners()

    abstract fun getLayoutResourceFile(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(getLayoutResourceFile(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        attachClickListeners()
    }

    override fun onResume() {
        super.onResume()
        onBindViewModel()
    }

    override fun onPause() {
        super.onPause()
        onUnbindViewModel()
    }

    private fun observeState() {
        viewModel.getState().observe(this, Observer { state -> updateUi(state as S) })
    }

    protected fun KClass<*>.start(clearingLast: Boolean) {
        val intent = Intent(activity, this.java)
        startActivity(intent)

        viewModel.resetNewActivity()
        if (clearingLast) activity?.finish()
    }

    protected fun KClass<*>.start(clearingLast: Boolean, extras: Bundle) {
        val intent = Intent(activity, this.java)
        intent.putExtras(extras)
        startActivity(intent)

        viewModel.resetNewActivity()
        if (clearingLast) activity?.finish()
    }

}