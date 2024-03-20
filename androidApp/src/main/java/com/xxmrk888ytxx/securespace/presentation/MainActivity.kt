package com.xxmrk888ytxx.securespace.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.xxmrk888ytxx.calculatorscreen.CalculatorViewModel
import com.xxmrk888ytxx.firstconfigurationscreen.FirstConfigurationViewModel
import com.xxmrk888ytxx.securespace.extensions.appComponent
import com.xxmrk888ytxx.securespace.extensions.setContentWithTheme
import com.xxmrk888ytxx.logininsecurespacescreen.LoginInSecureSpaceViewModel
import com.xxmrk888ytxx.securespace.domain.FirstStartStateHolder.FirstStartStateHolder
import com.xxmrk888ytxx.securespacemainscreen.SecureSpaceMainViewModel
import javax.inject.Inject
import javax.inject.Provider

internal class MainActivity : ComponentActivity() {

    @Inject
    lateinit var activityViewModelFactory: ActivityViewModel.Factory

    @Inject
    lateinit var calculatorViewModel: Provider<CalculatorViewModel>

    @Inject
    lateinit var loginInSecureSpaceViewModel: Provider<LoginInSecureSpaceViewModel>

    @Inject
    lateinit var firstConfigurationViewModel: Provider<FirstConfigurationViewModel>

    @Inject
    lateinit var firstStartStateHolder: FirstStartStateHolder

    @Inject
    lateinit var secureSpaceMainViewModel: Provider<SecureSpaceMainViewModel>

    private val activityViewModel by viewModels<ActivityViewModel> { activityViewModelFactory }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        setContentWithTheme {

            val isFirstStart by firstStartStateHolder.isFirstStartOfApplication.collectAsStateWithLifecycle(
                initialValue = false
            )

            val navController = rememberNavController()

            LaunchedEffect(key1 = navController, block = {
                activityViewModel.navController = navController
            })

            NavigationHost(
                navController = navController,
                navigator = activityViewModel,
                startDestination = if(isFirstStart) Screen.FirstConfigurationScreen.route
                else Screen.CalculatorScreen.route
            ) {
                firstConfigurationScreen(firstConfigurationViewModel)

                calculatorScreen(calculatorViewModel)

                loginInSecureSpaceScreen(loginInSecureSpaceViewModel)

                secureSpaceMainScreen(secureSpaceMainViewModel)
            }
        }
    }
}