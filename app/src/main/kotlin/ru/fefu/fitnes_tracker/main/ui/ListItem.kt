package ru.fefu.fitnes_tracker.main.ui

sealed class ListItem {
    class Date (
        val date: String
    ) : ListItem()

    class MyActivity(
        val id: Int,
        val activity: String,
        val distance: String,
        val time: String,
        val date: String,
        val start: String,
        val finish: String
    ) : ListItem()

    class UserActivity (
        val activity: String,
        val distance: String,
        val time: String,
        val date: String,
        val username: String,
//        val start: String,
//        val finish: String
    ) : ListItem()
}