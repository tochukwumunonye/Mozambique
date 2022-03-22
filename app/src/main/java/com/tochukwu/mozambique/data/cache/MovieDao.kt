package com.tochukwu.mozambique.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tochukwu.mozambique.data.cache.dto.MovieEntity
import com.tochukwu.mozambique.domain.model.Movie


@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(info: List<MovieEntity?>)


    @Query("SELECT * FROM  movieTable")
    fun returnAllMovies(): List<MovieEntity>


    @Query("SELECT * FROM movieTable WHERE title LIKE '%' || :title || '%'")
    suspend fun getPosterByTitle(title: String?): List<MovieEntity>



    @Query("DELETE FROM movieTable WHERE title IN(:words)")
    suspend fun deleteWordInfos(words: List<String?>)



}



/**

@Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun insertWordInfos(infos: List<WordInfoEntity>)

@Query("DELETE FROM wordinfoentity WHERE word IN(:words)")
suspend fun deleteWordInfos(words: List<String>)

@Query("SELECT * FROM wordinfoentity WHERE word LIKE '%' || :word || '%'")
suspend fun getWordInfos(word: String): List<WordInfoEntity> **/