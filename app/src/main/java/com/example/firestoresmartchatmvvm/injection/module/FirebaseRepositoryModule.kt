package com.example.firestoresmartchatmvvm.injection.module

import com.example.firestoresmartchatmvvm.injection.scope.AppScoped
import com.example.firestoresmartchatmvvm.mvvm.repository.FirebaseRepository
import dagger.Module
import dagger.Provides

@Module
class FirebaseRepositoryModule {

    @Provides
    @AppScoped
    fun provideFirebaseRepository(): FirebaseRepository = FirebaseRepository()

}