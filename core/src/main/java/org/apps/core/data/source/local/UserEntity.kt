package org.apps.core.data.source.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = "userId")
    var id: Int,

    @ColumnInfo(name = "userLogin")
    var login: String,

    @ColumnInfo(name = "userAvatar")
    var avatarUrl: String,

    @ColumnInfo(name = "link")
    var html_url: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)