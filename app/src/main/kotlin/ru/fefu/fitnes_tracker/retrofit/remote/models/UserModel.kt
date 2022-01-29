package ru.fefu.fitnes_tracker.retrofit.remote.models

data class UserModel(
    val id: Long,
    val name: String,
    val login: String,
    val gender: GenderModel
)
