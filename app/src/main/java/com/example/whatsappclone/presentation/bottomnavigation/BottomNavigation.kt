package com.example.whatsappclone.presentation.bottomnavigation

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.whatsappclone.R
import com.example.whatsappclone.presentation.navigation.Routes

@Composable
fun BottomNavigation(navController: NavController) {

    // 1. Get the current Route to know which tab to highlight
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // 2. Define your navigation items
    val items = listOf(
        BottomNavItem(
            label = "Chats",
            icon = R.drawable.message_4475881, // Ensure this ID exists
            route = Routes.HomeScreen
        ),
        BottomNavItem(
            label = "Updates",
            icon = R.drawable.update_icon,
            route = Routes.UpdateScreen
        ),
        BottomNavItem(
            label = "Communities",
            icon = R.drawable.communities_icon,
            route = Routes.CommunitiesScreen
        ),
        BottomNavItem(
            label = "Calls",
            icon = R.drawable.telephone,
            route = Routes.CallScreen
        )
    )

    // 3. Render the Navigation Bar
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 12.dp
    ) {
        items.forEach { item ->
            // Check if this item is selected based on current route
            // Note: Since Routes are objects/classes, we compare the class names or use a helper
            val isSelected = currentRoute == item.route::class.qualifiedName

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.label,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        fontSize = 12.sp
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Black, // Active Icon Color
                    selectedTextColor = Color.Black, // Active Text Color
                    indicatorColor = colorResource(R.color.light_green).copy(alpha = 0.2f), // Green bubble background
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray
                )
            )
        }
    }
}

// Data class helper for the list above
data class BottomNavItem(
    val label: String,
    val icon: Int,
    val route: Any // Routes can be objects or data classes
)