package com.example.myapplication

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.retrofit.MainViewModel
import com.example.myapplication.retrofit.home
import com.example.myapplication.retrofit.sayfa_Gecis
import com.example.myapplication.retrofit.sayfa_gec
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {


            val view : MainViewModel by viewModels()


            val navController = rememberNavController()


            MyApplicationTheme {
                //LogInScreen(view)
                sayfa_gec(navController,view)
                //LoadingScreen()
                //coin(view)
                //home(view)
                //sayfa_Gecis(navController,view)
            }
            }
        }
    }


