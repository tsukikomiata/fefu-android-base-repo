package ru.fefu.fitnes_tracker.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.fefu.fitnes_tracker.main.fragments.MyActivitiesFragment
import ru.fefu.fitnes_tracker.main.fragments.UsersActivitiesFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0->{
                MyActivitiesFragment()
            }
            1->{
                UsersActivitiesFragment()
            }
            else->{
                Fragment()
            }
        }
    }

}