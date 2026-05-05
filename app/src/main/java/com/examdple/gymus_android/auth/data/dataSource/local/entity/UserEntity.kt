package com.examdple.gymus_android.auth.data.dataSource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val username: String,
    val password: String,
    val role: String,
    @ColumnInfo(name = "created_at", defaultValue = "current_timestamp")
    val createdAt: String,
    @ColumnInfo(name = "updated_at", defaultValue = "current_timestamp")
    val updatedAt: String
)
