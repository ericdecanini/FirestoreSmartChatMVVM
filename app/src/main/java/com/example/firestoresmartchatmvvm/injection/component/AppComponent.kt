package com.example.firestoresmartchatmvvm.injection.component

import com.example.firestoresmartchatmvvm.Application
import com.example.firestoresmartchatmvvm.injection.module.*
import com.example.firestoresmartchatmvvm.injection.scope.AppScoped
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@AppScoped
@Component(modules = [
    AppModule::class,
    ActivityBindingModule::class,
    AndroidSupportInjectionModule::class,
    ViewModelModule::class,
    FirebaseRepositoryModule::class
])
interface AppComponent: AndroidInjector<Application> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}