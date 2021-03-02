package com.dixitpatel.elifedemo.di.modules

import android.content.Context
import androidx.room.Room
import com.dixitpatel.elifedemo.R
import com.dixitpatel.elifedemo.constant.BASE_URL
import com.dixitpatel.elifedemo.constant.HTTP_REQUEST_TIMEOUT
import com.dixitpatel.elifedemo.db.RoomDatabaseHelper
import com.dixitpatel.elifedemo.db.TasksDao
import com.dixitpatel.elifedemo.network.ApiInterface
import com.dixitpatel.elifedemo.repository.MainRepository
import com.dixitpatel.elifedemo.repository.Repository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 *  All Network Modules are defined here so they initialized at compileTime.
 */
@Module
class NetworkModule {

    // Interceptors are used for displaying logs of API.
    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor(context: Context): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = getOkHttpLogLevel(context.getString(R.string.okhttp_log_level))
        }
    }

    // Log levels are used for display particular information.
    private fun getOkHttpLogLevel(level: String?): HttpLoggingInterceptor.Level {
        return when (level) {
            HttpLoggingInterceptor.Level.NONE.toString() -> HttpLoggingInterceptor.Level.NONE
            HttpLoggingInterceptor.Level.BASIC.toString() -> HttpLoggingInterceptor.Level.BASIC
            HttpLoggingInterceptor.Level.HEADERS.toString() -> HttpLoggingInterceptor.Level.HEADERS
            HttpLoggingInterceptor.Level.BODY.toString() -> HttpLoggingInterceptor.Level.BODY
            else -> HttpLoggingInterceptor.Level.NONE
        }
    }

    // OkHttpClient for Retrofit and Picasso
    @Singleton
    @Provides
    fun providesClient(cache: Cache?, loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(HTTP_REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(HTTP_REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .retryOnConnectionFailure(true)
            .cache(cache)
            .build()
    }


    // Okhttp Cache file.
    @Provides
    @Singleton
    fun file(app: Context): File {
        val file = File(app.applicationContext.cacheDir, "okhttp_cache")
        file.mkdirs()
        return file
    }

    // Okhttp Cache file size.
    @Provides
    @Singleton
    fun cache(file: File): Cache {
        return Cache(file, 10 * 1000 * 1000) //10 MB
    }


    // Retrofit for Network call Attached with GSONConverterFactory
    @Singleton
    @Provides
    fun provideRetrofit(
        cache: Cache,
        loggingInterceptor: HttpLoggingInterceptor,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(providesClient(cache, loggingInterceptor))
            .build()
    }

    // Retrofit interface for all API call.
    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideRoomDatabaseHelper(context: Context): RoomDatabaseHelper {
        return Room.databaseBuilder(context, RoomDatabaseHelper::class.java, "tasks.db"
        ).allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    fun provideTasksDao(appDatabase: RoomDatabaseHelper): TasksDao {
        return appDatabase.tasksDao()
    }

    @Provides
    @Singleton
    fun providesMainRepository(apiInterface: ApiInterface, tasksDao: TasksDao) : Repository {
        return MainRepository(apiInterface,tasksDao)
    }

}