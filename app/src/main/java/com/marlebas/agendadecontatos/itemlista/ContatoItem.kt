package com.marlebas.agendadecontatos.itemlista

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.marlebas.agendadecontatos.model.Contato
import com.marlebas.agendadecontatos.ui.theme.WHITE

@Composable
fun ContatoItem(
    contato: Contato,
    navController: NavController
){

    val id = contato.id

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = WHITE,
            contentColor = WHITE
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = "Contato: ${contato.nome} ${contato.sobrenome}",
                fontSize = 18.sp,
                color = Color.Black
                )

            Text(
                text = "Telefone: ${contato.telefone}",
                fontSize = 18.sp,
                color = Color.Black
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = {
                        navController.navigate("atualizarContato/$id/${contato.nome}/${contato.sobrenome}/${contato.telefone}")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }

                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        tint = Color.Red
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ContatoItemPreview(){
    ContatoItem(
        contato = Contato( nome = "Marlon", sobrenome = "Jesus", telefone = "95555-6666"),
        navController = rememberNavController()
    )
}