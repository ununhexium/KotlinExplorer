package net.lab0.jetpackcomposeexplorer.framework.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserProfileDao {
  @Insert
  fun insert(userProfile: UserProfileEntity)

  @Query("SELECT * FROM user_profile")
  fun getAll(): List<UserProfileEntity>

  @Update
  fun update(vararg userProfiles: UserProfileEntity)
}
