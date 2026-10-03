package com.kiras.noter

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.DisposableEffect
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kiras.noter.designsystem.NoterTheme
import com.kiras.noter.presentation.util.NotePage
import com.kiras.noter.widgets.ACTION_CREATE_NOTE
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel by viewModel<MainViewModel>()
    private var navController: NavHostController? = null

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
            val navHostController = rememberNavController()
            DisposableEffect(navHostController) {
                navController = navHostController
                onDispose {
                    navController = null
                }
            }
            NoterTheme {
                NavigationRoot(
                    isCheckingAuth = viewModel.state.isCheckingAuth,
                    isLoggedIn = viewModel.state.isLoggedIn,
                    navHostController = navHostController
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        hideStatusBars()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        val handled = navController?.handleDeepLink(intent)
        if (handled != true && intent.action == ACTION_CREATE_NOTE) {
            if (viewModel.state.isLoggedIn) {
                navController?.navigate(NotePage()) {
                    launchSingleTop = true
                }
            }
        }
    }
}