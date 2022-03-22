package com.tochukwu.mozambique.data.remote.detailDto

import com.tochukwu.mozambique.data.cache.dto.MovieEntity
import com.tochukwu.mozambique.domain.model.Movie
import com.tochukwu.mozambique.domain.model.movieDetailDomain.Rate

data class Rating(
    val Source: String,
    val Value: String
){

    fun toRate(): Rate{

        return Rate(
            Source = Source,
            Value = Value
        )
    }
}
