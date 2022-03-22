package com.tochukwu.mozambique.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.squareup.moshi.Moshi
import com.tochukwu.mozambique.R
import com.tochukwu.mozambique.data.remote.MovieApi
import com.tochukwu.mozambique.data.remote.MovieApi.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }


    @Singleton
    @Provides
    fun providesConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun providesOkhttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .callTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)

        return okHttpClient.build()
    }


    @Singleton
    @Provides
    fun providesRetrofit(
        converterFactory: Converter.Factory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(converterFactory)
            .client(okHttpClient)

        return retrofit.build()
    }



    /**
    @Singleton
    @Provides
    fun provideOkHttpInterceptor(): Interceptor = Interceptor { chain: Interceptor.Chain ->
    val original: Request = chain.request()
    val requestBuilder: Request.Builder = original.newBuilder()
    .addHeader("x-rapidapi-key", BuildConfig.API_KEY)
    .addHeader("x-rapidapi-host", "movie-database-imdb-alternative.p.rapidapi.com")
    val request: Request = requestBuilder.build()
    chain.proceed(request)
    } **/

    /**
    @Singleton
    @Provides
    fun provideGlideInstance(
    @ApplicationContext context: Context
    ) = Glide.with(context).setDefaultRequestOptions(
    RequestOptions()
    .placeholder(R.drawable.ic_image)
    .error(R.drawable.ic_image)
    ) **/

    @Provides
    @Singleton
    fun provideMovieApiService(retrofit: Retrofit): MovieApi = retrofit
        .create(MovieApi::class.java)

}