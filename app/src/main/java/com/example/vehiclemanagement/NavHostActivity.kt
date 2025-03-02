package com.example.vehiclemanagement

import android.os.Bundle
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.findNavController
import androidx.navigation.get

class NavHostActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_host)

        initNavController()
        navController.navigate(R.id.vehicleListFragment)

        onBackPressedDispatcher.addCallback(this) {
            if (navController.currentDestination?.id == navController.graph.startDestinationId) {
                finish()
            } else {
                navController.popBackStack()
            }
        }
    }

    private fun initNavController() {
        navController = findNavController(R.id.nav_host)
        navController.setGraph(R.navigation.nav_graph)
    }
}