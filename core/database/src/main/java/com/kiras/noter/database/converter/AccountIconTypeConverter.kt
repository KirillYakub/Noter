package com.kiras.noter.database.converter

import androidx.room.TypeConverter
import com.kiras.noter.domain.accounts.model.AuthIcon

class AccountIconTypeConverter {

    @TypeConverter
    fun fromAccountIcon(icon: AuthIcon): String {
        return icon.name
    }

    @TypeConverter
    fun toAccountIcon(iconName: String): AuthIcon {
        return AuthIcon.valueOf(iconName)
    }
}