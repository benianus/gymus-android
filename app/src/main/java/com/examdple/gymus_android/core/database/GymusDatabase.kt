package com.examdple.gymus_android.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.examdple.gymus_android.auth.data.dataSource.local.UserDao
import com.examdple.gymus_android.auth.data.dataSource.local.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = true)
abstract class GymusDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}