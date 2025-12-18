package com.example.whatsappclone.presentation.callscreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
fun CallScreen() {

    var isSearching by remember { mutableStateOf(false) }
    var search by remember { mutableStateOf("") }
    var showMenu by remember { mutableStateOf(false) }
    val sampleCall = listOf(
        Call(
            image = R.drawable.akshay_kumar,
            name = "Akshay Kumar",
            time = "4:00 AM",
            isMissed = false
        ),
        Call(
            image = R.drawable.salmankhan, name = "Salman Khan", time = "10:32 PM", isMissed = true
        ),
        Call(
            image = R.drawable.sharukhkhan,
            name = "Shahrukh Khan",
            time = "6:43 AM",
            isMissed = false
        ),
        Call(image = R.drawable.boy, name = "Boy1", time = "7:45 PM", isMissed = true),
        Call(
            image = R.drawable.akshay_kumar,
            name = "Akshay Kumar",
            time = "4:00 AM",
            isMissed = false
        ),
        Call(
            image = R.drawable.salmankhan, name = "Salman Khan", time = "10:32 PM", isMissed = true
        ),
        Call(
            image = R.drawable.sharukhkhan,
            name = "Shahrukh Khan",
            time = "6:43 AM",
            isMissed = false
        ),
        Call(
            image = R.drawable.akshay_kumar,
            name = "Akshay Kumar",
            time = "4:00 AM",
            isMissed = false
        ),
        Call(
            image = R.drawable.salmankhan, name = "Salman Khan", time = "10:32 PM", isMissed = true
        ),
        Call(
            image = R.drawable.sharukhkhan,
            name = "Shahrukh Khan",
            time = "6:43 AM",
            isMissed = false
        ),
        Call(
            image = R.drawable.akshay_kumar,
            name = "Akshay Kumar",
            time = "4:00 AM",
            isMissed = false
        ),
        Call(
            image = R.drawable.salmankhan, name = "Salman Khan", time = "10:32 PM", isMissed = true
        ),
        Call(
            image = R.drawable.sharukhkhan,
            name = "Shahrukh Khan",
            time = "6:43 AM",
            isMissed = false
        ),
        Call(
            image = R.drawable.akshay_kumar,
            name = "Akshay Kumar",
            time = "4:00 AM",
            isMissed = false
        ),
        Call(
            image = R.drawable.salmankhan, name = "Salman Khan", time = "10:32 PM", isMissed = true
        ),
        Call(
            image = R.drawable.sharukhkhan,
            name = "Shahrukh Khan",
            time = "6:43 AM",
            isMissed = false
        ),
        Call(
            image = R.drawable.akshay_kumar,
            name = "Akshay Kumar",
            time = "4:00 AM",
            isMissed = false
        ),
        Call(
            image = R.drawable.salmankhan, name = "Salman Khan", time = "10:32 PM", isMissed = true
        ),
        Call(
            image = R.drawable.sharukhkhan,
            name = "Shahrukh Khan",
            time = "6:43 AM",
            isMissed = false
        ),
        Call(
            image = R.drawable.akshay_kumar,
            name = "Akshay Kumar",
            time = "4:00 AM",
            isMissed = false
        ),
        Call(
            image = R.drawable.salmankhan, name = "Salman Khan", time = "10:32 PM", isMissed = true
        ),
        Call(
            image = R.drawable.sharukhkhan,
            name = "Shahrukh Khan",
            time = "6:43 AM",
            isMissed = false
        ),
    )

    Scaffold(
        topBar = {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {

                    if (isSearching) {
                        TextField(
                            value = search,
                            onValueChange = { search = it },
                            placeholder = { Text("Search") },
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            modifier = Modifier.padding(start = 12.dp),
                            singleLine = true
                        )
                    } else {
                        Text(
                            text = "Calls",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 12.dp, top = 4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    if (isSearching) {
                        IconButton(onClick = {
                            isSearching = false
                            search = ""
                        }) {
                            Icon(
                                painter = painterResource(R.drawable.cross),
                                contentDescription = null,
                                modifier = Modifier.size(15.dp)
                            )
                        }
                    } else {
                        IconButton(onClick = { isSearching = true }) {
                            Icon(
                                painter = painterResource(R.drawable.search),
                                contentDescription = null
                            )
                        }

                        Box {
                            IconButton(onClick = { showMenu = true }) {
                                Icon(
                                    painter = painterResource(R.drawable.more),
                                    contentDescription = null
                                )
                            }

                            DropdownMenu(
                                expanded = showMenu,
                                onDismissRequest = { showMenu = false }
                            ) {
                                DropdownMenuItem(
                                    text = { Text("Settings") },
                                    onClick = { showMenu = false }
                                )
                            }
                        }
                    }
                }
                HorizontalDivider()
            }
        },

        bottomBar = {
            BottomNavigation()
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                containerColor = colorResource(R.color.light_green),
                contentColor = Color.White
            ) {
                Icon(
                    painter = painterResource(R.drawable.add_call),
                    contentDescription = null,
                )
            }
        }

    ) { paddingValues ->

        Column(modifier = Modifier.padding(paddingValues)) {
            Spacer(modifier = Modifier.height(16.dp))
            FavoriteSection()

            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.light_green)
                )
            ) {
                Text(
                    text = "Start a new call",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                text = "Recent Calls",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )

            LazyColumn {
                items(sampleCall) { data ->
                    CallItemDesign(data)
                }
            }
        }
    }
}