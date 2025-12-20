package com.example.whatsappclone.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.whatsappclone.presentation.callscreen.CallScreen
import com.example.whatsappclone.presentation.communityscreen.CommunityScreen
import com.example.whatsappclone.presentation.homescreen.HomeScreen
import com.example.whatsappclone.presentation.profile.UserProfileSetScreen
import com.example.whatsappclone.presentation.splashscreen.SplashScreen
import com.example.whatsappclone.presentation.updatescreen.UpdateScreen
import com.example.whatsappclone.presentation.userregistrationscreen.UserRegistrationScreen
import com.example.whatsappclone.presentation.welcomescreen.WelcomeScreen

@Composable
fun WhatsAppNavigationSystem() {

    val navController = rememberNavController()

    NavHost(startDestination = Routes.SplashScreen, navController = navController) {

        composable<Routes.SplashScreen> {
            SplashScreen(navController)
        }

        composable<Routes.WelcomeScreen> {
            WelcomeScreen(navController)
        }

        composable<Routes.UserRegistrationScreen> {
            UserRegistrationScreen(navController)
        }

        composable<Routes.HomeScreen> {
            HomeScreen()
        }

        composable<Routes.UpdateScreen> {
            UpdateScreen()
        }

        composable<Routes.CommunitiesScreen> {
            CommunityScreen()
        }

        composable<Routes.CallScreen> {
            CallScreen()
        }

        composable <Routes.UserProfileSetScreen>{
            UserProfileSetScreen(navHostController = navController)
        }
    }
}