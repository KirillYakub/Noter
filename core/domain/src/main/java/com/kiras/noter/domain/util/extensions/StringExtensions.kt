package com.kiras.noter.domain.util.extensions

import com.kiras.noter.domain.util.Constants.OVERVIEW_MAX_STRING_LENGTH

fun String.takeFirst(maxLength: Int = OVERVIEW_MAX_STRING_LENGTH): String {
    return if(this.length > maxLength) {
        this.take(maxLength) + "..."
    } else {
        this
    }
}