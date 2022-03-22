package com.tochukwu.mozambique.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(

    val title: String,
    val year: String,
    val poster: String
) : Parcelable{
}

