package org.apps.core.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Int,
    val login: String,
    val avatarUrl: String,
    val html_url: String,
    var isFavorite: Boolean
) : Parcelable
