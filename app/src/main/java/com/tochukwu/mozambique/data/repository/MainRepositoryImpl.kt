package com.tochukwu.mozambique.data.repository

import com.tochukwu.mozambique.data.cache.MovieDao
import com.tochukwu.mozambique.data.remote.MovieApi
import com.tochukwu.mozambique.domain.repository.MainRepository
import com.tochukwu.mozambique.data.remote.dto.Search
import com.tochukwu.mozambique.domain.model.Movie
import com.tochukwu.mozambique.domain.model.movieDetailDomain.Details
import com.tochukwu.mozambique.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val api: MovieApi,
    private val dao: MovieDao
): MainRepository{




    override fun searchMovie(word: String) : Flow<Resource<List<Movie>>> = flow{
        emit(Resource.loading(null))
        val movieInfo = dao.getPosterByTitle(word).map{it.toMovie()}

       emit(Resource.loading(data = movieInfo))

        try{
            val remoteWordInfos = api.getMovie(word).Search

           // emit(Resource.success(remoteWordInfos))
            remoteWordInfos.map{ it.Title }?.let { dao.deleteWordInfos(it) }

            dao.insertMovie(remoteWordInfos.map{it.toMovieEntity()})
        } catch(e: HttpException){
            emit(Resource.error(msg = "Oops, something went wrong!", null))
        } catch(e : IOException){
            emit(Resource.error(msg = "Couldnt reach server, check your internet connection", null))
        }

        val newWordInfo = dao.getPosterByTitle(word).map{it.toMovie()}
        emit(Resource.success(newWordInfo))
    }


    override fun getMovieDetail(id: String): Flow<Details> = flow {

        val detailFromApi = api.getDetails(id)
        val newDetail = detailFromApi.toDetails()
        emit(newDetail)

    }
}


/**

try {
val remoteWordInfos = api.getWordInfo(word)
dao.deleteWordInfos(remoteWordInfos.map { it.word })
dao.insertWordInfos(remoteWordInfos.map { it.toWordInfoEntity() })
} catch(e: HttpException) {
emit(Resource.Error(
message = "Oops, something went wrong!",
data = wordInfos
))
} catch(e: IOException) {
emit(Resource.Error(
message = "Couldn't reach server, check your internet connection.",
data = wordInfos
))
}

val newWordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
emit(Resource.Success(newWordInfos))
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
override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {
emit(Resource.Loading())

val wordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
emit(Resource.Loading(data = wordInfos))

try {
val remoteWordInfos = api.getWordInfo(word)
dao.deleteWordInfos(remoteWordInfos.map { it.word })
dao.insertWordInfos(remoteWordInfos.map { it.toWordInfoEntity() })
} catch(e: HttpException) {
emit(Resource.Error(
message = "Oops, something went wrong!",
data = wordInfos
))
} catch(e: IOException) {
emit(Resource.Error(
message = "Couldn't reach server, check your internet connection.",
data = wordInfos
))
}

val newWordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
emit(Resource.Success(newWordInfos))
}

 **/