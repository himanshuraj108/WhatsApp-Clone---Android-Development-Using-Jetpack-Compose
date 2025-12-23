package com.example.whatsappclone.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {
    @Serializable
    data object SplashScreen : Routes()

    @Serializable
    data object WelcomeScreen : Routes()

    @Serializable
    data object UserRegistrationScreen : Routes()

    @Serializable
    data object HomeScreen : Routes()

    @Serializable
    data object UpdateScreen : Routes()

    @Serializable
    data object CommunitiesScreen : Routes()

    @Serializable
    data object CallScreen : Routes()

    // Fixed: Added : Routes() inheritance
    @Serializable
    data object UserProfileSetScreen : Routes()

    // Fixed: Added : Routes() inheritance
    @Serializable
    data object SettingScreen : Routes()

    // Added: Route for Chat Details that requires a phone number
    @Serializable
    data class ChatDetailScreen(val phoneNumber: String) : Routes()
}