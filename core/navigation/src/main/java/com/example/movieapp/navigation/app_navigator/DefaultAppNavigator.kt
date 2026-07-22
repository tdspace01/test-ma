package com.example.movieapp.navigation.app_navigator

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

class DefaultAppNavigator(
    private val backStack: NavBackStack<NavKey>,
    private val navDebounceMs: Long = 400L
) : AppNavigator {

    private var lastNavTime = 0L

    private inline fun withDebounce(action: () -> Unit) {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastNavTime >= navDebounceMs) {
            lastNavTime = currentTime
            action()
        }
    }

    override fun navigateTo(route: NavKey) {
        withDebounce {
            if (backStack.lastOrNull() != route) {
                backStack.add(route)
            }
        }
    }

    override fun clearAndNavigate(route: NavKey) {
        withDebounce {
            backStack.clear()
            backStack.add(route)
        }
    }

    override fun navigateBack() {
        withDebounce {
            if (backStack.size > 1) {
                backStack.removeLastOrNull()
            }
        }
    }
}