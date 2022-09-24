package com.example.waveclone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.waveclone.databinding.ActivityMainBinding
import com.example.waveclone.repo.TransactionInfoRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val navController: NavController by lazy {
        findNavController(R.id.home_nav_host_fragment)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        if (savedInstanceState == null)
            setUpToolbar()
    }
    private fun setUpToolbar() {
        with(binding.toolbar) {
            setTitleTextAppearance(this@MainActivity,R.style.textStyle)
            isTitleCentered = true
            setupWithNavController(
                navController,
                AppBarConfiguration(navController.graph)
            )
            setNavigationIcon(R.drawable.ic_back)
        }

    }
}