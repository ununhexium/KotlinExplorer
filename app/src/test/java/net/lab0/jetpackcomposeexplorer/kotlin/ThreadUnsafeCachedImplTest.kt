package net.lab0.jetpackcomposeexplorer.kotlin

import net.lab0.jetpackcomposeexplorer.utils.cached
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class ThreadUnsafeCachedImplTest {

  companion object {
    // given
    var inputCallsCounter = 0
    var inputString = "a"
    val input = {
      inputCallsCounter++
      inputString
    }

    var initializerCallsCounter = 0
    val initializer = { it: String ->
      initializerCallsCounter++
      it.repeat(2)
    }
  }

  @Before
  fun before() {
    inputCallsCounter = 0
    initializerCallsCounter = 0
  }

  private fun use(f: String) {
    f.capitalize()
  }

  @Test
  fun `return the computed value`() {
    // given
    var inputCallsCounter = 0
    val input = {
      inputCallsCounter++
      "a"
    }

    var initializerCallsCounter = 0
    val initializer = { it: String ->
      initializerCallsCounter++
      it.repeat(2)
    }

    val f by cached(input, initializer)

    // when
    use(f)
    use(f)
    use(f)

    // then
    assertThat(inputCallsCounter).isEqualTo(3)
    assertThat(initializerCallsCounter).isEqualTo(1)
    assertThat(f).isEqualTo("aa")
  }

  @Test
  fun `recompute the value when the input changes`() {
    val f by cached(input, initializer)

    // when
    use(f)
    assertThat(f).isEqualTo("aa")
    assertThat(initializerCallsCounter).isEqualTo(1)
    inputString = "b"

    // then
    assertThat(f).isEqualTo("bb")
    assertThat(initializerCallsCounter).isEqualTo(2)
  }
}
