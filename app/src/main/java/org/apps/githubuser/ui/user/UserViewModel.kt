package org.apps.githubuser.ui.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import org.apps.core.domain.UserUseCase

class UserViewModel(private val userUseCase: UserUseCase) : ViewModel() {
    val user = userUseCase.getAllUser().asLiveData()
}