package com.kiras.noter.database.converter

import androidx.room.TypeConverter
import com.kiras.noter.domain.accounts.model.AccountIcon

class AccountIconTypeConverter {

    @TypeConverter
    fun fromAccountIcon(icon: AccountIcon): String {
        return icon.name
    }

    @TypeConverter
    fun toAccountIcon(iconName: String): AccountIcon {
        return AccountIcon.valueOf(iconName)
    }
}