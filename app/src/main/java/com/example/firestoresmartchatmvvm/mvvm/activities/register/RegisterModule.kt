package com.example.firestoresmartchatmvvm.mvvm.activities.register

import com.example.firestoresmartchatmvvm.injection.providers.BaseResourceProvider
import com.example.firestoresmartchatmvvm.injection.providers.ResourceProvider
import com.example.firestoresmartchatmvvm.injection.scope.ActivityScoped
import com.example.firestoresmartchatmvvm.injection.scope.FragmentScoped
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module(includes = [RegisterModule.RegisterAbstractModule::class])
class RegisterModule {

    @ActivityScoped
    @Provides
    internal fun provideResourceProvider(context: RegisterActivity): BaseResourceProvider {
        return ResourceProvider(context)
    }

    @Module
    interface RegisterAbstractModule {
        @FragmentScoped
        @ContributesAndroidInjector
        fun registerFragment(): RegisterFragment
    }
}
