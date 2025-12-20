package com.example.whatsappclone.presentation.homescreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.whatsappclone.R
import com.example.whatsappclone.presentation.bottomnavigation.BottomNavigation


@Composable
fun HomeScreen(navHostController: NavHostController) {
    val chatData = listOf(
        ChatDesignModel(
            image = R.drawable.salmankhan, name = "Salman Khan", time = "10:00AM", message = "Hi"
        ),
        ChatDesignModel(
            image = R.drawable.rashmika, name = "Rashmika", time = "9:00PM", message = "Hello"
        ),
        ChatDesignModel(
            image = R.drawable.sharukhkhan, name = "Sharukh Khan", time = "10:00AM", message = "Hi"
        ),
        ChatDesignModel(
            image = R.drawable.sharadhakapoor,
            name = "Sharadha Kapoor",
            time = "7:35PM",
            message = "Hi"
        ),
        ChatDesignModel(
            image = R.drawable.ajay_devgn, name = "Ajay Devgan", time = "10:00AM", message = "Hi"
        ),
        ChatDesignModel(
            image = R.drawable.akshay_kumar,
            name = "Akshay Kumar",
            time = "4:00AM",
            message = "hello"
        ),
        ChatDesignModel(
            image = R.drawable.hrithik_roshan,
            name = "Hrithik Raushan",
            time = "10:00AM",
            message = "Hi"
        ),
        ChatDesignModel(
            image = R.drawable.kartik_aaryan,
            name = "Kartik Aryan",
            time = "10:00AM",
            message = "Hi"
        ),
        ChatDesignModel(
            image = R.drawable.tripti_dimri, name = "Tripti Dimri", time = "12:00AM", message = "Hi"
        ),
    )
    Scaffold(floatingActionButton = {
        FloatingActionButton(
            onClick = { /* TODO */ },
            containerColor = colorResource(R.color.light_green),
            modifier = Modifier.size(65.dp),
            contentColor = Color.White
        ) {
            Icon(
                painter = painterResource(R.drawable.chat_icon),
                contentDescription = null,
                modifier = Modifier.size(28.dp)
            )
        }
    }, bottomBar = {
        BottomNavigation()
    }) {
        Column(modifier = Modifier.padding(it)) {
            Spacer(modifier = Modifier.height(16.dp))
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "WhatsApp",
                    fontSize = 28.sp,
                    color = colorResource(R.color.light_green),
                    modifier = Modifier
                        .align(
                            Alignment.CenterStart
                        )
                        .padding(16.dp),
                    fontWeight = FontWeight.Bold
                )
                Row(modifier = Modifier.align(Alignment.CenterEnd)) {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(R.drawable.camera),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(R.drawable.search),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(R.drawable.more),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
            HorizontalDivider()
            LazyColumn() {
                items(chatData) {
                    ChatDesign(chatDesignModel = it)
                }
            }
        }

    }
}