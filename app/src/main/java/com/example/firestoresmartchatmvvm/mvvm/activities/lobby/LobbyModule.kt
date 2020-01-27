package com.example.firestoresmartchatmvvm.mvvm.activities.lobby

import com.example.firestoresmartchatmvvm.injection.providers.BaseResourceProvider
import com.example.firestoresmartchatmvvm.injection.providers.ResourceProvider
import com.example.firestoresmartchatmvvm.injection.scope.ActivityScoped
import com.example.firestoresmartchatmvvm.injection.scope.FragmentScoped
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module(includes = [LobbyModule.LobbyAbstractModule::class])
class LobbyModule {

    @ActivityScoped
    @Provides
    internal fun provideResourceProvider(context: LobbyActivity): BaseResourceProvider {
        return ResourceProvider(context)
    }

    @Module
    interface LobbyAbstractModule {
        @FragmentScoped
        @ContributesAndroidInjector
        fun lobbyFragment(): LobbyFragment
    }
}