package ru.fefu.fitnes_tracker.main.fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.FragmentUsersActivitiesBinding
import ru.fefu.fitnes_tracker.main.ui.ListItemAdapter
import ru.fefu.fitnes_tracker.main.ui.UsersListItemRepository

class UsersActivitiesFragment :
    BaseFragment<FragmentUsersActivitiesBinding>(R.layout.fragment_users_activities) {

    private val repository = UsersListItemRepository()
    private val userActivitiesAdapter = ListItemAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.rvUserActivities) {
            adapter = userActivitiesAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        userActivitiesAdapter.setItemClickListener {
            val action = ActivityFragmentDirections.actionActivityFragmentToUserActivityDetailsFragment()
            findNavController().navigate(action)
        }
    }
}