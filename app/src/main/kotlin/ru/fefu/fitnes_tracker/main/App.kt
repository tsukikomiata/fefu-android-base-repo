package ru.fefu.fitnes_tracker.main

import android.app.Application
import androidx.room.Room
import ru.fefu.fitnes_tracker.main.room.db.MyActivitiesDatabase

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

    override fun onCreate() {
        super.onCreate()

        INSTANCE = this
    }
}