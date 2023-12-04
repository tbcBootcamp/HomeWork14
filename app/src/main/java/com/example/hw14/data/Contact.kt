package com.example.hw14.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.UUID

@Parcelize
data class Contact(
    val name: String,
    val number: String,
    val withEmail: Boolean = false,
    val email: String? = null,
    val id: UUID = UUID.randomUUID(),
) : Parcelable
