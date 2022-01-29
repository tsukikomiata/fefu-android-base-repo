package ru.fefu.fitnes_tracker.main.ui

class MyListItemRepository {
    private val defaultItems = listOf(
        ListItem.Date(
            date = "Вчера"
        ),
        ListItem.MyActivity(
            activity = "Серфинг",
            distance = "14.32 км",
            time = "2 часа 46 минут",
            date = "14 часов назад"
        ),
        ListItem.Date(
            date = "Май 2022 года"
        ),
        ListItem.MyActivity(
            activity = "Велосипед",
            distance = "1000 м",
            time = "60 минут",
            date = "29.05.2022"
        )
    )

    fun getItems(): List<ListItem> = defaultItems
}