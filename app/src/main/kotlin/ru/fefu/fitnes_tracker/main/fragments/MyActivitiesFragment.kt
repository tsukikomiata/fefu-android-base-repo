package ru.fefu.fitnes_tracker.main.fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.FragmentMyActivitiesBinding
import ru.fefu.fitnes_tracker.main.ui.ListItemAdapter
import ru.fefu.fitnes_tracker.main.ui.MyListItemRepository

class MyActivitiesFragment :
    BaseFragment<FragmentMyActivitiesBinding>(R.layout.fragment_my_activities) {

    private val repository = MyListItemRepository()
    private val myActivitiesAdapter = ListItemAdapter(repository.getItems())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.frMyRecyclerView) {
            adapter = myActivitiesAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        myActivitiesAdapter.setItemClickListener {
            val action = ActivityFragmentDirections.actionActivityFragmentToMyActivityDetailsFragment()
            findNavController().navigate(action)
        }
    }
}