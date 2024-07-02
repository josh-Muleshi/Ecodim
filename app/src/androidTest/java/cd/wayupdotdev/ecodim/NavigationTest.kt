package cd.wayupdotdev.ecodim

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import cd.wayupdotdev.ecodim.core.ui.AppNavHost
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var navController: TestNavHostController

    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            AppNavHost(modifier = Modifier, navController = navController, isDarkTheme = true){}
        }
    }

    @Test
    fun appNavHost_verifyStartDestination() {
        composeTestRule
            .onNodeWithText("Start Screen")
            .assertIsDisplayed()
    }

    @Test
    fun appNavHost_clickHome_navigateToHome() {
        composeTestRule.onNodeWithText("Start Screen")
            .performClick()

        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, "home")
    }
}

