package com.example.newcalculetor.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newcalculetor.calculator.CalculateViewModel

@Composable
fun Buttons(
    text: String,
    onClick: () -> Unit,
    widthFactor: Float,
    borderColor: Color = MaterialTheme.colorScheme.tertiary,
    subBorderColor: Color = Color.Transparent
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    var buttonWidth = ((screenWidth / 4) - ( 2.dp * 5)) * widthFactor
    val buttonHeight = ((screenHeight / 2)/5) - ( 2.dp * 4)

    if (widthFactor == 2f){
        buttonWidth += (2.dp * 4)
    }

    OutlinedButton(
        onClick = { onClick() },
        border = BorderStroke(
            width = 4.dp,
            color = borderColor
        ),
        shape = RoundedCornerShape(30),
        modifier = Modifier
            .width(buttonWidth)
            .height(buttonHeight)
            .drawBehind {
                drawLine(
                    color = subBorderColor,
                    start = Offset(20f, size.height - 20),
                    end = Offset(size.width - 20, size.height - 20),
                    strokeWidth = 10f
                )
            }

    ) {
        Text(
            text = text,
            fontSize = MaterialTheme.typography.titleLarge.fontSize
        )
    }
}

@Composable
fun Keyboard(viewModel: CalculateViewModel) {
    val buttonRows = listOf(
        listOf("√", "^"),
        listOf("AC", "ans", "C", "÷"),
        listOf("1", "2", "3", "×"),
        listOf("4", "5", "6", "-"),
        listOf("7", "8", "9", "+"),
        listOf(".", "0", "=")
    )

    Column(
        modifier = Modifier
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Bottom
    ) {
        buttonRows.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                row.forEach { text ->
                    Spacer(Modifier.padding(start = 8.dp))
                    Buttons(
                        text = text,
                        onClick = {
                            when (text) {
                                "AC" -> viewModel.deleteAll()
                                "C" -> viewModel.deleteLast()
                                "=" -> viewModel.calculate()
                                else -> {
                                    viewModel.appendToEquation(text)
                                    viewModel.appendToEquationCalculate(text)
                                }
                            }
                        },
                        widthFactor = if (text == "=") 2f else 1f,
                        borderColor = (
                                if (text == "√" || text == "^")
                                    Color.Transparent
                                else
                                    MaterialTheme.colorScheme.tertiary
                            ),
                        subBorderColor = (
                                if (text == "√" || text == "^")
                                    MaterialTheme.colorScheme.tertiary
                                else
                                    Color.Transparent
                            )
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun Screen(viewModel: CalculateViewModel) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(
                vertical = 30.dp,
                horizontal = 10.dp
            ),
        horizontalAlignment = Alignment.End
    ) {
        Text(
            text = viewModel.equacionParaUI,
            fontSize = 35.sp,
            color =(
                    if (viewModel.equacionParaUI == "Error")
                        MaterialTheme.colorScheme.error
                    else
                        MaterialTheme.colorScheme.primary
                    ),
            style = TextStyle(lineHeight = 45.sp),
            overflow = TextOverflow.Clip
        )
        Text(
            text = "= ${
                if (viewModel.result % 1 == 0.0f && viewModel.result < Int.MAX_VALUE) {

                    viewModel.result.toInt()
                    
                } else if (viewModel.result.toString().contains('E')) {

                    viewModel.result.toString().replace("E", "x10^")
                    
                } else {
                    
                    viewModel.result
                    
                }
            }",
            color = (
                    if (viewModel.equacionParaUI == "Error")
                        MaterialTheme.colorScheme.error
                    else
                        MaterialTheme.colorScheme.primary
                    ),
            fontSize = 35.sp
        )
    }
}

@Composable
fun MainPage() {
    val viewModel = remember { CalculateViewModel() }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            Screen(viewModel)
            Keyboard(viewModel)
        }
    }
}
