package com.kiras.noter.ui

import com.kiras.noter.domain.util.DataError

fun DataError.asUiText(): UiText {
    return when (this) {
        DataError.Local.DISC_FULL -> UiText.StringResource(
            id = R.string.error_disc_full
        )
        DataError.Local.CONFLICT -> UiText.StringResource(
            id = R.string.error_email_already_exists
        )
        else -> UiText.StringResource(
            id = R.string.error_unknown
        )
    }
}