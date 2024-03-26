package com.xxmrk888ytxx.securespace.presentation.navigation

import com.xxmrk888ytxx.calculatorscreen.CalculatorViewModel
import com.xxmrk888ytxx.firstconfigurationscreen.FirstConfigurationUiModel
import com.xxmrk888ytxx.logininsecurespacescreen.LoginInSecureSpaceViewModel
import com.xxmrk888ytxx.securespace.presentation.navigation.destination.CalculatorDestination
import com.xxmrk888ytxx.securespace.presentation.navigation.destination.EnterToSecureSpaceDestination
import com.xxmrk888ytxx.securespace.presentation.navigation.destination.FirstConfigurationDestination
import com.xxmrk888ytxx.securespace.presentation.navigation.destination.MainSecureSpaceDestination
import com.xxmrk888ytxx.securespacemainscreen.SecureSpaceMainViewModel
import org.koin.java.KoinJavaComponent.inject

class DestinationFactoryImpl : DestinationFactory {
    override suspend fun createDestination(id: Any): Destination {
        return when (id) {
            FirstConfigurationDestination.id -> {

                val firstConfigurationUiModel by inject<FirstConfigurationUiModel>(
                    FirstConfigurationUiModel::class.java
                )

                FirstConfigurationDestination(firstConfigurationUiModel)
            }

            CalculatorDestination.id ->  {
                val calculatorViewModel by inject<CalculatorViewModel>(CalculatorViewModel::class.java)
                CalculatorDestination(calculatorViewModel)
            }

            EnterToSecureSpaceDestination.id -> {
                val loginInSecureSpaceViewModel by inject<LoginInSecureSpaceViewModel>(LoginInSecureSpaceViewModel::class.java)
                EnterToSecureSpaceDestination(loginInSecureSpaceViewModel)
            }

            MainSecureSpaceDestination.id -> {
                val viewModel by inject<SecureSpaceMainViewModel>(SecureSpaceMainViewModel::class.java)
                MainSecureSpaceDestination(viewModel)
            }

            else -> error("Destination with id = $id not found")
        }
    }
}