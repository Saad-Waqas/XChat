package com.example.xchat.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.xchat.R
import com.example.xchat.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onStart() {
        super.onStart()
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY

        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.isAppearanceLightStatusBars = true
        window.statusBarColor =
            ContextCompat.getColor(this@MainActivity, R.color.white)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainC)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        init()

    }

    private fun init(){

        binding.blueHeart.setOnClickListener{
            //navController.navigate(R.id.actionMeasure)
        }

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        navController.addOnDestinationChangedListener { _, destination, _ ->

            Log.d("navControllerT","${destination.label}")
            when(destination.id){
                R.id.homeFragment , R.id.settingFragment , R.id.articleTestFragment , R.id.trackFragment -> {
                    visibleBottom(true)
                }
                else -> {
                    visibleBottom(false)
                }
            }
        }

        binding.bottomAppBar.setupWithNavController(navController)

        binding.bottomAppBar.setOnItemReselectedListener {
            // Prevent reloading same fragment
        }
    }

    fun showSystemBars() {
        val controller = WindowInsetsControllerCompat(window, window.decorView)

        // SHOW STATUS BAR ONLY
        controller.show(WindowInsetsCompat.Type.statusBars())

        // HIDE ONLY NAVIGATION BAR
        controller.hide(WindowInsetsCompat.Type.navigationBars())
        controller.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        // Set WHITE status bar with dark icons
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        controller.isAppearanceLightStatusBars = true
    }

    fun whiteStatusBar() {
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY

        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.isAppearanceLightStatusBars = true
        window.statusBarColor =
            ContextCompat.getColor(this@MainActivity, R.color.white)
    }

    fun blueStatusBar() {
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY

        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.isAppearanceLightStatusBars = false
        window.statusBarColor =
            ContextCompat.getColor(this@MainActivity, R.color.blueProgress)
    }

    fun hideSystemBars() {
        WindowInsetsControllerCompat(window, window.decorView).apply {
            hide(WindowInsetsCompat.Type.statusBars() or WindowInsetsCompat.Type.navigationBars())
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    private fun visibleBottom(value: Boolean){
        if (value){
            binding.line.visibility = View.VISIBLE
            binding.bottomAppBar.visibility = View.VISIBLE
            binding.blueHeart.visibility = View.VISIBLE
        }else{
            binding.line.visibility = View.GONE
            binding.bottomAppBar.visibility = View.GONE
            binding.blueHeart.visibility = View.GONE
        }
    }

}