package com.kiras.noter.presentation.settings

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kiras.noter.designsystem.Grey4
import com.kiras.noter.designsystem.anim.SlideFadeAnimatedContent
import com.kiras.noter.designsystem.components.NoterScaffold
import com.kiras.noter.presentation.R
import com.kiras.noter.presentation.settings.components.DisplayAccountDropDownMenu
import com.kiras.noter.presentation.settings.components.DisplayNotesSortDropDownMenu
import com.kiras.noter.presentation.settings.components.DisplayNotesDisplayStyleDropDownMenu
import com.kiras.noter.presentation.settings.components.SettingsContentCard
import com.kiras.noter.presentation.settings.components.SettingsDropDown
import com.kiras.noter.presentation.settings.components.SettingsNoteStyle
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
    SlideFadeAnimatedContent(visible = state.showContent) {
        NoterScaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = if (isSystemInDarkTheme()) Color.Black else Color.White,
            topAppBar = {
                SettingsStatusBar(
                    icon = state.accountNotesSettings.accountIcon,
                    onBackClick = { onAction(SettingsActions.OnBackClick) },
                    dropdown = @Composable { expanded, onDismiss ->
                        DisplayAccountDropDownMenu(
                            expanded = expanded,
                            onDismiss = onDismiss,
                            onAction = onAction
                        )
                    },
                    modifier = Modifier.padding(end = 10.dp)
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
                        SettingsContentCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = 15.dp,
                                    vertical = 5.dp
                                )
                        ) {
                            SettingsSwitcher(
                                text = stringResource(R.string.dates_search),
                                isChecked = state.accountNotesSettings.isDateSearchEnabled,
                                onCheckedChange = {
                                    onAction(SettingsActions.OnDateSearchEnableChange(it))
                                },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        SettingsContentCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = 15.dp,
                                    vertical = 5.dp
                                )
                        ) {
                            SettingsDropDown(
                                text = stringResource(R.string.display_style),
                                dropdown = @Composable { expanded, onDismiss ->
                                    DisplayNotesDisplayStyleDropDownMenu(
                                        selected = state.accountNotesSettings.displayType,
                                        expanded = expanded,
                                        onDismiss = onDismiss,
                                        onAction = onAction
                                    )
                                }
                            )
                            SettingsDropDown(
                                text = stringResource(R.string.sort_by),
                                dropdown = @Composable { expanded, onDismiss ->
                                    DisplayNotesSortDropDownMenu(
                                        selected = state.accountNotesSettings.sortType,
                                        expanded = expanded,
                                        onDismiss = onDismiss,
                                        onAction = onAction
                                    )
                                }
                            )
                        }
                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(top = 10.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.note_edit),
                            style = MaterialTheme.typography.titleSmall,
                            color = if (isSystemInDarkTheme()) Color.White else Grey4
                        )
                        SettingsContentCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = 15.dp,
                                )
                                .padding(
                                    top = 20.dp,
                                    bottom = 10.dp
                                )
                        ) {
                            SettingsNoteStyle(
                                selected = state.accountNotesSettings.notesStyle,
                                onStyleChange = {
                                    onAction(SettingsActions.OnNotesStyleChange(it))
                                }
                            )
                        }
                    }
                }
            }
        )
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(
        state = SettingsState(),
        onAction = {}
    )
}