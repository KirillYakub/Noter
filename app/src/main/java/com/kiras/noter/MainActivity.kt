package com.kiras.noter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.rememberNavController
import com.kiras.noter.designsystem.NoterTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel by viewModel<MainViewModel>()

    private val controller by lazy {
        WindowInsetsControllerCompat(window, window.decorView)
    }

    private fun hideStatusBars() {
        controller.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        controller.hide(WindowInsetsCompat.Type.systemBars())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.state.isCheckingAuth
            }
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoterTheme {
                NavigationRoot(
                    isLoggedIn = viewModel.state.isLoggedIn,
                    navHostController = rememberNavController()
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        hideStatusBars()
    }
}