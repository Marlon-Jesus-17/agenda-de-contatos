package com.marlebas.agendadecontatos.componentes

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.marlebas.agendadecontatos.ui.theme.PURPLE500

@Composable
fun OutlinedTextFieldCurstom(
    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable () -> Unit,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier //Assim não se torna um parâmetro obrigatório
    ){
    OutlinedTextField(
        value,
        onValueChange,
        label = label,
        keyboardOptions = keyboardOptions, //Tipo de teclado
        colors = TextFieldDefaults.colors(
            cursorColor = PURPLE500, //Cor do cursor
            focusedIndicatorColor = PURPLE500 //Cor da borda
        ),
        maxLines = 1,
        modifier = modifier
    )
}

@Composable
@Preview
private fun OutlinedTextFieldCurstomPreview(){
    OutlinedTextFieldCurstom(
        "Nome",
        {it},
        label = { Text(text = "Nome") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text //Tipo de Teclado
        )
    )
}