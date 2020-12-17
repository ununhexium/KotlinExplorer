package com.example.jetpackcomposeexplorer.repository

import androidx.lifecycle.MutableLiveData
import com.example.jetpackcomposeexplorer.db.ExplorerDatabase
import com.example.jetpackcomposeexplorer.db.UserProfile

object ExploreRepository {
    val user = MutableLiveData<UserProfile>()

    fun setUserAlias(alias: String) {
        Thread {
            val userDao = ExplorerDatabase.db.getUserDao()
            val newProfile = userDao
                    .getProfile()
                    .firstOrNull()
                    ?.copy(alias = alias)

            newProfile?.let { userDao.update(it) }

            user.postValue(newProfile)
        }
    }
}