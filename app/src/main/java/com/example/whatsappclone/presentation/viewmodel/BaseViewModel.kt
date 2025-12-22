package com.example.whatsappclone.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.whatsappclone.presentation.chat_box.ChatListModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class BaseViewModel : ViewModel() {
    fun searchUserByPhoneNumber(phoneNumber: String, callback: (ChatListModel?) -> Unit) {
        val Currentuser = FirebaseAuth.getInstance().currentUser
        if (Currentuser == null) {
            Log.e("BaseViewModel", "User is not authenticated")
            callback(null)
            return
        }

        val databaseReference = FirebaseDatabase.getInstance().getReference("users")
        databaseReference.orderByChild("phoneNumber").equalTo(phoneNumber)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val user = snapshot.children.first()
                            .getValue(ChatListModel::class.java)
                        callback(user)
                    } else {
                        callback(null)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e(
                        "BaseViewModel",
                        "Error fetching User: ${error.message} Details: ${error.details}"
                    )
                    callback(null)
                }
            })
    }

    fun getChatForUser(userId: String, callback: (List<ChatListModel>) -> Unit) {
        val chatRef = FirebaseDatabase.getInstance().getReference("users/$userId/chats")
        chatRef.orderByChild("userId").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val chatList = mutableListOf<ChatListModel>()
                for (childSnapshot in snapshot.children) {
                    val chat = childSnapshot.getValue(ChatListModel::class.java)
                    if (chat != null) {
                        chatList.add(chat)
                    }
                }
                callback(chatList)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("BaseViewModel", "Error fetching user chats ${error.message}")
                callback(emptyList())
            }
        }

        )
    }
}