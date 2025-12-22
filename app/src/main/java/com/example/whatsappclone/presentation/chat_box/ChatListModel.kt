package com.example.whatsappclone.presentation.chat_box

data class ChatListModel(
    var callback: (() -> Unit)? = null
)
