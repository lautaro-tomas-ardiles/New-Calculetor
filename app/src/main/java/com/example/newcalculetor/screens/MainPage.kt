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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newcalculetor.calculator.calculate
import com.example.newcalculetor.ui.theme.NewCalculetorTheme

var equacion by mutableStateOf("")
var problema = ""
var result by mutableDoubleStateOf(0.0)

@Composable
fun Buttons(text: String, onClick: () -> Unit, widthFactor: Float) {

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
            color = MaterialTheme.colorScheme.tertiary
        ),
        shape = RoundedCornerShape(30),
        modifier = Modifier
            .width(buttonWidth)
            .height(buttonHeight)
    ) {
        Text(
            text = text,
            //fontSize = 28.sp,
            fontSize= MaterialTheme.typography.titleLarge.fontSize
        )
    }
}

@Composable
fun Keyboard() {
    Column(
        modifier = Modifier
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Bottom
    ) {

        Row{
            Spacer(modifier = Modifier.padding(start = 8.dp))

            Buttons(
                text = "AC",
                onClick = {
                    equacion = ""
                    problema = ""
                    result = 0.0
                },
                widthFactor = 1f
            )
            Spacer(modifier = Modifier.padding(start = 8.dp))

            Buttons(
                text = "ans",
                onClick = {
                    equacion += "ans"
                    problema += "$result"
                },
                widthFactor = 1f
            )
            Spacer(modifier = Modifier.padding(start = 8.dp))

            Buttons(
                text = "C",
                onClick = {
                    if (equacion != "Error") {
                        equacion = equacion.dropLast(1)
                        problema = problema.dropLast(1)
                    }else {
                        equacion = ""
                        problema = ""
                    }
                },
                widthFactor = 1f
            )
            Spacer(modifier = Modifier.padding(start = 8.dp))

            Buttons(
                text = "÷",
                onClick = {
                    equacion += " ÷ "
                    problema += "/"
                },
                widthFactor = 1f
            )
            Spacer(modifier = Modifier.padding(end = 8.dp))
        }
        Spacer(modifier = Modifier.padding(bottom = 8.dp))

        Row{
            Spacer(modifier = Modifier.padding(start = 8.dp))

            Buttons(
                text = "1",
                onClick = {
                    equacion += "1"
                    problema += "1"
                },
                widthFactor = 1f
            )
            Spacer(modifier = Modifier.padding(start = 8.dp))

            Buttons(
                text = "2",
                onClick = {
                    equacion += "2"
                    problema += "2"
                },
                widthFactor = 1f
            )
            Spacer(modifier = Modifier.padding(start = 8.dp))

            Buttons(
                text = "3",
                onClick = {
                    equacion += "3"
                    problema += "3"
                },
                widthFactor = 1f
            )
            Spacer(modifier = Modifier.padding(start = 8.dp))

            Buttons(
                text = "×",
                onClick = {
                    equacion += " × "
                    problema += "*"
                },
                widthFactor = 1f
            )
            Spacer(modifier = Modifier.padding(end = 8.dp))
        }
        Spacer(modifier = Modifier.padding(bottom = 8.dp))

        Row{
            Spacer(modifier = Modifier.padding(start = 8.dp))

            Buttons(
                text = "4",
                onClick = {
                    equacion += "4"
                    problema += "4"
                },
                widthFactor = 1f
            )
            Spacer(modifier = Modifier.padding(start = 8.dp))

            Buttons(
                text = "5",
                onClick = {
                    equacion += "5"
                    problema += "5"
                },
                widthFactor = 1f
            )
            Spacer(modifier = Modifier.padding(start = 8.dp))

            Buttons(
                text = "6",
                onClick = {
                    equacion += "6"
                    problema += "6"
                },
                widthFactor = 1f
            )
            Spacer(modifier = Modifier.padding(start = 8.dp))

            Buttons(
                text = "-",
                onClick = {
                    equacion += " - "
                    problema += "-"
                },
                widthFactor = 1f
            )

            Spacer(modifier = Modifier.padding(end = 8.dp))
        }
        Spacer(modifier = Modifier.padding(bottom = 8.dp))

        Row{
            Spacer(modifier = Modifier.padding(start = 8.dp))

            Buttons(
                text = "7",
                onClick = {
                    equacion += "7"
                    problema += "7"
                    },
                widthFactor = 1f
            )
            Spacer(modifier = Modifier.padding(start = 8.dp))

            Buttons(
                text = "8",
                onClick = {
                    equacion += "8"
                    problema += "8"
                    },
                widthFactor = 1f
            )
            Spacer(modifier = Modifier.padding(start = 8.dp))

            Buttons(
                text = "9",
                onClick = {
                    equacion += "9"
                    problema += "9"
                    },
                widthFactor = 1f
            )
            Spacer(modifier = Modifier.padding(start = 8.dp))

            Buttons(
                text = "+",
                onClick = {
                    equacion += " + "
                    problema += "+"
                    },
                widthFactor = 1f
            )
            Spacer(modifier = Modifier.padding(end = 8.dp))
        }
        Spacer(modifier = Modifier.padding(bottom = 8.dp))

        Row{
            Spacer(modifier = Modifier.padding(start = 8.dp))

            Buttons(
                text = ".",
                onClick = {
                    equacion += "."
                    problema += "."
                    },
                widthFactor = 1f
            )
            Spacer(modifier = Modifier.padding(start = 8.dp))

            Buttons(
                text = "0",
                onClick = {
                    equacion += "0"
                    problema += "0"
                    },
                widthFactor = 1f
            )
            Spacer(modifier = Modifier.padding(start = 8.dp))

            Buttons(
                text = "=",
                onClick = {
                    equacion = ""
                    try {
                        if (
                            problema.contains('/') ||
                            problema.contains('*') ||
                            problema.contains('+') ||
                            problema.contains('-')
                        ) {
                            result = calculate(problema)
                            problema = ""
                        }
                    }catch (e: Exception){
                        equacion = "Error"
                        result = 0.0
                    }
                },
                widthFactor = 2f
            )
            Spacer(modifier = Modifier.padding(end = 8.dp))
        }
        Spacer(modifier = Modifier.padding(bottom = 8.dp))
    }
}

@Composable
fun Screen() {
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
            text = equacion,
            fontSize = 35.sp,
            color =
            if (equacion == "Error")
                MaterialTheme.colorScheme.error
            else
                MaterialTheme.colorScheme.primary
        )
        Text(
            text = "= ${if (result % 1 == 0.0) result.toInt() else result}",
            color =
            if (equacion == "Error")
                MaterialTheme.colorScheme.error
            else
                MaterialTheme.colorScheme.primary,
            fontSize = 35.sp
        )
    }
}

@Composable
fun MainPage() {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            Screen()
            Keyboard()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true,
    device = "spec:width=1080px,height=2340px,dpi=440"
)
@Composable
fun GreetingPreview() {
    NewCalculetorTheme (
        darkTheme = false
    ) {
        MainPage()
    }
}
