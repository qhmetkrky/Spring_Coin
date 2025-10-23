package com.example.myapplication

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun field(){


    var name = remember { mutableStateOf("") }

    Column(modifier= Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center

        ){

        Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
            ){

            OutlinedTextField(
                modifier = Modifier
                ,

                shape = RoundedCornerShape(30.dp),
                value = name.value,
                onValueChange = { name.value = it },
                label = { Text("Username", color = Color.DarkGray) },


                )


        }


}

}






@Composable
@Preview(showSystemUi = true)
fun show(){

    field()


}