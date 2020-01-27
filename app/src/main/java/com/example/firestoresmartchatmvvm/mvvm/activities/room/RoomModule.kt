package com.example.firestoresmartchatmvvm.mvvm.activities.room

import com.example.firestoresmartchatmvvm.injection.providers.BaseResourceProvider
import com.example.firestoresmartchatmvvm.injection.providers.ResourceProvider
import com.example.firestoresmartchatmvvm.injection.scope.ActivityScoped
import com.example.firestoresmartchatmvvm.injection.scope.FragmentScoped
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module(includes = [RoomModule.RoomAbstractModule::class])
class RoomModule {

    @ActivityScoped
    @Provides
    internal fun provideResourceProvider(context: RoomActivity): BaseResourceProvider {
        return ResourceProvider(context)
    }

    @Module
    interface RoomAbstractModule {
        @FragmentScoped
        @ContributesAndroidInjector
        fun registerFragment(): RoomFragment
    }
}
