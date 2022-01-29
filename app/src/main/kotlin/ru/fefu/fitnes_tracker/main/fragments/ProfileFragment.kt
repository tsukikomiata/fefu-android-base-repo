package ru.fefu.fitnes_tracker.main.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.FragmentProfileBinding
import ru.fefu.fitnes_tracker.WelcomeScreenActivity
import ru.fefu.fitnes_tracker.main.App
import ru.fefu.fitnes_tracker.retrofit.Result
import ru.fefu.fitnes_tracker.retrofit.remote.models.UserModel
import ru.fefu.fitnes_tracker.view_models.ProfileViewModel
import ru.fefu.fitnes_tracker.view_models.RegistrationViewModel

class ProfileFragment :
    BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {
    private lateinit var viewModel: ProfileViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        binding.btnChange.setOnClickListener() {
            val action = ProfileFragmentDirections.actionProfileFragmentToPasswordFragment()
            findNavController().navigate(action)
        }

        viewModel.logoutUser
            .onEach {
                if (it is Result.Success<Unit>) {
                    App.INSTANCE.sharedPreferences.edit().remove("token").apply()
                    val intent = Intent(requireActivity(), WelcomeScreenActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
                else if (it is Result.Error<Unit>) {
                    Toast.makeText(requireContext(), it.e.toString(), Toast.LENGTH_LONG).show()
                }
            }
            .launchIn(lifecycleScope)

        viewModel.profile
            .onEach {
                if (it is Result.Success<UserModel>) {
                    binding.tilLogin.editText?.setText(it.result.login)
                    binding.tilNickname.editText?.setText(it.result.name)
                }
                else if (it is Result.Error<UserModel>) {
                    Toast.makeText(requireContext(), it.e.toString(), Toast.LENGTH_LONG).show()
                }
            }
            .launchIn(lifecycleScope)
        viewModel.getProfile()

        binding.btnExit.setOnClickListener() {
            viewModel.logout()
        }
    }
}