package com.example.myapplication.retrofit

import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicSecureTextField
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@Composable
fun Create_screen(view: MainViewModel,navController: NavHostController) {

    var value_pass = remember { TextFieldState() }

    var email by remember { mutableStateOf("") }
    var usarname by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val fontsize by remember { mutableStateOf(10.sp) }
    val isLoading by view._isLoadind.observeAsState(false)
    val errorMessage by view._errorMessage.observeAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 25.dp)
            ) {
                Text(
                    text = "Welcome ", fontSize = 25.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold
                )

                Text(
                    text = "Create your account", fontSize = 13.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Medium,
                    modifier = Modifier.padding(top = 5.dp),
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(70.dp))

                Text(
                    modifier = Modifier.padding(start = 6.dp),
                    text = buildAnnotatedString {
                        append("Username ")
                        withStyle(style = androidx.compose.ui.text.SpanStyle(color = Color.Red)) {
                            append("*")
                        }
                    }, fontSize = fontsize
                )
                OutlinedTextField(
                    value = usarname,
                    onValueChange = { usarname = it },

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp)
                        .border(1.dp, Color.LightGray, RoundedCornerShape(6.dp))
                )
                Text(
                    modifier = Modifier.padding(start = 6.dp),
                    text = buildAnnotatedString {
                        append("Email adress ")
                        withStyle(style = androidx.compose.ui.text.SpanStyle(color = Color.Red)) {
                            append("*")

                        }
                    }, fontSize = fontsize
                )
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp)
                        .border(1.dp, Color.LightGray, RoundedCornerShape(6.dp))
                )
                Spacer(modifier = Modifier.height(10.dp))

                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        modifier = Modifier.padding(start = 6.dp),
                        text = buildAnnotatedString {
                            append("Password ")
                            withStyle(style = androidx.compose.ui.text.SpanStyle(color = Color.Red)) {
                                append("*")
                            }
                        }, fontSize = fontsize
                    )


                    passworde(value_pass)


                }
                Spacer(modifier = Modifier.height(10.dp))
                errorMessage?.let {
                    Text(
                        text = it,
                        color = Color.Red,
                        fontSize = 10.sp,
                        modifier = Modifier.padding(horizontal = 16.dp),
                        textAlign = TextAlign.Center,

                        )
                }
                Spacer(modifier = Modifier.height(50.dp))

                FilledTonalButton(
                    onClick = {
                        view.fetchpost(User(usarname, email, value_pass.text.toString()))


                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                        .padding(horizontal = 0.dp)
                        .padding(vertical = 4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xff0D2063))
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            color = Color.White,
                            strokeWidth = 2.dp,
                            modifier = Modifier.size(24.dp)
                        )
                    } else {

                        Text("Create account", color = Color.White, fontSize = 18.sp)


                    }

                }


            }
        }

    }
}
@Composable
fun passworde(value_pass: TextFieldState) {

    val state = value_pass
    var showPassword by remember { mutableStateOf(false) }
    BasicSecureTextField(
        state = state,
        textObfuscationMode =
            if (showPassword) {
                TextObfuscationMode.Visible
            } else {
                TextObfuscationMode.RevealLastTyped
            },
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .border(1.dp, Color.LightGray, RoundedCornerShape(6.dp))
            .padding(6.dp),
        decorator = { innerTextField ->
            Box(modifier = Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 16.dp, end = 48.dp)
                ) {
                    innerTextField()
                }
                Icon(
                    if (showPassword) {
                        Icons.Filled.Visibility
                    } else {
                        Icons.Filled.VisibilityOff
                    },
                    contentDescription = "Toggle password visibility",
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .requiredSize(48.dp).padding(16.dp)
                        .clickable { showPassword = !showPassword }
                )
            }
        }
    )

}






@Composable
fun OrDivider() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Divider(
            modifier = Modifier
                .weight(1f)
                .height(1.dp),
            color = Color.Gray.copy(alpha = 0.4f)
        )

        Text(
            text = "or",
            modifier = Modifier.padding(horizontal = 8.dp),
            color = Color.Gray,
            fontSize = 16.sp
        )

        Divider(
            modifier = Modifier
                .weight(1f)
                .height(1.dp),
            color = Color.Gray.copy(alpha = 0.4f)
        )
    }
}



@Preview(showBackground = true)
@Composable
fun show(){

    val view : MainViewModel = viewModel()
    val navController = rememberNavController()

    Create_screen(view,navController)



}