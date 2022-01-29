package ru.fefu.fitnes_tracker.map.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import ru.fefu.activitytracker.R
import ru.fefu.fitnes_tracker.map.ui.MapItem
import ru.fefu.fitnes_tracker.map.ui.MapItemAdapter

class StartTrackingFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView

    private val dataList = listOf(
        MapItem("Велосипед"),
        MapItem("Бег"),
        MapItem("Езда на кадилак"),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_start_tracking, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.rvMapItems)
        val adapter = MapItemAdapter(dataList)
        recyclerView.adapter = adapter

        val btn: Button = view.findViewById(R.id.btnStart)
        btn.setOnClickListener {
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

    companion object {
        @JvmStatic
        fun newInstance() = StartTrackingFragment()
    }
}