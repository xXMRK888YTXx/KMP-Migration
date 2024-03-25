package com.xxmrk888ytxx.calculatorscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xxmrk888ytxx.calculatorscreen.models.CalculatorButtonModel
import com.xxmrk888ytxx.calculatorscreen.models.CalculatorInputType
import com.xxmrk888ytxx.calculatorscreen.models.CalculatorResult
import com.xxmrk888ytxx.calculatorscreen.models.LocalUiEvent
import com.xxmrk888ytxx.calculatorscreen.models.ScreenState
import com.xxmrk888ytxx.shared.LocalComposeUiController
import com.xxmrk888ytxx.shared.mvi.UiEvent

@SuppressLint("ResourceType")
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CalculatorScreen(
    screenState: ScreenState,
    onEvent: (UiEvent) -> Unit,
) {

    val composeUiController = LocalComposeUiController.current

    val calculatorFieldColor = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.onSecondary
    else MaterialTheme.colorScheme.primary.copy(0.2f)


    LaunchedEffect(key1 = Unit, block = {
        composeUiController.setupStatusBar(calculatorFieldColor)
    })

    DisposableEffect(key1 = Unit, effect = {
        onDispose { composeUiController.resetStatusBarColor() }
    })

    var buttonContainerWidth by rememberSaveable { mutableIntStateOf(0) }

    var uiContainerSize by rememberSaveable { mutableIntStateOf(0) }

    val calculationFieldWeight by remember(buttonContainerWidth, uiContainerSize) {
        mutableFloatStateOf(
            maxOf((uiContainerSize - buttonContainerWidth).toFloat(), 1f)
        ).also { println("buttonContainerWidth:$buttonContainerWidth uiContainerSize:$uiContainerSize  res:$it") }
    }

    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = screenState.calculatorInput, block = {
        scrollState.animateScrollTo(scrollState.maxValue)
    })

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .onSizeChanged {
                uiContainerSize = it.width
            },
    ) { paddings ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddings)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(calculationFieldWeight)
                    .clip(
                        RoundedCornerShape(
                            bottomStart = 35.dp,
                            bottomEnd = 35.dp
                        )
                    )
                    .background(
                        calculatorFieldColor
                    ),

                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End,
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            start = 5.dp,
                            end = 5.dp
                        ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End,
                ) {

                    val errorColor =
                        if (isSystemInDarkTheme()) MaterialTheme.colorScheme.errorContainer else MaterialTheme.colorScheme.error


                    Text(
                        text = screenState.calculatorInput,

                        style = MaterialTheme.typography.displayLarge.copy(
                            fontSize = 82.sp,
                            color = if (screenState.mathResult !is CalculatorResult.Error) MaterialTheme.colorScheme.primary else errorColor,
                            textAlign = TextAlign.End
                        ),
                        modifier = Modifier
                            .horizontalScroll(scrollState)
                        ,
                    )

                    Text(
                        text = when (screenState.mathResult) {
                            is CalculatorResult.Error -> screenState.mathResult.errorString

                            is CalculatorResult.Result -> screenState.mathResult.resultString

                            CalculatorResult.Stub -> String()
                        },
                        style = MaterialTheme.typography.displayLarge.copy(
                            fontSize = 50.sp,
                            color = (if (screenState.mathResult !is CalculatorResult.Error) MaterialTheme.colorScheme.primary else errorColor).copy(
                                0.7f
                            ),
                            textAlign = TextAlign.End
                        ),
                        modifier = Modifier
                            .align(Alignment.End)
                            .verticalScroll(rememberScrollState())
                    )
                }

            }

            FlowRow(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .onSizeChanged {
                        buttonContainerWidth = it.width
                    },
                maxItemsInEachRow = 4,
                horizontalArrangement = Arrangement.spacedBy(0.dp),
            ) {
                val numberButtonColor =
                    if (isSystemInDarkTheme()) MaterialTheme.colorScheme.tertiaryContainer else MaterialTheme.colorScheme.tertiary.copy(
                        0.8f
                    )
                val actionButtonColor =
                    if (isSystemInDarkTheme()) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.secondary.copy(
                        0.8f
                    )
                val equalsButtonColor =
                    if (isSystemInDarkTheme()) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.primary.copy(
                        0.8f
                    )

                val items = remember {
                    provideCalculatorButtons(
                        numberButtonColor, actionButtonColor, equalsButtonColor, onEvent
                    )
                }


                items.forEach {
                    CalculatorButton(
                        modifier = Modifier
                            .weight(1f)
                            .padding(3.dp),
                        backgroundColor = it.color,
                        action = {
                            it.action()
                        }
                    ) {
                        val contentColor =
                            if (isSystemInDarkTheme()) MaterialTheme.colorScheme.secondary
                            else MaterialTheme.colorScheme.onSecondary

                        when (it) {
                            is CalculatorButtonModel.Text -> {
                                Text(
                                    text = it.text,
                                    style = MaterialTheme.typography.displaySmall.copy(
                                        color = contentColor
                                    ),
                                )
                            }

                            is CalculatorButtonModel.Icon -> {
                                Icon(
                                    painter = painterResource(id = it.iconId),
                                    contentDescription = "",
                                    tint = contentColor,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@SuppressLint("ResourceType")
private fun provideCalculatorButtons(
    numberButtonColor: Color,
    actionButtonColor: Color,
    equalsButtonColor: Color,
    onEvent: (UiEvent) -> Unit,
): List<CalculatorButtonModel> {
    return listOf<CalculatorButtonModel>(
        //First row
        CalculatorButtonModel.Icon(R.drawable.c, actionButtonColor) {
            onEvent(
                LocalUiEvent.CalculatorInput(CalculatorInputType.CLEAR)
            )
        },
        CalculatorButtonModel.Icon(
            R.drawable.exponentiation,
            actionButtonColor
        ) { onEvent(LocalUiEvent.CalculatorInput(CalculatorInputType.EXPONENTIATION)) },
        CalculatorButtonModel.Icon(
            R.drawable.pi,
            actionButtonColor
        ) { onEvent(LocalUiEvent.CalculatorInput(CalculatorInputType.PI)) },
        CalculatorButtonModel.Icon(
            R.drawable.division,
            actionButtonColor
        ) { onEvent(LocalUiEvent.CalculatorInput(CalculatorInputType.DIVISION)) },
        //Second row
        CalculatorButtonModel.Text(
            "7",
            numberButtonColor
        ) { onEvent(LocalUiEvent.CalculatorInput(CalculatorInputType.SEVEN)) },
        CalculatorButtonModel.Text(
            "8",
            numberButtonColor
        ) { onEvent(LocalUiEvent.CalculatorInput(CalculatorInputType.EIGHT)) },
        CalculatorButtonModel.Text(
            "9",
            numberButtonColor
        ) { onEvent(LocalUiEvent.CalculatorInput(CalculatorInputType.NINE)) },
        CalculatorButtonModel.Icon(
            R.drawable.multiplication,
            actionButtonColor
        ) { onEvent(LocalUiEvent.CalculatorInput(CalculatorInputType.MULTIPLICATION)) },
        //Tertiary row
        CalculatorButtonModel.Text(
            "4",
            numberButtonColor
        ) { onEvent(LocalUiEvent.CalculatorInput(CalculatorInputType.FOUR)) },
        CalculatorButtonModel.Text(
            "5",
            numberButtonColor
        ) { onEvent(LocalUiEvent.CalculatorInput(CalculatorInputType.FIVE)) },
        CalculatorButtonModel.Text(
            "6",
            numberButtonColor
        ) { onEvent(LocalUiEvent.CalculatorInput(CalculatorInputType.SIX)) },
        CalculatorButtonModel.Icon(R.drawable.minus, actionButtonColor) {
            onEvent(
                LocalUiEvent.CalculatorInput(CalculatorInputType.MINUS)
            )
        },
        //Fourth row
        CalculatorButtonModel.Text(
            "1",
            numberButtonColor
        ) { onEvent(LocalUiEvent.CalculatorInput(CalculatorInputType.ONE)) },
        CalculatorButtonModel.Text(
            "2",
            numberButtonColor
        ) { onEvent(LocalUiEvent.CalculatorInput(CalculatorInputType.TWO)) },
        CalculatorButtonModel.Text(
            "3",
            numberButtonColor
        ) { onEvent(LocalUiEvent.CalculatorInput(CalculatorInputType.THREE)) },
        CalculatorButtonModel.Icon(R.drawable.plus, actionButtonColor) {
            onEvent(
                LocalUiEvent.CalculatorInput(CalculatorInputType.PLUS)
            )
        },
        //Fiftieth row
        CalculatorButtonModel.Text(
            "0",
            numberButtonColor
        ) { onEvent(LocalUiEvent.CalculatorInput(CalculatorInputType.ZERO)) },
        CalculatorButtonModel.Text(
            ".",
            numberButtonColor
        ) { onEvent(LocalUiEvent.CalculatorInput(CalculatorInputType.POINT)) },
        CalculatorButtonModel.Icon(
            R.drawable.baseline_backspace_24,
            numberButtonColor
        ) { onEvent(LocalUiEvent.CalculatorInput(CalculatorInputType.REMOVE_SYMBOL)) },
        CalculatorButtonModel.Icon(R.drawable.equals, equalsButtonColor) {
            onEvent(
                LocalUiEvent.CalculatorInput(CalculatorInputType.EQUALS)
            )
        },
    )
}

@Composable
fun CalculatorButton(
    modifier: Modifier,
    backgroundColor: Color,
    action: () -> Unit,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = action,
            colors = ButtonDefaults.buttonColors(
                containerColor = backgroundColor
            ),
            shape = CircleShape,
            modifier = Modifier.size(85.dp),
        ) {
            content()
        }
    }
}


@Preview
@Composable
fun CalculatorButtonPrev() {

}