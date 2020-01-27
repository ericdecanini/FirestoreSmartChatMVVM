package com.example.firestoresmartchatmvvm.injection.module

import com.example.firestoresmartchatmvvm.injection.scope.AppScoped
import com.example.firestoresmartchatmvvm.mvvm.activities.lobby.LobbyViewState
import com.example.firestoresmartchatmvvm.mvvm.activities.login.LoginViewState
import com.example.firestoresmartchatmvvm.mvvm.activities.register.RegisterViewState
import com.example.firestoresmartchatmvvm.mvvm.activities.room.RoomViewState
import com.example.firestoresmartchatmvvm.mvvm.repository.FirebaseRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoriesModule {

    @Provides
    @AppScoped
    fun provideFirebaseRepository(): FirebaseRepository = FirebaseRepository()

}