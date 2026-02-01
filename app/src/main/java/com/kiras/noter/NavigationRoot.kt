package com.kiras.noter

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kiras.noter.notes.presentation.note_page.NotePageScreenRoot
import com.kiras.noter.notes.presentation.notes_overview.NotesOverviewScreenRoot
import com.kiras.noter.notes.presentation.util.NotePage
import com.kiras.noter.notes.presentation.util.NotesOverview
import com.kiras.noter.presentation.intro.IntroScreenRoot
import com.kiras.noter.presentation.login.LoginScreenRoot
import com.kiras.noter.presentation.registration.RegisterScreenRoot
import com.kiras.noter.presentation.util.Intro
import com.kiras.noter.presentation.util.Login
import com.kiras.noter.presentation.util.Registration

@Composable
fun NavigationRoot(
    isLoggedIn: Boolean,
    navHostController: NavHostController
) {
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navHostController,
        startDestination = if(!isLoggedIn) Intro else NotesOverview
    ) {
        authGraph(navHostController)
        notesGraph(navHostController)
    }
}

private fun NavGraphBuilder.notesGraph(navHostController: NavHostController) {
    composable<NotesOverview> {
        NotesOverviewScreenRoot(
            onNoteClick = { id ->
                navHostController.navigate(NotePage(id)) {
                    launchSingleTop = true
                    popUpTo(NotesOverview)
                }
            },
            onAddNoteClick = {
                navHostController.navigate(NotePage()) {
                    launchSingleTop = true
                    popUpTo(NotesOverview)
                }
            }
        )
    }
    composable<NotePage> {
        NotePageScreenRoot(
            onBackClick = { navHostController.navigateUp() }
        )
    }
}

private fun NavGraphBuilder.authGraph(navController: NavHostController) {
    composable<Intro> {
        IntroScreenRoot(
            onRegisterClick = {
                navController.navigate(Registration) {
                    launchSingleTop = true
                    popUpTo(Intro)
                }
            },
            onLoginClick = {
                navController.navigate(Login) {
                    launchSingleTop = true
                    popUpTo(Intro)
                }
            }
        )
    }
    composable<Registration> {
        RegisterScreenRoot(
            onLoginClick = {
                navController.navigate(Login) {
                    launchSingleTop = true
                    restoreState = true
                    popUpTo(Registration) {
                        inclusive = true
                        saveState = true
                    }
                }
            },
            onSuccessfulRegistrationClick = {
                navController.navigate(NotesOverview) {
                    popUpTo(Intro) {
                        inclusive = true
                    }
                }
            }
        )
    }
    composable<Login> {
        LoginScreenRoot(
            onRegisterClick = {
                navController.navigate(Registration) {
                    launchSingleTop = true
                    restoreState = true
                    popUpTo(Login) {
                        inclusive = true
                        saveState = true
                    }
                }
            },
            onSuccessfulLogin = {
                navController.navigate(NotesOverview) {
                    popUpTo(Intro) {
                        inclusive = true
                    }
                }
            }
        )
    }
}