package net.lab0.kotlinexplorer.framework.presentation.intent

import android.app.Activity
import android.content.Context
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.firebase.ui.auth.AuthUI

object Auth {
  fun requestSignIn(
    fragment: Fragment,
    onSuccess: (ActivityResult) -> Unit,
    onFailure: (ActivityResult) -> Unit,
  ) {

    fragment.registerForActivityResult(
      ActivityResultContracts.StartActivityForResult()
    ) { result ->
      if (result.resultCode == Activity.RESULT_OK) {
        // logged in
        onSuccess(result)
      } else {
        onFailure(result)
      }
    }.launch(
      AuthUI.getInstance()
        .createSignInIntentBuilder()
        .setAvailableProviders(
          listOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
          )
        )
        .build()
    )
  }

  fun logOut(context: Context) =
    AuthUI.getInstance().signOut(context)
}

