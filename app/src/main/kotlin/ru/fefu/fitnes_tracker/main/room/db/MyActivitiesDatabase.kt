package ru.fefu.fitnes_tracker.main.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.fefu.fitnes_tracker.main.room.math.Converters

@Database(entities = [MyActivityRoom::class], version = 2)
@TypeConverters(Converters::class)
abstract class MyActivitiesDatabase: RoomDatabase() {
    abstract fun myActivityDao(): MyActivityDao
}