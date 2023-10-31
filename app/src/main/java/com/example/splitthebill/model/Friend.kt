package com.example.splitthebill.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Friend(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @NonNull var name: String,
    var value: Float,
    var description: String,
): Parcelable