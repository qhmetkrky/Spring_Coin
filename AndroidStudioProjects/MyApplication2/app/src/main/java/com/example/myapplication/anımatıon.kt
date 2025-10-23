package com.example.myapplication

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LoadingScreen() {
    // Lottie JSON dosyasını RawRes'den yükle
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.tt))

    // Animasyonu sonsuz tekrar et

    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever
    )

    // Ekranda ortala

        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier.size(250.dp) // animasyon boyutu
        )

}

@Composable
@Preview(showSystemUi = true)
fun ss(){
    LoadingScreen()
}