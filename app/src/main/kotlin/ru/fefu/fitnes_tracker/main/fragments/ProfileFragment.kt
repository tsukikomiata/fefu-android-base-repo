package ru.fefu.fitnes_tracker.main.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.FragmentProfileBinding
import ru.fefu.fitnes_tracker.WelcomeScreenActivity

class ProfileFragment :
    BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnChange.setOnClickListener() {
            val action = ProfileFragmentDirections.actionProfileFragmentToPasswordFragment()
            findNavController().navigate(action)
        }

        binding.btnExit.setOnClickListener() {
            val intent = Intent(context, WelcomeScreenActivity::class.java)
            startActivity(intent)
        }
    }
}