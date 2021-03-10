package net.lab0.kotlinexplorer.framework.firebase.implementation

import assertk.assertThat
import assertk.assertions.isEqualToIgnoringGivenProperties
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import net.lab0.kotlinexplorer.business.domain.extracontent.ExtraContentRequest
import net.lab0.kotlinexplorer.framework.firebase.abstraction.ExtraContentService
import net.lab0.kotlinexplorer.framework.firebase.model.ExtraContentRequestDocument
import net.lab0.kotlinexplorer.framework.util.FromDomain
import net.lab0.kotlinexplorer.framework.util.ToDomain
import net.lab0.kotlinexplorer.injection.FirestoreInstanceModule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@UninstallModules(FirestoreInstanceModule::class)
@HiltAndroidTest
internal class ExtraContentServiceImplTest {
  private lateinit var extraContentService: ExtraContentService

  @get:Rule(order = 0)
  var hiltRule = HiltAndroidRule(this)

  @Inject
  lateinit var firebaseAuth: FirebaseAuth

  @Inject
  lateinit var firestore: FirebaseFirestore

  @Inject
  lateinit var fromDomain: FromDomain<ExtraContentRequestDocument, ExtraContentRequest>

  @Inject
  lateinit var toDomain: ToDomain<ExtraContentRequestDocument, ExtraContentRequest>

  @Before
  fun before() {
    hiltRule.inject()

    Tasks.await(firebaseAuth.signInAnonymously())

    extraContentService = ExtraContentServiceImpl(
      firestore,
      fromDomain,
    )
  }

  @Test
  fun canSubmitAnExtraLessonRequest(): Unit = runBlocking {
    // given
    val service = ExtraContentServiceImpl(firestore, fromDomain)
    val extra = ExtraContentRequest(116, 117, "", "", "")

    // when
    service.requestExtraLessons(firebaseAuth.uid!!, extra)

    // then
    val savedObject = firestore
      .collection("users")
      .document(firebaseAuth.uid!!)
      .collection("extraLessonRequest")
      .document("singleton")
      .get()
      .await()
      .toObject(ExtraContentRequestDocument::class.java)!!

    val extraDoc = fromDomain(extra)
    assertThat(savedObject).isEqualToIgnoringGivenProperties(
      extraDoc,
      ExtraContentRequestDocument::timestamp
    )
  }
}