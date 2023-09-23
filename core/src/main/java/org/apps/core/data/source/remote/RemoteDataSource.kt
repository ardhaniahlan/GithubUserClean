package org.apps.core.data.source.remote


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.apps.core.data.source.remote.network.ApiResponse
import org.apps.core.data.source.remote.network.ApiService
import org.apps.core.data.source.remote.response.UserResponse


class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getAllUser(): Flow<ApiResponse<List<UserResponse>>>{
        return flow{
            try {
                val response = apiService.getSearch("ardhan")
                val dataArray = response.items
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.items))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}