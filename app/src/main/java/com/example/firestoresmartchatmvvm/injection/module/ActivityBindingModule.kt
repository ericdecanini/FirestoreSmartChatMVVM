package com.example.firestoresmartchatmvvm.injection.module

import com.example.firestoresmartchatmvvm.injection.scope.ActivityScoped
import com.example.firestoresmartchatmvvm.mvvm.activities.login.LoginActivity
import com.example.firestoresmartchatmvvm.mvvm.activities.login.LoginModule
import com.example.firestoresmartchatmvvm.mvvm.activities.register.RegisterActivity
import com.example.firestoresmartchatmvvm.mvvm.activities.register.RegisterModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [LoginModule::class])
    internal abstract fun loginActivity(): LoginActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [RegisterModule::class])
    internal abstract fun registerActivity(): RegisterActivity
}