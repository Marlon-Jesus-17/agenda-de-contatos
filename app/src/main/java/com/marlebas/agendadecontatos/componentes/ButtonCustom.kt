package com.marlebas.agendadecontatos.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marlebas.agendadecontatos.ui.theme.PURPLE500
import com.marlebas.agendadecontatos.ui.theme.WHITE

@Composable
fun ButtonCustom(
    onClick: () -> Unit,
    texto: String
){

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = PURPLE500
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .background(shape = RoundedCornerShape(5.dp), color = PURPLE500)
    ) {
        Text(
            text = texto,
            color = WHITE,
            fontSize = 18.sp
        )
    }
}