package net.lab0.kotlinexplorer.framework.presentation.intent

import android.app.Activity
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

const val TAG = "Intents-All"

fun Fragment.requestSignIn(): FirebaseUser? {

  val providers = arrayListOf(
      AuthUI.IdpConfig.EmailBuilder().build(),
  )

  val resultLaunhcer = this
      .registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
          // There are no request codes
          FirebaseAuth.getInstance().currentUser
        } else {
          Log.d(TAG, "Content: Failed to sign in: ${result.data}")
        }
      }
      .launch(
          AuthUI.getInstance()
              .createSignInIntentBuilder()
              .setAvailableProviders(providers)
              .build()
      )

  return FirebaseAuth.getInstance().currentUser
}