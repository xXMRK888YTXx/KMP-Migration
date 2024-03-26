package com.xxmrk888ytxx.securespace.presentation.navigation

import com.xxmrk888ytxx.firstconfigurationscreen.FirstConfigurationUiModel
import com.xxmrk888ytxx.securespace.presentation.navigation.destination.CalculatorDestination
import com.xxmrk888ytxx.securespace.presentation.navigation.destination.FirstConfigurationDestination
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
                CalculatorDestination()
            }

            else -> error("Destination with id = $id not found")
        }
    }
}