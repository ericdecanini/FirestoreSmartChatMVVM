package com.example.firestoresmartchatmvvm.injection.module

import com.example.firestoresmartchatmvvm.injection.scope.AppScoped
import com.example.firestoresmartchatmvvm.mvvm.activities.lobby.LobbyViewState
import com.example.firestoresmartchatmvvm.mvvm.activities.login.LoginViewState
import com.example.firestoresmartchatmvvm.mvvm.activities.register.RegisterViewState
import com.example.firestoresmartchatmvvm.mvvm.activities.room.RoomViewState
import dagger.Module
import dagger.Provides

@Module
class ViewStatesModule {

    @Provides
    @AppScoped
    fun provideLoginViewState(): LoginViewState = LoginViewState()

    @Provides
    @AppScoped
    fun provideRegisterViewState(): RegisterViewState = RegisterViewState()

    @Provides
    @AppScoped
    fun provideLobbyViewState(): LobbyViewState = LobbyViewState()

    @Provides
    @AppScoped
    fun provideRoomViewState(): RoomViewState = RoomViewState()

}