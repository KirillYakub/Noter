package com.kiras.noter.widgets

import android.content.Context
import android.content.Intent
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
import androidx.glance.appwidget.provideContent
import androidx.glance.layout.Column

const val ACTION_CREATE_NOTE = "com.kiras.noter.CREATE_NOTE"

class AddNoteWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget
        get() = AddNoteWidget()
}

class AddNoteWidget : GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val addNote = context.getString(R.string.add_note_widget)

        val createNoteIntent = Intent(Intent.ACTION_VIEW, "https://noter.com/create_note".toUri()).apply {
            setClassName(context, "com.kiras.noter.MainActivity")
            action = ACTION_CREATE_NOTE
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }

        provideContent {
            GlanceTheme {
                Column(
                    modifier = GlanceModifier
                        .clickable(actionStartActivity(createNoteIntent))
                ) {
                    Image(
                        provider = ImageProvider(resId = com.kiras.noter.designsystem.R.drawable.widget_add_note),
                        contentDescription = addNote
                    )
                }
            }
        }
    }
}
