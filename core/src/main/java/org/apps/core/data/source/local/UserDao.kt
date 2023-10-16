package org.apps.core.data.source.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import org.apps.core.domain.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAllUser(): Flow<List<UserEntity>>

    @Query("SELECT * FROM user where isFavorite = 1")
    fun getFavoriteUser(): Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: List<UserEntity>)

    @Update
    fun updateFavoriteUser(user: UserEntity)
}