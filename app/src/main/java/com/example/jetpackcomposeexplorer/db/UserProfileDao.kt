package com.example.jetpackcomposeexplorer.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserProfileDao {
  @Insert
  fun insert(userProfile: UserProfile)

  @Query("SELECT * FROM userprofile WHERE id == -1")
  fun getProfile(): List<UserProfile>

  @Update
  fun update(vararg userProfiles: UserProfile)
}
