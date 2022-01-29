package ru.fefu.fitnes_tracker

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.fefu.activitytracker.databinding.ActivityWelcomeScreenBinding
import ru.fefu.fitnes_tracker.main.App
import ru.fefu.fitnes_tracker.main.MainScreenActivity

class WelcomeScreenActivity: AppCompatActivity() {
    lateinit var binding: ActivityWelcomeScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        if (App.INSTANCE.sharedPreferences.getString("token", null) !== null) {
            val intent = Intent(this, MainScreenActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }

        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, RegistrationScreenActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, LoginScreenActivity::class.java)
            startActivity(intent)
        }
    }
}