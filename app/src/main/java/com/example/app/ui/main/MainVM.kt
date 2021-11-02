package com.example.app.ui.main

import android.app.Application
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.app.data.pref.PrefKey
import com.example.app.data.pref.dataStore
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainVM(application: Application) : AndroidViewModel(application) {

    private val dataStore = getApplication<Application>().dataStore

    private val lastMenuIdLiveData: MutableLiveData<Int> = MutableLiveData()

    init {
        initLastMenuId()
    }

    fun getLastMenuId(): Int = lastMenuIdLiveData.value ?: 0

    fun saveCurrentMenuId(selectedItemId: Int) {
        viewModelScope.launch {
            dataStore.edit { pref ->
                pref[PrefKey.PREF_KEY_LAST_MENU_ID] = selectedItemId
            }
        }
    }

    private fun initLastMenuId() {
        viewModelScope.launch {
            dataStore.data
                .map { pref -> pref[PrefKey.PREF_KEY_LAST_MENU_ID] ?: 0 }
                .collect {
                    lastMenuIdLiveData.value = it
                }
        }
    }
}