package com.example.core.config

import android.content.Context
import androidx.room.Room

fun createDatabase(context: Context) =
        Room.databaseBuilder(
            context = context,
            klass = com.example.core.database.GymusDatabase::class.java,
            name = "gymus-db"
        ).build()