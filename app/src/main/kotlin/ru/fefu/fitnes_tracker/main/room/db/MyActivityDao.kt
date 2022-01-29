package ru.fefu.fitnes_tracker.main.room.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MyActivityDao {
    @Query("SELECT * FROM my_activities_database ORDER BY finish DESC")
    fun getAll(): LiveData<List<MyActivityRoom>>

    @Query("SELECT * FROM my_activities_database WHERE id=:id")
    fun getById(id: Int): MyActivityRoom

    @Insert
    fun insert(activity : MyActivityRoom)

    @Delete
    fun delete(activity: MyActivityRoom)
}