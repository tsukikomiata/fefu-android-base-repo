package ru.fefu.fitnes_tracker.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_activity.*
import ru.fefu.activitytracker.R
import ru.fefu.fitnes_tracker.main.ViewPagerAdapter

class ActivityFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val myFragment = inflater.inflate(R.layout.fragment_activity, container, false)
        val tabLayout = myFragment.findViewById<TabLayout>(R.id.fr_act_tabLayout)
        val viewPager2 = myFragment.findViewById<ViewPager2>(R.id.fr_act_viewPager)
        val adapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        viewPager2.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager2) {tab, position->
            when(position) {
                0->{
                    tab.text = "Моя"
                }
                1->{
                    tab.text = "Пользователей"
                }
            }
        }.attach()

        return myFragment
    }
}