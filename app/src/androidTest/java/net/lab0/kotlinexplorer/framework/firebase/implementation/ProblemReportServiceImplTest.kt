package net.lab0.kotlinexplorer.framework.firebase.implementation

import com.google.android.gms.tasks.Tasks
import com.google.common.truth.Truth.assertThat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import io.mockk.clearAllMocks
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import net.lab0.kotlinexplorer.business.domain.problemreport.ProblemReport
import net.lab0.kotlinexplorer.framework.firebase.abstraction.ProblemReportService
import net.lab0.kotlinexplorer.framework.firebase.implementation.TestUsers.user1email
import net.lab0.kotlinexplorer.framework.firebase.implementation.TestUsers.user1password
import net.lab0.kotlinexplorer.framework.firebase.model.ProblemReportDocument
import net.lab0.kotlinexplorer.framework.firebase.model.problemReportCollection
import net.lab0.kotlinexplorer.framework.util.FromDomain
import net.lab0.kotlinexplorer.framework.util.ToDomain
import net.lab0.kotlinexplorer.injection.FirestoreInstanceModule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@HiltAndroidTest
@UninstallModules(FirestoreInstanceModule::class)
internal class ProblemReportServiceImplTest {
  private lateinit var problemReportService: ProblemReportService

  @get:Rule(order = 0)
  var hiltRule = HiltAndroidRule(this)

  @Inject
  lateinit var firebaseAuth: FirebaseAuth

  @Inject
  lateinit var firestore: FirebaseFirestore

  @Inject
  lateinit var fromDomain: FromDomain<ProblemReportDocument, ProblemReport>

  @Inject
  lateinit var toDomain: ToDomain<ProblemReportDocument, ProblemReport>

  @Before
  fun before() {
    hiltRule.inject()
    clearAllMocks()
    Tasks.await(
        firebaseAuth.createUserWithEmailAndPassword(user1email, user1password)
    )
    problemReportService = ProblemReportServiceImpl(
        firebaseAuth,
        firestore,
        fromDomain,
    )
  }

  // FIXME: use firebase auth in test
  @Test
  fun canReportAProblem(): Unit = runBlocking {
    // given
    val problemLocationDescription = "Lesson: x.y.z, page = Foo"
    val problemReport1 = ProblemReport(problemLocationDescription, "FUBAR!")

    val problemLocationDescription2 = "Lesson: a.b.c, page = Foo2"
    val problemReport2 = ProblemReport(problemLocationDescription2, "FUBAR2!")

    // when
    problemReportService.insertOrUpdateProblemReport(problemReport1)
    problemReportService.insertOrUpdateProblemReport(problemReport2)

    // then
    val reports = firestore
        .problemReportCollection(firebaseAuth.uid!!)
        .get()
        .await()
        .toObjects(ProblemReportDocument::class.java)

    assertThat(reports.map { it.id }).containsAtLeast(
        problemReport1.id.toString(),
        problemReport2.id.toString()
    )
  }
}
