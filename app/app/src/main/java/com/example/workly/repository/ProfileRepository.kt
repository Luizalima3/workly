package com.example.workly.repository

import com.example.workly.model.ProfileData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class ProfileRepository {
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private val profileCollection = db.collection("profiles")

    suspend fun fetchProfileData(): ProfileData? {
        val userId = auth.currentUser?.uid ?: return null
        val snapshot = profileCollection.document(userId).get().await()
        return if (snapshot.exists()) {
            ProfileData(
                name = snapshot.getString("name") ?: "",
                email = snapshot.getString("email") ?: auth.currentUser?.email.orEmpty(),
                phone = snapshot.getString("phone") ?: "",
                profession = snapshot.getString("profession") ?: "",
                description = snapshot.getString("description") ?: ""
            )
        } else {
            null
        }
    }

    suspend fun saveProfileData(profileData: ProfileData): Boolean {
        val userId = auth.currentUser?.uid ?: return false
        val payload = mapOf(
            "name" to profileData.name,
            "email" to profileData.email,
            "phone" to profileData.phone,
            "profession" to profileData.profession,
            "description" to profileData.description
        )
        return try {
            profileCollection.document(userId).set(payload).await()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}
