package com.kiras.noter

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kiras.noter.notes.presentation.note_page.NotePageScreenRoot
import com.kiras.noter.notes.presentation.notes_overview.NotesOverviewScreenRoot
import com.kiras.noter.notes.presentation.util.NotePage
import com.kiras.noter.notes.presentation.util.NotesOverview
import com.kiras.noter.presentation.registration.RegisterScreenRoot
import com.kiras.noter.presentation.util.Login
import com.kiras.noter.presentation.util.Registration

@Composable
fun NavigationRoot(navHostController: NavHostController) {
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navHostController,
        startDestination = Registration
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
    composable<Registration> {
        RegisterScreenRoot(
            onLoginClick = {

            },
            onSuccessfulRegistrationClick = {

            }
        )
    }
    composable<Login> {

    }
}