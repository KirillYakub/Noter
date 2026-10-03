package com.kiras.noter.widgets

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.action.actionStartActivity
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import com.kiras.noter.domain.notes.repository.NotesRepository
import com.kiras.noter.widgets.theme.WidgetAddButtonContainerColor
import com.kiras.noter.widgets.theme.WidgetContainerColor
import com.kiras.noter.widgets.theme.WidgetPrimaryTextColor
import com.kiras.noter.widgets.theme.WidgetSecondaryTextColor
import org.koin.java.KoinJavaComponent

class AddNoteWidgetWithUserDataReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget
        get() = AddNoteWidgetWithUserData()
}

class AddNoteWidgetWithUserData : GlanceAppWidget() {

    private val notesRepository: NotesRepository = KoinJavaComponent.get(NotesRepository::class.java)

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val totalNotesText = context.getString(R.string.total_notes)
        val tapToCreateText = context.getString(R.string.tap_to_create_new_note)
        val addNoteText = context.getString(com.kiras.noter.designsystem.R.string.add_note)

        val createNoteIntent = Intent(Intent.ACTION_VIEW, "https://noter.com/create_note".toUri()).apply {
            setClassName(context, "com.kiras.noter.MainActivity")
            action = ACTION_CREATE_NOTE
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }

        val notesCount = notesRepository.getActiveUserNotesCount()

        provideContent {
            val notesCountAsState = notesCount.collectAsState(initial = 0)
            GlanceTheme {
                Row(
                    modifier = GlanceModifier
                        .fillMaxWidth()
                        .background(WidgetContainerColor)
                        .cornerRadius(16.dp)
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = GlanceModifier.defaultWeight()
                    ) {
                        Text(
                            text = totalNotesText.plus(" ${notesCountAsState.value}"),
                            style = TextStyle(
                                color = WidgetPrimaryTextColor,
                                fontSize = 14.sp
                            )
                        )
                        Text(
                            text = tapToCreateText,
                            style = TextStyle(
                                color = WidgetSecondaryTextColor,
                                fontSize = 12.sp
                            )
                        )
                    }
                    Box(
                        modifier = GlanceModifier
                            .size(36.dp)
                            .cornerRadius(18.dp)
                            .background(WidgetAddButtonContainerColor)
                            .clickable(actionStartActivity(createNoteIntent)),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            provider = ImageProvider(com.kiras.noter.designsystem.R.drawable.add_icon),
                            contentDescription = addNoteText,
                            modifier = GlanceModifier.size(18.dp)
                        )
                    }
                }
            }
        }
    }
}
