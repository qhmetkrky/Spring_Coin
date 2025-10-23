package com.example.myapplication

import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.retrofit.MainViewModel


@Composable
fun coin(view: MainViewModel,navController: NavHostController){

    val interfont= FontFamily(
        Font(R.font.inter),
    )
    var coin_gram by remember { mutableStateOf("") }
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()
        .background(color = androidx.compose.ui.graphics.Color.White),

        ){
        Spacer(modifier = Modifier.height(100.dp))

        Column(modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

            Text(text = "Altın Miktarını Giriniz",
                fontSize =25.sp,
                fontWeight =  androidx.compose.ui.text.font.FontWeight.SemiBold
                )



            Spacer(modifier = Modifier.height(50.dp))
            LoadingScreen()
            Spacer(modifier = Modifier.height(45.dp))

          /*  TextField(
                value = coin_gram,
                onValueChange = {coin_gram = it},
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                ),
                label = { Text("Altın Miktarı gr")},
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.of),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),

                    )
                },

            ) */
            AltinMiktariSecimi { secilenMiktar ->
                println("Seçilen miktar: $secilenMiktar gram")
                coin_gram=secilenMiktar.toString()
            }
            Spacer(modifier = Modifier.height(30.dp))
            FilledTonalButton(

                onClick = {
                    if(coin_gram!=null){
                    view.updateAmount(coin_gram.toDouble()){
                        succes->
                        if(succes){
                            Toast.makeText(context,"Güncelleme başarılı", Toast.LENGTH_SHORT).show()
                            navController.navigate("ProcessBarScreen")
                        }
                        else{
                            Toast.makeText(context,"Güncelleme başarısız", Toast.LENGTH_SHORT).show()
                        }

                    }

                }
                    else{
                        Toast.makeText(context,"Miktar giriniz", Toast.LENGTH_SHORT).show()
                    }
                          }
                ,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(47.dp)
                    .padding(horizontal = 20.dp),
                interactionSource = interactionSource,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xff0D2063))
            ) {
                Text("Confirm ", color = Color.White, fontSize = 16.sp)
            }



        }
        }
    }

@Composable
fun AltinMiktariSecimi(onMiktarDegisti: (Int) -> Unit) {
    var miktar by remember { mutableStateOf(1f) } // Varsayılan değer
    val context = LocalContext.current
    val vibrator = remember {
        context.getSystemService(Vibrator::class.java)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Altın Miktarı: %.1f g".format(miktar),
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        Slider(
            value = miktar,
            onValueChange = {
                miktar = it
                onMiktarDegisti(it.toInt())

                // Titreşim efekti
                vibrator?.vibrate(
                    VibrationEffect.createOneShot(10, VibrationEffect.DEFAULT_AMPLITUDE)
                )
            },
            valueRange = 1f..100f,
            steps = 99,
            modifier = Modifier.fillMaxWidth(0.9f)
        )
    }
}








@Composable
@Preview(showSystemUi = true)
fun coin2(){

    val view : MainViewModel = viewModel()
    val navhost : NavHostController = rememberNavController()

    coin(view,navhost)

}