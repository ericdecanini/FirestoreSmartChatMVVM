package com.example.firestoresmartchatmvvm.mvvm.activities.lobby

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.lifecycle.ViewModelProviders
import androidx.preference.PreferenceManager
import com.example.firestoresmartchatmvvm.R
import com.example.firestoresmartchatmvvm.injection.scope.ActivityScoped
import com.example.firestoresmartchatmvvm.mvvm.activities.base.BaseFragment
import com.example.firestoresmartchatmvvm.util.AppConstants
import kotlinx.android.synthetic.main.fragment_lobby.*
import javax.inject.Inject

@ActivityScoped
class LobbyFragment @Inject constructor(
    override var viewModel: LobbyViewModel
): BaseFragment<LobbyViewModel, LobbyViewState>(viewModel)
{

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.viewModel = ViewModelProviders.of(this, viewModelFactory).get(LobbyViewModel::class.java)

        setHasOptionsMenu(true)
    }

    override fun onBindViewModel() {
        super.onBindViewModel()
        checkRoomIdPreference()
    }

    private fun checkRoomIdPreference() {
        val preferences = PreferenceManager
            .getDefaultSharedPreferences(activity)

        val roomId = preferences.getString(AppConstants.PREFERENCE_ROOMID, null)
        viewModel.checkRoomIdPreference(roomId)
    }

    override fun getLayoutResourceFile(): Int {
        return R.layout.fragment_lobby
    }

    override fun updateUi(state: LobbyViewState) {

        button_enter.isEnabled = state.enterBtnEnabled
        textview_error_enter.text = state.errorMessage
        state.newActivity?.start(state.clearActivityOnIntent, state.intentExtras)

        if (state.clearRoomTextfield) {
            state.clearRoomTextfield = false
            edittext_roomid.setText("")
        }
    }

    override fun attachClickListeners() {
        button_enter.setOnClickListener { viewModel.handleEnterButton(edittext_roomid.text.toString()) }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {

            R.id.nav_signout -> {
                viewModel.handleSignOut()
                true
            }
            else -> false
        }
    }

}