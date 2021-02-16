package net.lab0.kotlinexplorer.framework.presentation.activity.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.loadVectorResource
import com.google.firebase.auth.FirebaseAuth
import net.lab0.kotlinexplorer.R
import net.lab0.kotlinexplorer.framework.presentation.components.UserProfileUi

class UserProfile : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      val placeholder = loadVectorResource(id = R.drawable.ic_kotlin_logo).resource.resource
      UserProfileUi(FirebaseAuth.getInstance().currentUser?.email, placeholder)
    }
  }
}