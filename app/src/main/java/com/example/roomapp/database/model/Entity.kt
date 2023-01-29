package com.example.roomapp.database.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "answer_table")
data class Entity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val Answers: String

): Parcelable