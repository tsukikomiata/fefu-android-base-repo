package ru.fefu.fitnes_tracker.main.fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.FragmentMyActivitiesBinding
import ru.fefu.fitnes_tracker.main.App
import ru.fefu.fitnes_tracker.main.room.math.toDateSeparator
import ru.fefu.fitnes_tracker.main.ui.ListItem
import ru.fefu.fitnes_tracker.main.ui.ListItemAdapter

class MyActivitiesFragment :
    BaseFragment<FragmentMyActivitiesBinding>(R.layout.fragment_my_activities) {

    private val myActivitiesAdapter = ListItemAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myActivitiesAdapter.setItemClickListener {
            val action = ActivityFragmentDirections.actionActivityFragmentToMyActivityDetailsFragment()
            findNavController().navigate(action)
        }

        with(binding.rvMyActivities) {
            adapter = myActivitiesAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        App.INSTANCE.db.myActivityDao().getAll().observe(viewLifecycleOwner) {
                activitiesList ->
            val activitiesMap = mutableMapOf<String, MutableList<ListItem.MyActivity>>()

            activitiesList.forEach {
                if (!activitiesMap.containsKey(it.finish.toDateSeparator())) {
                    activitiesMap[it.finish.toDateSeparator()] = mutableListOf()
                }

                activitiesMap[it.finish.toDateSeparator()]?.add(it.toMyActivity())
            }

            val packedList = mutableListOf<ListItem>()

            activitiesMap.forEach { (dateSeparatorContent, myActivitiesList) ->
                packedList.add(ListItem.Date(dateSeparatorContent))
                myActivitiesList.forEach {
                    packedList.add(it)
                }
            }

            myActivitiesAdapter.submitList(packedList)
        }

    }
}