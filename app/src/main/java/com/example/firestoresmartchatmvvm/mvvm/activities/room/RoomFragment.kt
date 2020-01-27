package com.example.firestoresmartchatmvvm.mvvm.activities.room

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firestoresmartchatmvvm.R
import com.example.firestoresmartchatmvvm.entity.ChatMessage
import com.example.firestoresmartchatmvvm.injection.scope.ActivityScoped
import com.example.firestoresmartchatmvvm.mvvm.activities.base.BaseFragment
import com.example.firestoresmartchatmvvm.mvvm.activities.lobby.LobbyActivity
import com.example.firestoresmartchatmvvm.util.AppConstants
import kotlinx.android.synthetic.main.fragment_room.*
import javax.inject.Inject

@ActivityScoped
class RoomFragment @Inject constructor(override var viewModel: RoomViewModel): BaseFragment<RoomViewModel, RoomViewState>(viewModel) {


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(RoomViewModel::class.java)

        activity?.intent?.extras?.let {
            val roomId = it[AppConstants.INTENT_EXTRA_ROOMID] as String

            viewModel.setRoomId(roomId)
            saveRoomIdToPreferences(roomId)
        } ?: run {
            LobbyActivity::class.start(true)
        }
    }

    override fun getLayoutResourceFile(): Int {
        return R.layout.fragment_room
    }

    override fun updateUi(state: RoomViewState) {
        if (state.clearEdittextChat) {
            edittext_chat.setText("")
            state.clearEdittextChat = false
        }

        state.messagesLiveData?.observe(this, Observer {
            setListAdapter(it, state.firebaseUser!!.uid)
        })
    }

    private fun setListAdapter(messages: List<ChatMessage>, uid: String) {
        val adapter = ChatAdapter(messages, uid)
        list_chat.layoutManager = LinearLayoutManager(activity)
        list_chat.adapter = adapter
    }

    override fun attachClickListeners() {
        button_send.setOnClickListener {
            viewModel.handleSendButton(edittext_chat.text.toString())
        }
    }

    fun saveRoomIdToPreferences(roomId: String?) {
        val preferences = PreferenceManager
            .getDefaultSharedPreferences(activity)

        preferences.edit()
            .putString(AppConstants.PREFERENCE_ROOMID, roomId)
            .apply()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {

            R.id.nav_signout -> {
                saveRoomIdToPreferences(null)
                viewModel.handleSignOut()
                true
            }
            else -> false
        }
    }



}