package com.example.whatsappclone.presentation.chat_box

import android.graphics.Bitmap
import com.google.firebase.database.Exclude

data class ChatListModel(
    val name: String? = null,
    val phoneNumber: String? = null,
    val image: Int? = null,
    val userId: String? = null,
    val time: String? = null,
    val message: String? = null,
    val profileImage: String? = null
) {
    // Empty constructor required for Firebase
    constructor() : this(null, null, null, null, null, null, null)

    // This variable holds the actual image for the UI.
    // @Exclude tells Firebase NOT to save this to the database.
    @get:Exclude
    var userProfileBitmap: Bitmap? = null
}