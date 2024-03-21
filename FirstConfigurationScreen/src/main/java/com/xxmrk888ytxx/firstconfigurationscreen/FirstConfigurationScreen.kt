package com.xxmrk888ytxx.firstconfigurationscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.xxmrk888ytxx.corecompose.LocalNavigator
import com.xxmrk888ytxx.firstconfigurationscreen.models.LocalUiEvent
import com.xxmrk888ytxx.firstconfigurationscreen.models.ScreenState
import com.xxmrk888ytxx.firstconfigurationscreen.models.ScreenType
import com.xxmrk888ytxx.shared.mvi.UiEvent

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FirstConfigurationScreen(
    screenState: ScreenState,
    onEvent: (UiEvent) -> Unit,
) {

    val pages = remember {
        ScreenType.entries
    }

    val pager = rememberPagerState { pages.size }

    val scope = rememberCoroutineScope()

    val navigator = LocalNavigator.current

    val isNextAvailable = remember(screenState) {
        when (screenState.screenType) {
            ScreenType.CALCULATION_PASSWORD_SETUP -> screenState.passwordFromCalculator == screenState.repeatPasswordFromCalculator &&
                    screenState.passwordFromCalculator.isNotEmpty()
                    && screenState.repeatPasswordFromCalculator.isNotEmpty()
                    && screenState.passwordFromCalculator.lastOrNull() != '.'
                    && screenState.repeatPasswordFromCalculator.lastOrNull() != '.'

            ScreenType.SECURE_SPACE_PASSWORD_SETUP -> screenState.passwordOfSecureSpace == screenState.repeatPasswordOfSecureSpace
                    && screenState.passwordOfSecureSpace.isNotEmpty()
                    && screenState.repeatPasswordOfSecureSpace.isNotEmpty()
                    && screenState.passwordOfSecureSpace.length >= 8

            else -> true
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            Button(
                onClick = {
                    when(screenState.screenType) {
                        ScreenType.CALCULATION_PASSWORD_SETUP -> {
                            onEvent(LocalUiEvent.ToSecureSpaceSetup(pager,scope))
                        }

                        ScreenType.SECURE_SPACE_PASSWORD_SETUP -> { onEvent(LocalUiEvent.FinalConfigurationEvent(navigator)) }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                enabled = isNextAvailable
            ) {
                Text(
                    text = if(screenState.screenType != ScreenType.SECURE_SPACE_PASSWORD_SETUP) stringResource(R.string.next)  else stringResource(
                        R.string.finish
                    )
                )
            }
        }
    ) { paddings ->
        HorizontalPager(
            state = pager,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddings)
        ) {
            when (screenState.screenType) {
                ScreenType.CALCULATION_PASSWORD_SETUP -> CalculationPasswordSetupScreenType(
                    password = screenState.passwordFromCalculator,
                    repeatPassword = screenState.repeatPasswordFromCalculator,
                    onPasswordChanged = { onEvent(LocalUiEvent.PasswordFromCalculatorInput(it)) },
                    onRepeatPasswordChanged = {
                        onEvent(
                            LocalUiEvent.RepeatPasswordFromCalculatorInput(
                                it
                            )
                        )
                    }
                )


                ScreenType.SECURE_SPACE_PASSWORD_SETUP -> SecureSpacePasswordSetupScreenType(
                    password = screenState.passwordOfSecureSpace,
                    repeatPassword = screenState.repeatPasswordOfSecureSpace,
                    onPasswordChanged = { onEvent(LocalUiEvent.PasswordFromSecureSpace(it)) },
                    onRepeatPasswordChanged = { onEvent(LocalUiEvent.RepeatPasswordFromSecureSpace(it)) }
                )
            }
        }
    }

    if(screenState.isLoading) {
        LoadingDialog()
    }
}

@Composable
fun SecureSpacePasswordSetupScreenType(
    password: String,
    repeatPassword: String,
    onPasswordChanged: (String) -> Unit,
    onRepeatPasswordChanged: (String) -> Unit,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.safe_anim))

    val progress by animateLottieCompositionAsState(
        composition,
        restartOnPlay = true,
        speed = 0.5f,
        iterations = Int.MAX_VALUE,
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.setup_password_of_secure_space),
            style = MaterialTheme.typography.titleLarge
        )

        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .size(200.dp)
        )

        Text(
            text = stringResource(R.string.password_must_contain_at_least_8_characters),
            style = MaterialTheme.typography.bodyLarge
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            StyledTextField(
                text = password,
                onTextChanged = onPasswordChanged,
                label = stringResource(R.string.enter_password),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                ),
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
            )

            StyledTextField(
                text = repeatPassword,
                onTextChanged = onRepeatPasswordChanged,
                label = stringResource(R.string.repeat_password),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
            )
        }
    }
}


@Composable
private fun StyledTextField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChanged: (String) -> Unit,
    label: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    OutlinedTextField(
        value = text,
        onValueChange = onTextChanged,
        singleLine = true,
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyLarge
            )
        },
        keyboardOptions = keyboardOptions,
        modifier = modifier,
        visualTransformation = visualTransformation
    )
}

@Composable
fun CalculationPasswordSetupScreenType(
    password: String,
    repeatPassword: String,
    onPasswordChanged: (String) -> Unit,
    onRepeatPasswordChanged: (String) -> Unit,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.calculator_anim))

    val progress by animateLottieCompositionAsState(
        composition,
        restartOnPlay = true,
        speed = 0.5f,
        iterations = Int.MAX_VALUE,
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.you_must_create_a_password_to_open_the_secure_space_from_the_calculator),
            style = MaterialTheme.typography.titleLarge
        )

        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .size(200.dp)
        )

        Text(
            text = stringResource(R.string.the_password_must_consist_of_numbers_0_9_and_can_consist_of_a_single_dot_the_maximum_password_length_is_10_characters),
            style = MaterialTheme.typography.bodyLarge
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            StyledTextField(
                text = password,
                onTextChanged = onPasswordChanged,
                label = stringResource(R.string.enter_password),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.fillMaxWidth()
            )

            StyledTextField(
                text = repeatPassword,
                onTextChanged = onRepeatPasswordChanged,
                label = stringResource(R.string.repeat_password),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun LoadingDialog() {
    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.large,
            tonalElevation = AlertDialogDefaults.TonalElevation
        ) {
            Row(
                Modifier.fillMaxWidth().padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp,Alignment.CenterHorizontally)
            ) {
                CircularProgressIndicator()

                Text(text = stringResource(R.string.please_wait))
            }
        }
    }
}
