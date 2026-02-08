package com.kiras.noter.presentation.settings

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kiras.noter.designsystem.Grey4
import com.kiras.noter.designsystem.components.NoterScaffold
import com.kiras.noter.designsystem.getSettingsContainerColor
import com.kiras.noter.presentation.R
import com.kiras.noter.presentation.settings.components.DisplayNotesSortDropDownMenu
import com.kiras.noter.presentation.settings.components.DisplayNotesStyleDropDownMenu
import com.kiras.noter.presentation.settings.components.SettingsContentRow
import com.kiras.noter.presentation.settings.components.SettingsStatusBar
import com.kiras.noter.presentation.settings.components.SettingsSwitcher
import com.kiras.noter.ui.ObserveAsEvents
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsScreenRoot(
    onBackClick: () -> Unit,
    onLogoutClick: () -> Unit,
    viewModel: SettingsViewModel = koinViewModel()
) {
    ObserveAsEvents(viewModel.events) {
        onLogoutClick()
    }
    SettingsScreen(
        state = viewModel.state,
        onAction = { action ->
            when(action) {
                SettingsActions.OnBackClick -> onBackClick()
                else -> viewModel.onAction(action)
            }
        }
    )
}

@Composable
fun SettingsScreen(
    state: SettingsState,
    onAction: (SettingsActions) -> Unit
) {
    NoterScaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = if (isSystemInDarkTheme()) Color.Black else Color.White,
        topAppBar = {
            SettingsStatusBar(
                onBackClick = { onAction(SettingsActions.OnBackClick) }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 25.dp),
                ) {
                    Text(
                        text = stringResource(id = R.string.menu),
                        style = MaterialTheme.typography.titleSmall,
                        color = if (isSystemInDarkTheme()) Color.White else Grey4
                    )
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = getSettingsContainerColor,
                            contentColor = if (isSystemInDarkTheme()) Color.White else Color.Black
                        ),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        SettingsSwitcher(
                            text = stringResource(R.string.dates_search),
                            isChecked = state.accountNotesSettings.isDateSearchEnabled,
                            onCheckedChange = {
                                onAction(SettingsActions.OnDateSearchEnableChange(it))
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp)
                        )
                    }
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = getSettingsContainerColor,
                            contentColor = if (isSystemInDarkTheme()) Color.White else Color.Black
                        ),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(15.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp)
                        ) {
                            SettingsContentRow(
                                text = stringResource(R.string.display_style),
                                dropdown = @Composable { expanded, onDismiss ->
                                    DisplayNotesStyleDropDownMenu(
                                        expanded = expanded,
                                        onDismiss = onDismiss,
                                        onAction = onAction
                                    )
                                }
                            )
                            SettingsContentRow(
                                text = stringResource(R.string.sort_by),
                                dropdown = @Composable { expanded, onDismiss ->
                                    DisplayNotesSortDropDownMenu(
                                        expanded = expanded,
                                        onDismiss = onDismiss,
                                        onAction = onAction
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    )
}