package com.tochukwu.mozambique.data.remote.dto

import com.tochukwu.mozambique.data.cache.dto.MovieEntity

data class Search(
    val Poster: String,
    val Title: String,
    val Type: String,
    val Year: String,
    val imdbID: String
){


    fun toMovieEntity(): MovieEntity {

        return MovieEntity(
            title = Title ?: "",
            year = Year?: "",
            poster = Poster ?: ""

        )
    }
}


/**
/**
 * DTO are responsible for parsing responses from network.
*/
@JsonClass(generateAdapter = true)
data class NewsResponse(
@Json(name = "articles") val articleDTO: List<ArticleDTO>
)

@JsonClass(generateAdapter = true)
data class ArticleDTO(
val url: String?,
val author: String?,
val title: String?,
val description: String?,
@Json(name = "urlToImage") val imgUrl: String?,
@Json(name = "publishedAt") val date: String?,
val content: String?,
val source: SourceDTO
)

@JsonClass(generateAdapter = true)
data class SourceDTO(val name: String)

@JsonClass(generateAdapter = true)
fun SourceDTO.asModel(): Source {
return Source(name = name)
}

/**
 * Mapping ArticleDTO to model Article.
*/
fun NewsResponse.asModel(): List<Article> {
return articleDTO.map {
Article(
id = it.title+System.currentTimeMillis(),
url = it.url?: "",
author = it.author ?: "",
title = it.title ?: "",
description = it.description ?: "",
imgUrl = it.imgUrl ?: "",
date = it.date ?: "",
content = it.content ?: "",
source = it.source.asModel()
)
}
} **/