package com.example.whatsappclone.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute // <--- THIS WAS MISSING
import com.example.whatsappclone.presentation.callscreen.CallScreen
import com.example.whatsappclone.presentation.communityscreen.CommunityScreen
import com.example.whatsappclone.presentation.homescreen.HomeScreen
import com.example.whatsappclone.presentation.profile.UserProfileSetScreen
import com.example.whatsappclone.presentation.splashscreen.SplashScreen
import com.example.whatsappclone.presentation.updatescreen.UpdateScreen
import com.example.whatsappclone.presentation.userregistrationscreen.UserRegistrationScreen
import com.example.whatsappclone.presentation.welcomescreen.WelcomeScreen
import com.example.whatsappclone.presentation.viewmodel.BaseViewModel

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
            val viewModel = hiltViewModel<BaseViewModel>()
            HomeScreen(navHostController = navController, homeBaseViewModel = viewModel)
        }

        composable<Routes.UpdateScreen> {
            // If UpdateScreen(navController) gives an error, use UpdateScreen()
            // until you update that file to accept arguments.
            UpdateScreen(navHostController = navController)
        }

        composable<Routes.CommunitiesScreen> {
            CommunityScreen(navHostController = navController)
        }

        composable<Routes.CallScreen> {
            CallScreen(navHostController = navController)
        }

        composable<Routes.UserProfileSetScreen> {
            UserProfileSetScreen(navHostController = navController)
        }

        composable<Routes.ChatDetailScreen> { backStackEntry ->
            val data = backStackEntry.toRoute<Routes.ChatDetailScreen>()
            // Now you can use data.phoneNumber
        }
    }
}