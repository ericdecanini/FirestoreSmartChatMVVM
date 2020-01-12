package com.example.firestoresmartchatmvvm.mvvm.activities.login

import android.os.Bundle
import com.example.firestoresmartchatmvvm.R
import com.example.firestoresmartchatmvvm.util.ActivityUtils
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class LoginActivity: DaggerAppCompatActivity() {

    @Inject
    lateinit var loginFragment: LoginFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var fragment = supportFragmentManager.findFragmentById(R.id.contentFrame)

        if (fragment == null) {
            fragment = loginFragment
            ActivityUtils.addFragmentToActivity(
                supportFragmentManager,
                fragment,
                R.id.contentFrame
            )
        }
    }

}
