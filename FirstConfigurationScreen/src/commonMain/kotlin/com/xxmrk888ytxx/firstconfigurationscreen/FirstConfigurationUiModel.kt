package com.xxmrk888ytxx.firstconfigurationscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.text.input.TextFieldValue
import com.xxmrk888ytxx.firstconfigurationscreen.contracts.FinishConfigurationContract
import com.xxmrk888ytxx.firstconfigurationscreen.contracts.SetupCalculatorPasswordContract
import com.xxmrk888ytxx.firstconfigurationscreen.contracts.SetupSecureSpacePasswordContract
import com.xxmrk888ytxx.firstconfigurationscreen.models.LocalUiEvent
import com.xxmrk888ytxx.firstconfigurationscreen.models.ScreenState
import com.xxmrk888ytxx.firstconfigurationscreen.models.ScreenType
import com.xxmrk888ytxx.shared.getWithCast
import com.xxmrk888ytxx.shared.mvi.MultiplatformUiModel
import com.xxmrk888ytxx.shared.mvi.UiEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FirstConfigurationUiModel(
    private val setupCalculatorPasswordContract: SetupCalculatorPasswordContract,
    private val setupSecureSpacePasswordContract: SetupSecureSpacePasswordContract,
    private val finishConfigurationContract: FinishConfigurationContract,
) : MultiplatformUiModel<ScreenState>() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onNewEvent(event: UiEvent) {
        if (event !is LocalUiEvent) return

        when (event) {
            is LocalUiEvent.PasswordFromCalculatorInput -> {
                if (screenTypeState.value != ScreenType.CALCULATION_PASSWORD_SETUP) return

                if (event.text.isCalculatorPasswordValid()) {
                    passwordFromCalculatorState.update { event.text }
                }
            }

            is LocalUiEvent.RepeatPasswordFromCalculatorInput -> {
                if (screenTypeState.value != ScreenType.CALCULATION_PASSWORD_SETUP) return

                if (event.text.isCalculatorPasswordValid()) {
                    repeatPasswordFromCalculatorState.update { event.text }
                }
            }

            is LocalUiEvent.ToSecureSpaceSetup -> {
                event.scope.launch {
                    screenTypeState.update { ScreenType.SECURE_SPACE_PASSWORD_SETUP }

                    event.pager.animateScrollToPage(event.pager.currentPage + 1)
                }
            }

            is LocalUiEvent.PasswordFromSecureSpace -> {
                if (screenTypeState.value != ScreenType.SECURE_SPACE_PASSWORD_SETUP) return

                passwordOfSecureSpaceState.update { event.text }
            }

            is LocalUiEvent.RepeatPasswordFromSecureSpace -> {
                if (screenTypeState.value != ScreenType.SECURE_SPACE_PASSWORD_SETUP) return

                repeatPasswordOfSecureSpace.update { event.text }
            }

            is LocalUiEvent.FinalConfigurationEvent -> {
                uiModelScope.launch {

                    isLoadingState.update { true }

                    setupCalculatorPasswordContract.setupPassword(passwordFromCalculatorState.value.text)
                    setupSecureSpacePasswordContract.setupPassword(passwordOfSecureSpaceState.value.text)

                    finishConfigurationContract.finishConfiguration(event.navigator)
                }
            }
        }
    }

    private val screenTypeState = MutableStateFlow(ScreenType.CALCULATION_PASSWORD_SETUP)

    private val passwordFromCalculatorState = MutableStateFlow(TextFieldValue())

    private val repeatPasswordFromCalculatorState = MutableStateFlow(TextFieldValue())

    private val passwordOfSecureSpaceState = MutableStateFlow(TextFieldValue())

    private val repeatPasswordOfSecureSpace = MutableStateFlow(TextFieldValue())

    private val isLoadingState = MutableStateFlow(false)

    override val state: Flow<ScreenState> = combine(
        screenTypeState,
        passwordFromCalculatorState,
        repeatPasswordFromCalculatorState,
        passwordOfSecureSpaceState,
        repeatPasswordOfSecureSpace,
        isLoadingState
    ) { flowArray: Array<Any> ->

        ScreenState(
            screenType = flowArray.getWithCast(0),
            passwordFromCalculator = flowArray.getWithCast(1),
            repeatPasswordFromCalculator = flowArray.getWithCast(2),
            passwordOfSecureSpace = flowArray.getWithCast(3),
            repeatPasswordOfSecureSpace = flowArray.getWithCast(4),
            isLoading = flowArray.getWithCast(5)
        ).also {
            cashedScreenState = it
        }
    }

    private var cashedScreenState = ScreenState()

    override val defaultValue: ScreenState
        get() = cashedScreenState

    private fun TextFieldValue.isCalculatorPasswordValid(): Boolean {
        if (text.length > 10) return false

        var isPointAlreadyBeen = false

        text.forEach {
            when {
                it == '.' -> {
                    if (isPointAlreadyBeen) return false

                    isPointAlreadyBeen = true
                }

                !it.isDigit() -> return false
            }
        }

        return true
    }




}