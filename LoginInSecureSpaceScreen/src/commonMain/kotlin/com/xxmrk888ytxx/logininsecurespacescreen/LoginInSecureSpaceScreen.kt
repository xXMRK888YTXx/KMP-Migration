package com.xxmrk888ytxx.logininsecurespacescreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.xxmrk888ytxx.logininsecurespacescreen.models.LocalUiEvent
import com.xxmrk888ytxx.logininsecurespacescreen.models.ScreenState
import com.xxmrk888ytxx.shared.LocalNavigator
import com.xxmrk888ytxx.shared.mvi.UiEvent
import dev.icerock.moko.resources.compose.stringResource
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class, ExperimentalFoundationApi::class)
@Composable
fun LoginInSecureSpaceScreen(
    screenState: ScreenState,
    onEvent: (UiEvent) -> Unit,
) {
    val navigator = LocalNavigator.current

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddings ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddings),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .heightIn(min = 150.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(
                        value = screenState.enteredPassword,
                        onValueChange = {
                            onEvent(LocalUiEvent.InputEvent(it))
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text(
                                text = stringResource(MR.strings.password_of_secure_space),
                                style = MaterialTheme.typography.bodyLarge
                            )
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                onEvent(
                                    LocalUiEvent.UnlockEvent(
                                        navigator,
                                        snackbarHostState
                                    )
                                )
                            }
                        )
                    )

                    Spacer(
                        modifier = Modifier.height(15.dp)
                    )

                    Button(onClick = {
                        onEvent(
                            LocalUiEvent.UnlockEvent(
                                navigator,
                                snackbarHostState
                            )
                        )
                    }) {
                        Text(text = stringResource(MR.strings.unlock))
                    }


                }
            }

        }
    }
}