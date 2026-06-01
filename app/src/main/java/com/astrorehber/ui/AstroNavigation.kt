package com.astrorehber.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.astrorehber.data.UserPreferences
import com.astrorehber.data.UserProfile
import com.astrorehber.ui.chart.ProfileScreen
import com.astrorehber.ui.events.EventsScreen
import com.astrorehber.ui.home.HomeScreen
import com.astrorehber.ui.onboarding.OnboardingScreen
import com.astrorehber.ui.theme.StarGold
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

private object Routes {
    const val ONBOARDING = "onboarding"
    const val HOME = "home"
    const val PROFILE = "profile"
    const val EVENTS = "events"
    const val EDIT = "edit"
}

@Composable
fun AstroNavigation() {
    val context = LocalContext.current
    val prefs = remember { UserPreferences(context.applicationContext) }
    val scope = rememberCoroutineScope()

    var loaded by remember { mutableStateOf(false) }
    var profile by remember { mutableStateOf<UserProfile?>(null) }

    LaunchedEffect(Unit) {
        profile = prefs.profile.first()
        loaded = true
    }

    if (!loaded) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = StarGold)
        }
        return
    }

    val nav = rememberNavController()
    val startRoute = if (profile == null) Routes.ONBOARDING else Routes.HOME

    NavHost(navController = nav, startDestination = startRoute) {
        composable(Routes.ONBOARDING) {
            OnboardingScreen(
                initial = null,
                onSubmit = { data ->
                    scope.launch {
                        prefs.save(data)
                        profile = data
                        nav.navigate(Routes.HOME) {
                            popUpTo(Routes.ONBOARDING) { inclusive = true }
                            launchSingleTop = true
                        }
                    }
                }
            )
        }
        composable(Routes.EDIT) {
            val current = profile
            if (current != null) {
                OnboardingScreen(
                    initial = current,
                    onCancel = { nav.popBackStack() },
                    onSubmit = { data ->
                        scope.launch {
                            prefs.save(data)
                            profile = data
                            nav.popBackStack()
                        }
                    }
                )
            }
        }
        composable(Routes.HOME) {
            val current = profile
            if (current != null) {
                HomeScreen(
                    profile = current,
                    onOpenChart = { nav.navigate(Routes.PROFILE) },
                    onOpenEvents = { nav.navigate(Routes.EVENTS) },
                    onEditProfile = { nav.navigate(Routes.EDIT) },
                    onResetProfile = {
                        scope.launch {
                            prefs.clear()
                            profile = null
                            nav.navigate(Routes.ONBOARDING) {
                                popUpTo(Routes.HOME) { inclusive = true }
                                launchSingleTop = true
                            }
                        }
                    }
                )
            }
        }
        composable(Routes.PROFILE) {
            val current = profile
            if (current != null) {
                ProfileScreen(profile = current, onBack = { nav.popBackStack() })
            }
        }
        composable(Routes.EVENTS) {
            EventsScreen(onBack = { nav.popBackStack() })
        }
    }
}
