package com.example.whatsappclone.presentation.updatescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whatsappclone.R
import com.example.whatsappclone.presentation.bottomnavigation.BottomNavigation

@Composable
@Preview(showSystemUi = true)
fun UpdateScreen(modifier: Modifier = Modifier) {
    val scrollStatus = rememberScrollState()

    val sampleStatus = listOf(
        StatusData(image = R.drawable.boy, name = "Rahul", time = "10 min ago"),
        StatusData(image = R.drawable.akshay_kumar, name = "Akshay", time = "6 hour ago"),
        StatusData(image = R.drawable.carryminati, name = "Carry", time = "18 hour ago")
    )

    val sampleChannels = listOf(
        ChannelItem(
            image = R.drawable.akshay_kumar,
            name = "Akshay",
            description = "Join this fan channel now"
        ),
        ChannelItem(
            image = R.drawable.carryminati,
            name = "Carry",
            description = "Welcome To YouTuber Channel"
        )
    )

    Scaffold(floatingActionButton = {
        FloatingActionButton(
            onClick = {},
            containerColor = colorResource(R.color.light_green),
            modifier = Modifier.size(65.dp),
            contentColor = Color.White
        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_photo_camera_24),
                contentDescription = null
            )
        }
    }, bottomBar = {
        BottomNavigation()
    }, topBar = {
        TopBar()
    }) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .verticalScroll(scrollStatus)
        ) {
            Text(
                text = "Status",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
            )
            MyStatus()

            sampleStatus.forEach { data ->
                StatusItem(statusData = data)
            }
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(
                color = Color.Gray
            )
            Text(
                text = "Channels",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(text = "Stay updated on topics that matter to you. Find channels to follow below")
                Spacer(modifier = Modifier.height(32.dp))
                Text(text = "Find channels to follow")
            }
            Spacer(modifier = Modifier.height(16.dp))

            sampleChannels.forEach { item ->
                ChannelItemDesign(channelItem = item) // i can also write (it) only
            }
        }
    }
}