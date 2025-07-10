package com.example.quotesapp.darktheme

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(application: Application): AndroidViewModel(application) {
    private val context = application.applicationContext

    private val _isDarkTheme = MutableStateFlow(false)
    val isDarkTheme: StateFlow<Boolean> = _isDarkTheme.asStateFlow()

    init {
        viewModelScope.launch {
            ThemePreferenceManager.getThemePref(context).collect {
                _isDarkTheme.value = it
            }
        }
    }

    fun toggleTheme(isDark: Boolean) {
        viewModelScope.launch {
            ThemePreferenceManager.saveThemePref(context, isDark)
        }
    }
}