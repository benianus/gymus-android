package com.example.gymusAndroid.auth.data.dataSource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.gymusAndroid.auth.data.dataSource.local.entity.UserEntity

@Dao
interface UserDao {
    @Insert(entity = UserEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)
}