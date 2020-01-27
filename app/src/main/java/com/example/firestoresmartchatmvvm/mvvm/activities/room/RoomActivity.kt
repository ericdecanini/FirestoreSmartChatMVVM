package com.example.firestoresmartchatmvvm.mvvm.activities.room

import android.os.Bundle
import com.example.firestoresmartchatmvvm.R
import com.example.firestoresmartchatmvvm.util.ActivityUtils
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class RoomActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var roomFragment: RoomFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        var fragment = supportFragmentManager.findFragmentById(R.id.contentFrame)

        if (fragment == null) {
            fragment = roomFragment
            ActivityUtils.addFragmentToActivity(
                supportFragmentManager,
                fragment,
                R.id.contentFrame
            )
        }
    }

    override fun onBackPressed() {
        roomFragment.saveRoomIdToPreferences(null)
        super.onBackPressed()
    }
}
