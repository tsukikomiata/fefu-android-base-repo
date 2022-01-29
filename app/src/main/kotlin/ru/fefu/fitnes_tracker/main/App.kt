package ru.fefu.fitnes_tracker.main

import android.app.Application
import androidx.room.Room
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.fefu.fitnes_tracker.main.room.db.MyActivitiesDatabase
import ru.fefu.fitnes_tracker.retrofit.remote.interceptors.CustomHeaderInterceptor
import java.util.concurrent.TimeUnit

class App : Application() {

    companion object {
        lateinit var INSTANCE: App
    }

    val db by lazy {
        Room.databaseBuilder(
            this,
            MyActivitiesDatabase::class.java,
            "my_activities_database"
        ).allowMainThreadQueries().build()
    }

    val sharedPreferences by lazy {
        getSharedPreferences("shared_prefs", MODE_PRIVATE)
    }

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(10L, TimeUnit.SECONDS)
            .readTimeout(10L, TimeUnit.SECONDS)
            .writeTimeout(10L, TimeUnit.SECONDS)
            .callTimeout(10L, TimeUnit.SECONDS)
            .addInterceptor(CustomHeaderInterceptor(sharedPreferences))
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    val retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://fefu.t.feip.co/")
            .client(okHttpClient)
            .build()
    }

    override fun onCreate() {
        super.onCreate()

        INSTANCE = this
    }
}