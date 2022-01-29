package ru.fefu.fitnes_tracker

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.fefu.activitytracker.databinding.ActivityLoginScreenBinding
import ru.fefu.fitnes_tracker.main.App
import ru.fefu.fitnes_tracker.main.MainScreenActivity
import ru.fefu.fitnes_tracker.retrofit.Result
import ru.fefu.fitnes_tracker.retrofit.remote.models.TokenUserModel
import ru.fefu.fitnes_tracker.view_models.LoginViewModel

class LoginScreenActivity: AppCompatActivity() {
    lateinit var binding: ActivityLoginScreenBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        binding = ActivityLoginScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnArrow.setOnClickListener {
            val intent = Intent(this, WelcomeScreenActivity::class.java)
            startActivity(intent)
        }

        viewModel.dataFlow
            .onEach {
                if (it is Result.Success<TokenUserModel>) {
                    App.INSTANCE.sharedPreferences.edit().putString("token", it.result.token).apply()
                    val intent = Intent(this, MainScreenActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
                else if (it is Result.Error<TokenUserModel>) {
                    Toast.makeText(this, it.e.toString(), Toast.LENGTH_LONG).show()
                }
            }
            .launchIn(lifecycleScope)

        binding.btnContinue.setOnClickListener {
            val login = binding.tilLogin.editText?.text.toString()
            val password = binding.tilPassword.editText?.text.toString()
            viewModel.login(login, password)
        }

    }
}