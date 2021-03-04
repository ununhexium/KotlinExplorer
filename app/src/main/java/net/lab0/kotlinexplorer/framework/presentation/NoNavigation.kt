package net.lab0.kotlinexplorer.framework.presentation

import android.os.Bundle
import androidx.navigation.NavDirections

object NoNavigation : NavDirections {
  override fun getActionId() = 0
  override fun getArguments(): Bundle = throw IllegalStateException("Don't call this")
}
