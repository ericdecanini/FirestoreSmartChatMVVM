package com.example.firestoresmartchatmvvm.injection.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.firestoresmartchatmvvm.injection.ViewModelKey
import com.example.firestoresmartchatmvvm.injection.scope.AppScoped
import com.example.firestoresmartchatmvvm.mvvm.ViewModelFactory
import com.example.firestoresmartchatmvvm.mvvm.activities.login.LoginViewModel
import com.example.firestoresmartchatmvvm.mvvm.activities.register.RegisterViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @AppScoped
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    abstract fun bindRegisterViewModel(viewModel: RegisterViewModel): ViewModel

}