package com.example.whatsappclone.presentation.profile

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.whatsappclone.R
import com.example.whatsappclone.presentation.navigation.Routes
import com.example.whatsappclone.presentation.viewmodel.AuthState
import com.example.whatsappclone.presentation.viewmodel.PhoneAuthViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth


@Composable
fun UserProfileSetScreen(
    phoneAuthViewModel: PhoneAuthViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    var name by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("Available") } // Default status
    var profileImageUri by remember { mutableStateOf<Uri?>(null) }
    var bitmapImage by remember { mutableStateOf<Bitmap?>(null) }

    val authState by phoneAuthViewModel.authState.collectAsState()
    val context = LocalContext.current

    // Firebase details (optional usage here since ViewModel handles logic)
    val firebaseAuth = Firebase.auth
    val userId = firebaseAuth.currentUser?.uid ?: ""

    // Image Picker Logic
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            profileImageUri = uri
            uri?.let {
                // Decode URI to Bitmap for display
                bitmapImage = if (Build.VERSION.SDK_INT < 28) {
                    @Suppress("DEPRECATION")
                    MediaStore.Images.Media.getBitmap(context.contentResolver, it)
                } else {
                    val source = ImageDecoder.createSource(context.contentResolver, it)
                    ImageDecoder.decodeBitmap(source)
                }
            }
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // --- HEADER ---
        Text(
            text = "Profile Info",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.dark_green)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Please provide your name and an optional profile photo",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(32.dp))

        // --- IMAGE PICKER ---
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(Color.LightGray.copy(alpha = 0.3f))
                .clickable { imagePickerLauncher.launch("image/*") },
            contentAlignment = Alignment.Center
        ) {
            if (bitmapImage != null) {
                // Show selected image
                Image(
                    bitmap = bitmapImage!!.asImageBitmap(),
                    contentDescription = "Profile Image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            } else {
                // Show Default Icon
                Icon(
                    imageVector = Icons.Default.Person, // Or your custom drawable
                    contentDescription = "Add Photo",
                    tint = Color.Gray,
                    modifier = Modifier.size(50.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // --- INPUT FIELDS ---

        // Name Input
        TextField(
            value = name,
            onValueChange = { name = it },
            placeholder = { Text("Type your name here") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                focusedIndicatorColor = colorResource(R.color.dark_green),
                unfocusedIndicatorColor = colorResource(R.color.dark_green)
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Status Input (About)
        TextField(
            value = status,
            onValueChange = { status = it },
            placeholder = { Text("About") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                focusedIndicatorColor = colorResource(R.color.dark_green),
                unfocusedIndicatorColor = colorResource(R.color.dark_green)
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(50.dp))

        // --- NEXT BUTTON ---
        Button(
            onClick = {
                if (name.isNotEmpty()) {
                    // Call ViewModel to save data
                    // Note: You need to implement saveUserProfile in your ViewModel
                    // phoneAuthViewModel.saveUserProfile(name, status, profileImageUri, context)
                    Toast.makeText(context, "Save feature implementing next...", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(context, "Please enter your name", Toast.LENGTH_SHORT).show()
                }
            },
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.dark_green))
        ) {
            Text(text = "Next")
        }

        if (authState is AuthState.Loading) {
            Spacer(modifier = Modifier.height(16.dp))
            CircularProgressIndicator(color = colorResource(R.color.dark_green))
        }
    }
    // ... existing UI code ...

    // --- PASTE THIS AT THE BOTTOM OF UserProfileSetScreen ---

    // Watch for the AuthState to change to Success
    val currentAuthState = authState // capture state to local variable for smart cast if needed

    if (currentAuthState is AuthState.Success) {
        // LaunchedEffect ensures we navigate only once
        androidx.compose.runtime.LaunchedEffect(Unit) {

            // Navigate to your HomeScreen
            navHostController.navigate(Routes.HomeScreen) {
                // Clear the back stack so the user cannot go back to the registration screen
                popUpTo(Routes.UserRegistrationScreen) { inclusive = true }
                popUpTo(Routes.UserProfileScreen) { inclusive = true }
            }
        }
    }

    // Optional: Error Handling
    if (currentAuthState is AuthState.Error) {
        val message = (currentAuthState as AuthState.Error).message
        androidx.compose.runtime.LaunchedEffect(message) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
}
