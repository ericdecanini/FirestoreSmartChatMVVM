package com.example.firestoresmartchatmvvm.mvvm.activities.login

import com.example.firestoresmartchatmvvm.injection.providers.BaseResourceProvider
import com.example.firestoresmartchatmvvm.injection.providers.ResourceProvider
import com.example.firestoresmartchatmvvm.injection.scope.ActivityScoped
import com.example.firestoresmartchatmvvm.injection.scope.FragmentScoped
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module(includes = [LoginModule.LoginAbstractModule::class])
class LoginModule {

    @ActivityScoped
    @Provides
    internal fun provideResourceProvider(context: LoginActivity): BaseResourceProvider {
        return ResourceProvider(context)
    }

    @Module
    interface LoginAbstractModule {
        @FragmentScoped
        @ContributesAndroidInjector
        fun loginFragment(): LoginFragment
    }
}
