package com.example.whatsappclone.presentation.userregistrationscreen

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.whatsappclone.R
import com.example.whatsappclone.presentation.navigation.Routes
import com.example.whatsappclone.presentation.viewmodel.AuthState
import com.example.whatsappclone.presentation.viewmodel.PhoneAuthViewModel

@Composable
fun UserRegistrationScreen(
    navController: NavHostController,
    phoneAuthViewModel: PhoneAuthViewModel = hiltViewModel()
) {
    val authState by phoneAuthViewModel.authState.collectAsState()
    val context = LocalContext.current
    val activity = LocalContext.current as Activity

    var countryCode by remember { mutableStateOf("+91") }
    var phoneNumber by remember { mutableStateOf("") }
    var otp by remember { mutableStateOf("") }
    var verificationId by remember { mutableStateOf<String?>(null) }

    var expanded by remember { mutableStateOf(false) }
    var selectedCountry by remember { mutableStateOf("India") }

    // --- MAIN CONTAINER ---
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 32.dp, vertical = 50.dp), // Added margin for better look
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // --- HEADERS ---
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                "Enter your phone number",
                fontSize = 20.sp,
                color = colorResource(R.color.dark_green),
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            "WhatsApp will need to verify your phone number.",
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        // --- COUNTRY DROPDOWN ---
        // Keeps the layout centered
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                TextButton(
                    onClick = { expanded = true },
                    modifier = Modifier.fillMaxWidth() // Fill width to center align content
                ) {
                    Row(
                        modifier = Modifier.width(220.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.width(24.dp)) // Balance the icon
                        Text(
                            text = selectedCountry,
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = null,
                            tint = colorResource(R.color.dark_green)
                        )
                    }
                }

                // Green Divider below country
                HorizontalDivider(
                    modifier = Modifier.width(240.dp),
                    thickness = 1.dp,
                    color = colorResource(R.color.dark_green)
                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    listOf("India", "USA", "China", "Canada").forEach { country ->
                        DropdownMenuItem(
                            text = { Text(text = country) },
                            onClick = {
                                selectedCountry = country
                                expanded = false
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // --- LOGIC & INPUTS ---
        when (authState) {
            is AuthState.Ideal, is AuthState.Loading, is AuthState.CodeSent -> {

                // Update Verification ID if code is sent
                if (authState is AuthState.CodeSent) {
                    verificationId = (authState as AuthState.CodeSent).verificationId
                }

                // --- STATE 1: PHONE NUMBER INPUT ---
                if (verificationId == null) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        // Country Code Field
                        TextField(
                            value = countryCode,
                            onValueChange = { countryCode = it },
                            modifier = Modifier.width(70.dp),
                            singleLine = true,
                            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color.Transparent,
                                focusedContainerColor = Color.Transparent,
                                unfocusedIndicatorColor = colorResource(R.color.dark_green),
                                focusedIndicatorColor = colorResource(R.color.dark_green)
                            )
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        // Phone Number Field
                        TextField(
                            value = phoneNumber,
                            onValueChange = { phoneNumber = it },
                            placeholder = { Text("Phone number") },
                            modifier = Modifier.width(180.dp),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color.Transparent,
                                focusedContainerColor = Color.Transparent,
                                unfocusedIndicatorColor = colorResource(R.color.dark_green),
                                focusedIndicatorColor = colorResource(R.color.dark_green)
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Carrier charges may apply",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    // NEXT BUTTON
                    Button(
                        onClick = {
                            if (phoneNumber.isNotEmpty()) {
                                val fullPhoneNumber = "$countryCode$phoneNumber"
                                phoneAuthViewModel.sendVerificationCode(fullPhoneNumber, activity)
                            } else {
                                Toast.makeText(context, "Enter valid number", Toast.LENGTH_SHORT).show()
                            }
                        },
                        shape = RoundedCornerShape(4.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.dark_green)),
                        modifier = Modifier.width(100.dp)
                    ) {
                        Text(text = "Next")
                    }

                    if (authState is AuthState.Loading) {
                        Spacer(modifier = Modifier.height(16.dp))
                        CircularProgressIndicator(color = colorResource(R.color.dark_green))
                    }
                }

                // --- STATE 2: OTP INPUT ---
                else {
                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        "Verifying your number...",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    TextField(
                        value = otp,
                        onValueChange = { if (it.length <= 6) otp = it },
                        placeholder = { Text("Enter OTP", textAlign = TextAlign.Center) },
                        textStyle = LocalTextStyle.current.copy(
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp,
                            letterSpacing = 4.sp
                        ),
                        modifier = Modifier.width(200.dp),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            unfocusedIndicatorColor = colorResource(R.color.dark_green),
                            focusedIndicatorColor = colorResource(R.color.dark_green)
                        )
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Button(
                        onClick = {
                            if (otp.isNotEmpty() && verificationId != null) {
                                phoneAuthViewModel.verifyCode(otp, context)
                            } else {
                                Toast.makeText(context, "Invalid OTP", Toast.LENGTH_SHORT).show()
                            }
                        },
                        shape = RoundedCornerShape(4.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.dark_green))
                    ) {
                        Text(text = "Verify")
                    }

                    if (authState is AuthState.Loading) {
                        Spacer(modifier = Modifier.height(16.dp))
                        CircularProgressIndicator(color = colorResource(R.color.dark_green))
                    }
                }
            }

            // --- STATE 3: SUCCESS ---
            is AuthState.Success -> {
                Log.d("PhoneAuth", "LoginSuccessful")
                phoneAuthViewModel.resetAuthState()
                navController.navigate(Routes.UserProfileSetScreen) {
                    popUpTo<Routes.UserRegistrationScreen> { inclusive = true }
                }
            }

            // --- STATE 4: ERROR ---
            is AuthState.Error -> {
                Toast.makeText(context, (authState as AuthState.Error).message, Toast.LENGTH_SHORT).show()
                // You might want to provide a "Retry" button or reset UI state here
            }
        }
    }
}