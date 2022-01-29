package ru.fefu.fitnes_tracker

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.fefu.activitytracker.databinding.WelcomeScreenBinding

class welcomeScreenActivity: AppCompatActivity() {
    lateinit var binding: WelcomeScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WelcomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.wsButtonRegister.setOnClickListener {
            val intent = Intent(this, registrationActivity::class.java)
            startActivity(intent)
        }

        binding.wsButtonLogin.setOnClickListener {
            val intent = Intent(this, loginActivity::class.java)
            startActivity(intent)
        }
    }
}