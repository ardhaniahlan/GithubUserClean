package org.apps.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import org.apps.core.domain.UserUseCase

class FavoriteViewModel(userUseCase: UserUseCase) : ViewModel() {

    val favoriteUser = userUseCase.getFavoriteUser().asLiveData()
}