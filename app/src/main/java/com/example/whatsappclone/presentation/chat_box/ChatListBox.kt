package com.example.whatsappclone.presentation.chat_box

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// import com.example.whatsappclone.presentation.viewmodel.BaseViewModel // Not needed here anymore

@Composable
fun ChatListBox(
    chatListModel: ChatListModel,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // --- 1. Profile Image Logic ---
        // We use the 'userProfileBitmap' that was prepared in the ViewModel
        if (chatListModel.userProfileBitmap != null) {
            Image(
                bitmap = chatListModel.userProfileBitmap!!.asImageBitmap(),
                contentDescription = "Profile Picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )
        } else {
            // Fallback: Default Icon if bitmap is null
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Default Profile",
                tint = Color.Gray,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
                    .padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // --- 2. Name and Last Message ---
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = chatListModel.name ?: "Unknown",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = chatListModel.message ?: "Tap to start chatting",
                fontSize = 14.sp,
                color = Color.Gray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        // --- 3. Time ---
        Text(
            text = chatListModel.time ?: "",
            fontSize = 12.sp,
            color = Color.Gray
        )
    }

    // Optional: Divider line
    HorizontalDivider(
        modifier = Modifier.padding(start = 82.dp, end = 16.dp),
        thickness = 0.5.dp,
        color = Color.LightGray
    )
}