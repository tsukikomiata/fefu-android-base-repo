package ru.fefu.fitnes_tracker.map.ui

import androidx.fragment.app.FragmentManager

interface ParentFragmentManager {
    fun getActivitiesFragmentManager() : FragmentManager
}