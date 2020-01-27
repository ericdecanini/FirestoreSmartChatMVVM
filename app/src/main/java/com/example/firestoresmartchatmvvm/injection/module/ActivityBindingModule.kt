package com.example.firestoresmartchatmvvm.injection.module

import com.example.firestoresmartchatmvvm.injection.scope.ActivityScoped
import com.example.firestoresmartchatmvvm.mvvm.activities.lobby.LobbyActivity
import com.example.firestoresmartchatmvvm.mvvm.activities.lobby.LobbyModule
import com.example.firestoresmartchatmvvm.mvvm.activities.login.LoginActivity
import com.example.firestoresmartchatmvvm.mvvm.activities.login.LoginModule
import com.example.firestoresmartchatmvvm.mvvm.activities.register.RegisterActivity
import com.example.firestoresmartchatmvvm.mvvm.activities.register.RegisterModule
import com.example.firestoresmartchatmvvm.mvvm.activities.room.RoomActivity
import com.example.firestoresmartchatmvvm.mvvm.activities.room.RoomModule
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

    @ActivityScoped
    @ContributesAndroidInjector(modules = [LobbyModule::class])
    internal abstract fun lobbyActivity(): LobbyActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [RoomModule::class])
    internal abstract fun roomActivity(): RoomActivity

}