package com.example.jetpackcomposeexplorer.repository

import com.example.jetpackcomposeexplorer.db.ExplorerDatabase

object ExploreRepository {
  fun getProfileAlias(): String {
    var alias = ""
    val t = Thread {
      val firstProfile = ExplorerDatabase.db.getUserDao().getAll().first()
      alias = firstProfile.alias
    }
    t.start()
    t.join()

    return alias
  }

  fun setProfileAlias(alias: String) {
    Thread {
      val userDao = ExplorerDatabase.db.getUserDao()
      val newProfile = userDao
          .getAll()
          .firstOrNull()
          ?.copy(alias = alias)

      newProfile?.let { userDao.update(it) }
    }.start()
  }
}
