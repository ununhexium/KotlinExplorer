package net.lab0.kotlinexplorer.framework.firebase.implementation

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import net.lab0.kotlinexplorer.business.domain.LessonProgress
import net.lab0.kotlinexplorer.framework.firebase.abstraction.LessonProgressFirebaseService
import net.lab0.kotlinexplorer.framework.firebase.model.LessonProgressDocument
import net.lab0.kotlinexplorer.framework.util.ToDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LessonProgressFirebaseServiceImpl
@Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val mapperToDomain: ToDomain<LessonProgressDocument, LessonProgress>
): LessonProgressFirebaseService