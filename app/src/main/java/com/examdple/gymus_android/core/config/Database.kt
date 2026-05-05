package com.examdple.gymus_android.core.config

import android.content.Context
import androidx.room.Room
import com.examdple.gymus_android.core.database.GymusDatabase

fun createDatabase(context: Context): GymusDatabase {
    return Room.databaseBuilder(
        context = context,
        klass = GymusDatabase::class.java,
        name = "gymus-db"
    ).build()
}