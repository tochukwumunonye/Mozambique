package com.tochukwu.mozambique.data.remote

import com.tochukwu.mozambique.data.remote.detailDto.DetailInfo
import com.tochukwu.mozambique.data.remote.dto.Run
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface  MovieApi {


    @GET("/")
    suspend fun getMovie(
        @Query("s") word: String,
        @Query("apikey") apiKey: String = "35c0cb92",
    ): Run

    @GET("/{t}")
    suspend fun getDetails(
      //  @Path("t") t: String,
        @Query("t") t: String,

        @Query("apiKey") apiKey: String = "35c0cb92",

       //I/okhttp.OkHttpClient: <-- 404 Not Found http://www.omdbapi.com/tt1570728?apiKey=35c0cb92 (478ms)
    ): DetailInfo

    companion object {
        const val BASE_URL = "http://www.omdbapi.com/"
    }

}

/**
 *
 *
 *
 *
@GET("genre/tv/list")
suspend fun getTvGenres(
@Query(API_KEY) apiKey: String,
@Query(LANGUAGE) language: String
): NetworkTvGenres

@GET("tv/{tv_id}/reviews")
suspend fun getTvReview(
@Path(TV_ID) tvId: Int,
@Query(API_KEY) apiKey: String
): NetworkTvReview

@GET("tv/{tv_id}/credits")
suspend fun getTvCredits(
@Path(TV_ID) tvId: Int,
@Query(API_KEY) apiKey: String
): NetworkTvCredit

 *
 *
@GET("/v1/coins/{coinId}")
suspend fun getCoinById(
@Path("coinId") coinId: String)
: CoinDetailDto
}
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *

interface DictionaryApi {

@GET("/")
suspend fun getWordInfo(
@Query("s") word: String
): List<MovieDto>

companion object {
const val BASE_URL = "http://www.omdbapi.com/"
}

}



http://www.omdbapi.com/?i=tt3896198&apikey=35c0cb92
http://img.omdbapi.com/?s=batman&apikey=35c0cb92

interface MovieApiService {

@GET("/")
suspend fun getMovies(
@Query("s") movieSearchQuery: String,
@Query("r") returnType: String = "json"
): Response<MovieResultDto>

}
https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=API_KEY
@GET("top-headlines")
suspend fun getNews(
@Query("country") cou2ntry: String = "",
@Query("apiKey") apiKey: String = API_KEY,
@Query("category") category: String =  "",
@Query("page") page: Int = 0,
@Query("pageSize") pageSize: Int = 0
): NewssterResponse**/