package com.example.newcalculetor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.newcalculetor.screens.MainPage
import com.example.newcalculetor.ui.theme.NewCalculetorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewCalculetorTheme (
                darkTheme = true
            ) {
                MainPage()
            }
        }
    }
}