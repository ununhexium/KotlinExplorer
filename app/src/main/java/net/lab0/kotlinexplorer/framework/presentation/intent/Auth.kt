package net.lab0.kotlinexplorer.framework.presentation.intent

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

const val TAG = "Intents-All"

object Auth {
  fun requestSignIn(
      fragment: Fragment
  ): FirebaseUser? {

    val providers = arrayListOf(
        AuthUI.IdpConfig.EmailBuilder().build(),
    )

    fragment.registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
      if (result.resultCode == Activity.RESULT_OK) {
        // logged in
      } else {
        Log.d(TAG, "Content: Failed to sign in: ${result.data}")
      }
    }.launch(
        AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
    )

    return FirebaseAuth.getInstance().currentUser
  }

  fun logOut(context: Context) {
    AuthUI.getInstance()
        .signOut(context)
  }
}

