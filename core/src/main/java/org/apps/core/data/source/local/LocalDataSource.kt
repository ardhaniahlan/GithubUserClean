package org.apps.core.data.source.local

import android.content.SharedPreferences
import kotlinx.coroutines.flow.Flow
import org.apps.core.domain.User

class LocalDataSource(private val userDao: UserDao) {

    fun getAllUser(): Flow<List<UserEntity>> = userDao.getAllUser()

    fun getFavoriteUser(): Flow<List<UserEntity>> = userDao.getFavoriteUser()

    suspend fun insertUser(userList: List<UserEntity>) = userDao.insertUser(userList)

    fun setFavoriteUser(user: UserEntity, newState: Boolean) {
        user.isFavorite = newState
        userDao.updateFavoriteUser(user)
    }
}