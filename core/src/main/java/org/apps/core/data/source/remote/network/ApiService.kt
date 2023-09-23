package org.apps.core.data.source.remote.network


import org.apps.core.data.source.remote.response.ListUserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    suspend fun getSearch(
        @Query("q") query: String
    ) : ListUserResponse
}