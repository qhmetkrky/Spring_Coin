package com.example.myapplication.retrofit

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.R
import com.example.myapplication.coin
import com.example.myapplication.ui.theme.LoadingScreen2

@Composable
fun home(view: MainViewModel) {

    val goldPrice by view.goldPrice.collectAsState()
    val font = FontFamily(Font(R.font.sedan))
    val options = listOf("Day", "Month", "Yearly")
    var selectedOption by remember { mutableStateOf("Month") }

    Column(
        modifier = Modifier.fillMaxSize()
            .background(Color(0xffFFFFFF))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = "Zamanlayıcı",
                modifier = Modifier
                    .size(24.dp)
                    .clickable { }
            )
            Spacer(modifier = Modifier.width(20.dp))
            Icon(
                imageVector = Icons.Outlined.Timer,
                contentDescription = "Bildirim",
                modifier = Modifier
                    .size(24.dp)
                    .clickable { }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // İkisini hizalamak için ortak bir Column
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 22.dp), // sol hizayı kontrol eder
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "finance",
                fontSize = 30.sp,
                fontFamily = font,

                )

            Spacer(modifier = Modifier.height(25.dp))

            Card(
                modifier = Modifier
                    .width(350.dp)
                    .height(120.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xffE7F3FF)
                )

            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Account Balacne", fontSize = 16.sp,
                            color = Color(0xff608EE9)
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                       Text(text = "$ 500", color = Color(0xff608EE9),
                           fontSize = 25.sp,)
                        }

                    }
                }

                // içerik buraya
            }
            Spacer(modifier = Modifier.height(40.dp))

            Card(
                modifier = Modifier.fillMaxWidth()
                    .height(55.dp)
                    .padding(horizontal = 22.dp),
                shape = RoundedCornerShape(43.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Row(

                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        options.forEach { option ->
                            val isSelected = option == selectedOption
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(50))

                                    .background(if (isSelected) Color(0xFF4D73FF) else Color.Transparent)
                                    .clickable { selectedOption = option }
                                    .padding(horizontal = 24.dp, vertical = 8.dp)
                            ) {
                                Text(
                                    text = option,
                                    color = if (isSelected) Color.White else Color.Gray,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                )
                            }
                        }
                    }
                }

            }
            Spacer(modifier = Modifier.height(25.dp))
            LineChartScreen()
        }
    }

@Composable
fun sayfa_Gecis(navControllers: NavHostController,view: MainViewModel){

    NavHost(navController = navControllers, startDestination = "ProcessBarScreen"){

        composable("ProcessBarScreen"){
            ProcessBarScreen(view,navControllers)
        }
        composable("home"){
            home(view)
        }

    }
}

@Composable
fun ProcessBarScreen(view: MainViewModel, navController: NavHostController) {


    val load by view.isLoading.collectAsState()



    // Fetch sadece loading ekranında çağrılır
    LaunchedEffect(Unit) {
        view.fetchGoldPrice()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (load) {
            LoadingScreen2()
        }
        else{

            navController.navigate("home")

        }
    }
}







@Composable
fun SegmentedControl() {
    val options = listOf("Day", "Month", "Yearly")
    var selectedOption by remember { mutableStateOf("Month") }



    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .clip(RoundedCornerShape(50))
                .background(Color(0xFFF5F6FA))
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            options.forEach { option ->
                val isSelected = option == selectedOption
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))

                        .background(if (isSelected) Color(0xFF4D73FF) else Color.Transparent)
                        .clickable { selectedOption = option }
                        .padding(horizontal = 24.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = option,
                        color = if (isSelected) Color.White else Color.Gray,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                    )
                }
            }
        }
    }




}







@Composable
@Preview(showSystemUi = true)
fun showhome(){

    val view : MainViewModel = viewModel()

    home(view)
    // SegmentedControl()

}