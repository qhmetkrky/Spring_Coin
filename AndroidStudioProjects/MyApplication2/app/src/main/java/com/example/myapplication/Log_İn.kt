@file:Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.example.myapplication

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.myapplication.retrofit.User

import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.sp
import com.example.myapplication.retrofit.MainViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LogInScreen(view:MainViewModel) {
    var name = remember { mutableStateOf("") }
    var password = remember { mutableStateOf("") }

    val username = remember { mutableStateOf("") }
    val isLoading by view._isLoadind.observeAsState(false)





    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {


            OutlinedTextField(


                shape = RoundedCornerShape(30.dp),
                singleLine = true,
                value = name.value,
                onValueChange = { name.value = it },
                label = { Text("Name", color = Color.DarkGray) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(

                    focusedContainerColor = Color.Transparent,   // Arka plan
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color(0xFF9C27B0),   // Mor (focus)
                    unfocusedIndicatorColor = Color(0xFFCE93D8), // Açık mor (normal)
                    cursorColor = Color(0xFF9C27B0)              // İmleç
                ),


                )


            OutlinedTextField(


                shape = RoundedCornerShape(30.dp),
                singleLine = true,
                value = username.value,
                onValueChange = { username.value = it },
                label = { Text("Username", color = Color.DarkGray) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(

                    focusedContainerColor = Color.Transparent,   // Arka plan
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color(0xFF9C27B0),   // Mor (focus)
                    unfocusedIndicatorColor = Color(0xFFCE93D8), // Açık mor (normal)
                    cursorColor = Color(0xFF9C27B0)              // İmleç
                ),


                )

            OutlinedTextField(


                shape = RoundedCornerShape(30.dp),
                colors = TextFieldDefaults.colors(

                    focusedContainerColor = Color.Transparent,   // Arka plan
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color(0xFF9C27B0),   // Mor (focus)
                    unfocusedIndicatorColor = Color(0xFFCE93D8), // Açık mor (normal)
                    cursorColor = Color(0xFF9C27B0)              // İmleç
                ),
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(50.dp))


            Button(

                onClick = {

                    view.fetchpost(User(name.value, username.value, password.value))


                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp)
                    .padding(horizontal = 20.dp)
                    .padding(top = 20.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                )

            ) {
                if (isLoading) {
                    CircularProgressIndicator(color = Color.White,
                        strokeWidth = 2.dp,
                        modifier = Modifier.size(24.dp) )
                } else {


                    Text(
                        text = "Save", color = Color.White,
                        fontSize = 15.sp
                    )


                }
            }
        }
    }
}




@Composable
@Preview(showSystemUi = true)
fun show_log_in_screen(){

    val view : MainViewModel = viewModel()

    LogInScreen(view)



}
