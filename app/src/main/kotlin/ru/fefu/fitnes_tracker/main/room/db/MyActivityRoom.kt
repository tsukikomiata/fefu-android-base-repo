package ru.fefu.fitnes_tracker.main.room.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.fefu.fitnes_tracker.main.room.math.*
import ru.fefu.fitnes_tracker.main.ui.ListItem
import ru.fefu.fitnes_tracker.map.ui.MapItemType
import java.time.Duration
import java.time.LocalDateTime

@Entity (tableName = "my_activities_database")
data class MyActivityRoom(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "activity") val activity: Int,
    @ColumnInfo(name = "start") val start: LocalDateTime,
    @ColumnInfo(name = "finish") val finish: LocalDateTime,
    @ColumnInfo(name = "coords") val coords: List<Pair<Double, Double>>
) {
    fun toMyActivity(): ListItem.MyActivity {
        return ListItem.MyActivity(
            id,
            MapItemType.values()[activity].type,
            coords.getDistance().toFormattedDistance(),
            Duration.between(start, finish).toFormattedDurationBetween(),
            finish.toFinishDateOrTime(),
            start.toTime(),
            finish.toTime(),
        )
    }
}
