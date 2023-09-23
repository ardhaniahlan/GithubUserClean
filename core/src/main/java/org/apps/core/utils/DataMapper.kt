package org.apps.core.utils

import org.apps.core.data.source.local.UserEntity
import org.apps.core.data.source.remote.response.UserResponse
import org.apps.core.domain.User

object DataMapper {

    fun mapResponsesToEntities(input: List<UserResponse>) :List <UserEntity>{
        val userList = ArrayList<UserEntity>()
        input.map{
            val user = UserEntity(
                id = it.id,
                login = it.login,
                avatarUrl = it.avatarUrl,
                html_url = it.htmlUrl,
                isFavorite = false
            )
            userList.add(user)
        }
        return userList
    }

    fun mapEntitiesToDomain(input: List<UserEntity>) : List<User> =
        input.map{
            User(
                id = it.id,
                login = it.login,
                avatarUrl = it.avatarUrl,
                html_url = it.html_url,
                isFavorite = false
            )
        }

    fun mapDomainToEntities(input: User) =
        UserEntity(
            id = input.id,
            login = input.login,
            avatarUrl = input.avatarUrl,
            html_url = input.html_url,
            isFavorite = false
        )
}