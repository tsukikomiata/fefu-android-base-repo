package ru.fefu.fitnes_tracker.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.FragmentPasswordBinding

class PasswordFragment:
    BaseFragment<FragmentPasswordBinding>(R.layout.fragment_password) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState).also {
            binding.tbPassword.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
}