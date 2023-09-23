package org.apps.core.domain


import kotlinx.coroutines.flow.Flow
import org.apps.core.data.Resource

interface UserUseCase {
    fun getAllUser(): Flow<Resource<List<User>>>
    fun getFavoriteUser(): Flow<List<User>>
    fun setFavoriteUser(user: User, state: Boolean)
}