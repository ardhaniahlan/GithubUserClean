package org.apps.githubuser.ui.detail

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.lifecycle.ViewModel
import org.apps.core.domain.User
import org.apps.core.domain.UserUseCase

class DetailViewModel(private val context: Context, private val userUseCase: UserUseCase) : ViewModel() {

    private val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun setFavoriteUser(user: User, newStatus: Boolean) {
        sharedPreferences.edit().putBoolean(user.login, newStatus).apply()
        userUseCase.setFavoriteUser(user, newStatus)
    }

    fun getFavoriteStatus(user: User): Boolean {
        return sharedPreferences.getBoolean(user.login, false)
    }

}