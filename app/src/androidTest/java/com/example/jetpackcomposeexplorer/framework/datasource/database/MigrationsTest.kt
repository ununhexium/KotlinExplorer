package com.example.jetpackcomposeexplorer.framework.datasource.database

import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.espresso.contrib.DrawerActions.close
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class MigrationTest {
  private val TEST_DB = "migration-test"

  @get:Rule
  var helper: MigrationTestHelper = MigrationTestHelper(
      InstrumentationRegistry.getInstrumentation(),
      ExplorerDatabase::class.java.canonicalName,
      FrameworkSQLiteOpenHelperFactory()
  )

//  @Test
//  @Throws(IOException::class)
//  fun migrate_2_3() {
//    var db = helper.createDatabase(TEST_DB, 2).apply {
//      execSQL("""INSERT INTO user_profile('id', 'alias') VALUES ('1', 'foo')""")
//      execSQL("""INSERT INTO user_profile('id', 'alias') VALUES ('2', 'bar')""")
//
//      close()
//    }
//
//    // Re-open the database with version 2 and provide
//    // MIGRATION_1_2 as the migration process.
//    db = helper.runMigrationsAndValidate(TEST_DB, 3, true, MIGRATION_1_2)
//
//    // MigrationTestHelper automatically verifies the schema changes,
//    // but you need to validate that the data was migrated properly.
//  }
}
