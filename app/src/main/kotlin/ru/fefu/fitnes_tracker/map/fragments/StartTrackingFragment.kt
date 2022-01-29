package ru.fefu.fitnes_tracker.map.fragments

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.FragmentStartTrackingBinding
import ru.fefu.fitnes_tracker.main.App
import ru.fefu.fitnes_tracker.main.fragments.BaseFragment
import ru.fefu.fitnes_tracker.main.room.db.MyActivityRoom
import ru.fefu.fitnes_tracker.map.ui.MapItem
import ru.fefu.fitnes_tracker.map.ui.MapItemAdapter
import ru.fefu.fitnes_tracker.map.ui.MapItemType
import java.time.LocalDateTime

class StartTrackingFragment : BaseFragment<FragmentStartTrackingBinding>(R.layout.fragment_start_tracking) {
    private lateinit var recyclerView: RecyclerView

    private val dataList = listOf(
        MapItem(MapItemType.BICYCLE.ordinal),
        MapItem(MapItemType.RUNNING.ordinal),
        MapItem(MapItemType.CAR.ordinal),
    )

    private var selectedActivity: MapItemType? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MapItemAdapter(dataList)
        binding.rvMapItems.adapter = adapter

        adapter.setItemClickListener {
                position, activityType -> selectedActivity = activityType
            for(i in 0..binding.rvMapItems.layoutManager?.itemCount!!) {
                binding.rvMapItems.layoutManager?.findViewByPosition(i)?.isSelected=false
            }
            binding.rvMapItems.layoutManager?.findViewByPosition(position)?.isSelected=true
        }

        binding.btnStart.setOnClickListener {
            selectedActivity?.let {
                App.INSTANCE.db.myActivityDao().insert(
                    MyActivityRoom(
                        0,
                        selectedActivity!!.ordinal,
                        LocalDateTime.now().minusHours(2),
                        LocalDateTime.now(),
                        listOf(Pair(25.0, 25.0))
                        //TODO хардкод data
                    )
                )
            parentFragmentManager
                .beginTransaction().apply {
                    replace(
                        R.id.containerTracking,
                        ProcessTrackingFragment.newInstance(),
                        "processTracking"
                    )
                    addToBackStack(null)
                    commit()
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = StartTrackingFragment()
    }
}