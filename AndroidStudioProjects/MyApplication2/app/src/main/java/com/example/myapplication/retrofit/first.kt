package com.example.myapplication.retrofit

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.coin

@Composable
fun firs(navController: NavController){

    val haptic = LocalHapticFeedback.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Spacer(modifier = Modifier.height(30.dp))

        Column(modifier = Modifier.fillMaxWidth()
            .padding(start = 60.dp)) {
            Text(text = "Welcom to", fontSize =25.sp,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,)

        }



        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
                .padding(end = 10.dp)
                .offset(y=-6.dp)
            ) {



            Text(text = "Kit", fontSize = 80.sp,
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold
                ))
            Row(modifier = Modifier.offset(y=-5.dp)) {
                Text(
                    text = "Built by",
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.W800,

                    modifier = Modifier.padding(start = 110.dp)
                )
                Spacer(modifier = Modifier.width(1.dp))

                Image(
                    painter = painterResource(id = R.drawable.unnamed),
                    contentDescription = null,
                    modifier = Modifier.size(22.dp)
                )

            }
            // crachter
            Column(
                modifier = Modifier
                    .fillMaxSize()
                   , // ✅ Burada kullanabilirsin
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Alt kısım: Buton
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        OutlinedButton(
                            onClick = { navController.navigate("login")
                                haptic.performHapticFeedback(HapticFeedbackType.LongPress)},
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(55.dp)
                                .padding(vertical = 4.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                        ) {
                            Text("Log in", color = Color.Black, fontSize = 18.sp)
                        }

                        FilledTonalButton(
                            onClick = {  haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                navController.navigate("Create_screen") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(55.dp)
                                .padding(vertical = 4.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
                        ) {
                            Text("Create account", color = Color.White, fontSize = 18.sp)
                        }
                        Text("By continuing you agree to our Terms of Service and Privacy Policy.",
                            textAlign = TextAlign.Center,
                            fontSize = 10.sp
                        )

                    }

                    // Üst kısım: Karakter (yarım daire + gözler)
                    Canvas(
                        modifier = Modifier
                            .size(300.dp, 250.dp)
                            .offset(y = (-62).dp)
                            .align(Alignment.TopCenter) // butonun üstünde ortalanır
                    ) {
                        // Yarım daire (siyah kafa)
                        drawArc(
                            color = Color.Black,
                            startAngle = 180f,
                            sweepAngle = 180f,
                            useCenter = true,
                            size = Size(size.width, size.height),
                            topLeft = Offset(0f, 0f)
                        )
                    }

                    // Gözler (beyaz + siyah oval)
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .offset(y = -(10).dp) // kafa içine yerleştir
                    ) {
                        Eye()
                        Eye()
                    }
                }
                    // Siyah yuvarlak



                }



            }


        }

    }

@Composable
fun sayfa_gec(navcontroller: NavHostController,view: MainViewModel){



    NavHost(navController = navcontroller, startDestination = "firs") {
        composable("firs") {
            firs(navcontroller)
        }
        composable("Create_screen") {
            Create_screen(view, navcontroller)
        }
        composable("coin") {
            coin(view,navcontroller)
        }
        composable("login") {
            login(view, navcontroller)
        }
        composable("ProcessBarScreen"){
            ProcessBarScreen(view,navcontroller)
        }
        composable("home"){
            home(view)
        }
    }


}




@Composable
fun FunnyLoginButton() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        // Alt kısım: Buton
        Button(
            onClick = { /* TODO */ },
            shape = RoundedCornerShape(50), // oval kenarlar
            modifier = Modifier
                .padding(top = 60.dp) // karakter için boşluk bırak
                .height(56.dp)
                .fillMaxWidth(0.7f)
        ) {
            Text("Log in")
        }

        // Üst kısım: Karakter (yarım daire + gözler)
        Canvas(
            modifier = Modifier
                .size(300.dp, 250.dp)
                .align(Alignment.TopCenter) // butonun üstünde ortalanır
        ) {
            // Yarım daire (siyah kafa)
            drawArc(
                color = Color.Black,
                startAngle = 180f,
                sweepAngle = 180f,
                useCenter = true,
                size = Size(size.width, size.height),
                topLeft = Offset(0f, 0f)
            )
        }

        // Gözler (beyaz + siyah oval)
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = -(90).dp) // kafa içine yerleştir
        ) {
            Eye()
            Eye()
        }
    }
}

@Composable
fun Eye() {
    Box(
        modifier = Modifier
            .size(30.dp, 40.dp) // oval göz
            .background(Color.White, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(10.dp, 16.dp) // gözbebeği
                .background(Color.Black, shape = CircleShape)
        )
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

    var navController = rememberNavController()

    firs(navController)

}