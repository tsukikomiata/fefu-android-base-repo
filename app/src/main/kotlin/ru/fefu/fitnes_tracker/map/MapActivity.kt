package ru.fefu.fitnes_tracker.map

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.ActivityMapBinding
import ru.fefu.fitnes_tracker.map.fragments.StartTrackingFragment
import ru.fefu.fitnes_tracker.map.ui.ParentFragmentManager

class MapActivity : AppCompatActivity(), ParentFragmentManager {
    lateinit var binding: ActivityMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnArrow.setOnClickListener {
            finish()
        }

        binding.cvTracking.setBackgroundResource(R.drawable.half_rounded_shape)

        supportFragmentManager.beginTransaction().apply {
            add(
                R.id.containerTracking,
                StartTrackingFragment.newInstance(),
                "startTracking"
            )
            commit()
        }
    }

    override fun getActivitiesFragmentManager(): FragmentManager = supportFragmentManager
}