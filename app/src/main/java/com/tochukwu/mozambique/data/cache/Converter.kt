package com.tochukwu.mozambique.data.cache

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tochukwu.mozambique.domain.model.movieDetailDomain.Rate
import java.util.*


@ProvidedTypeConverter
class Converter{


    @TypeConverter
    open fun storedStringToMyObjects(data: String?): List<Rate?>? {
        val gson = Gson()
        if (data == null) {
            return Collections.emptyList()
        }
        val listType = object : TypeToken<List<Rate?>?>() {}.type
        return gson.fromJson<List<Rate>>(data, listType)
    }

    @TypeConverter
    fun myObjectsToStoredString(myObjects: List<Rate?>?): String? {
        val gson = Gson()
        return gson.toJson(myObjects)
    }
}





/**
@TypeConverter
    fun storedStringToMyObjects(data: String?): Rating? {
        val gson = Gson()
        if (data == null) {
            return null
        }
        val listType: Type = object : TypeToken<Rating?>() {}.type
        return gson.fromJson<Rating>(data, listType)
    }

    @TypeConverter
    fun myObjectsToStoredString(myObjects: Rating?): String? {
        val gson = Gson()
        return gson.toJson(myObjects)
    }

} **/



/**



@ProvidedTypeConverter

@Provides
@Singleton
fun provideWordInfoDatabase(app: Application): WordInfoDatabase {
return Room.databaseBuilder(
app, WordInfoDatabase::class.java, "word_db"
).addTypeConverter(Converters(GsonParser(Gson())))
.build()
} **/