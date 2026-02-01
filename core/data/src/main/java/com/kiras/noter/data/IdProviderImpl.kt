package com.kiras.noter.data

import com.kiras.noter.domain.IdProvider
import org.bson.types.ObjectId

class IdProviderImpl : IdProvider {
    override fun newId(): String = ObjectId().toHexString()
}