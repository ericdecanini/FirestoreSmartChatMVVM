package com.example.firestoresmartchatmvvm.mvvm.repository

import androidx.lifecycle.MutableLiveData
import com.example.firestoresmartchatmvvm.entity.ChatMessage
import com.google.firebase.Timestamp
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await
import java.util.*

class FirebaseRepository {

    val auth = FirebaseAuth.getInstance()
    val firestore = FirebaseFirestore.getInstance()

    var roomId = ""
    val chatMessagesLiveData = MutableLiveData<List<ChatMessage>>()

    fun user(): FirebaseUser? = auth.currentUser

    suspend fun login(email: String, password: String): AuthResult {
        return auth.signInWithEmailAndPassword(email, password).await()
    }

    suspend fun register(email: String, password: String): AuthResult {
        return auth.createUserWithEmailAndPassword(email, password).await()
    }

    suspend fun addUserToRoom(roomId: String): DocumentReference {

        return firestore.collection("rooms")
            .document(roomId)
            .collection("users")
            .add(mapOf(
                Pair("id", user()!!.uid),
                Pair("name", user()!!.displayName),
                Pair("image", user()!!.photoUrl)
            ))
            .await()
    }

    fun sendChatMessage(message: String) {

        firestore.collection("rooms")
            .document(roomId)
            .collection("messages")
            .add(mapOf(
                Pair("text", message),
                Pair("user", user()!!.uid),
                Pair("timestamp", Timestamp.now())
            ))
    }

    fun observeChatMessages() {

        firestore.collection("rooms")
            .document(roomId)
            .collection("messages")
            .orderBy("timestamp")
            .addSnapshotListener { messagesSnapshot, exception ->

                if (exception != null) {
                    exception.printStackTrace()
                    return@addSnapshotListener
                }

                val messages = messagesSnapshot?.documents?.map {
                    ChatMessage(
                        it["text"] as String,
                        it["user"] as String,
                        (it["timestamp"]) as Date
                    )
                }

                messages?.let { chatMessagesLiveData.postValue(messages) }
            }
    }


}