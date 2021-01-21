package com.example.jetpackcomposeexplorer.framework.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserProfileDao {
  @Insert
  fun insert(userProfile: UserProfile)

  @Query("SELECT * FROM userprofile")
  fun getAll(): List<UserProfile>

  @Update
  fun update(vararg userProfiles: UserProfile)
}
