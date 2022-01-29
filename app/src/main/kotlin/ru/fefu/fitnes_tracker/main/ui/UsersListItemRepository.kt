package ru.fefu.fitnes_tracker.main.ui

class UsersListItemRepository {
    private val defaultItems = listOf(
        ListItem.Date(
            date = "Вчера"
        ),
        ListItem.UserActivity(
            activity = "Серфинг",
            distance = "14.32 км",
            time = "2 часа 46 минут",
            date = "14 часов назад",
            username = "@van_darkholme"
        ),
        ListItem.UserActivity(
            activity = "Качели",
            distance = "228 м",
            time = "14 часов 48 минут",
            date = "14 часов назад",
            username = "@techniquepasha"
        ),
        ListItem.UserActivity(
            activity = "Езда на кадилак",
            distance = "10 км",
            time = "1 час 10 минут",
            date = "14 часов назад",
            username = "@morgen_shtern"
        )
    )

    fun getItems(): List<ListItem> = defaultItems
}