package org.apps.core.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.apps.core.data.source.local.LocalDataSource
import org.apps.core.data.source.remote.RemoteDataSource
import org.apps.core.data.source.remote.network.ApiResponse
import org.apps.core.data.source.remote.response.UserResponse
import org.apps.core.domain.IUserRepository
import org.apps.core.domain.User
import org.apps.core.utils.AppExecutors
import org.apps.core.utils.DataMapper


class UserRepository (
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IUserRepository {

    override fun getAllUser(): Flow<Resource<List<User>>> =
        object : NetworkBoundResource<List<User>, List<UserResponse>>() {
            override fun loadFromDB(): Flow<List<User>> {
                return localDataSource.getAllUser().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<User>?): Boolean =  data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<UserResponse>>> =
                remoteDataSource.getAllUser()

            override suspend fun saveCallResult(data: List<UserResponse>) {
                val userList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertUser(userList)
            }
        }.asFlow()

    override fun getFavoriteUser(): Flow<List<User>> {
        return localDataSource.getFavoriteUser().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteUser(user: User, state: Boolean) {
        val userEntity = DataMapper.mapDomainToEntities(user)
        appExecutors.diskIO().execute { localDataSource.setFavoriteUser(userEntity, state) }
    }
}