package com.example.waveclone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import com.example.waveclone.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

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
            title = getString(R.string.transaction_fee)
            setTitleTextAppearance(this@MainActivity,R.style.textStyle)
            isTitleCentered = true
            setNavigationIcon(R.drawable.ic_back)
        }

    }
}