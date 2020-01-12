package com.example.firestoresmartchatmvvm.mvvm.activities.register

import android.os.Bundle
import com.example.firestoresmartchatmvvm.R
import com.example.firestoresmartchatmvvm.util.ActivityUtils
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class RegisterActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var registerFragment: RegisterFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        var fragment = supportFragmentManager.findFragmentById(R.id.contentFrame)
        if (fragment == null) {
            fragment = registerFragment
            ActivityUtils.addFragmentToActivity(
                supportFragmentManager,
                fragment,
                R.id.contentFrame
            )
        }
    }
}
