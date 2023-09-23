package org.apps.core.domain

import kotlinx.coroutines.flow.Flow

class UserInteractor (private val userRepository: IUserRepository): UserUseCase {
    override fun getAllUser() = userRepository.getAllUser()
    override fun getFavoriteUser() = userRepository.getFavoriteUser()
    override fun setFavoriteUser(user: User, state: Boolean) = userRepository.setFavoriteUser(user, state)
}