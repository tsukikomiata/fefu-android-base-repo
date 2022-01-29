package ru.fefu.fitnes_tracker.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.fefu.activitytracker.R

class MainScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        val fragmentView = supportFragmentManager.findFragmentById(R.id.fragmentMain) as NavHostFragment
        val navController = fragmentView.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigationMain)
        bottomNavigationView.setupWithNavController(navController)
    }
}
