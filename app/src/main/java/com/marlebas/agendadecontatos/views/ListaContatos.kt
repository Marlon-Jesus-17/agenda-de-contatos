package com.marlebas.agendadecontatos.views

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.marlebas.agendadecontatos.AppDatabase
import com.marlebas.agendadecontatos.dao.ContatoDAO
import com.marlebas.agendadecontatos.itemlista.ContatoItem
import com.marlebas.agendadecontatos.model.Contato
import com.marlebas.agendadecontatos.ui.theme.PURPLE500
import com.marlebas.agendadecontatos.ui.theme.WHITE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private lateinit var contatoDAO: ContatoDAO

@SuppressLint("CoroutineCreationDuringComposition") //Permite criar uma trhead durante a composição
@OptIn(ExperimentalMaterial3Api::class) //Adicionar essa anotação para trabalhar com o Scaffold
@Composable
fun ListaContatos(navController: NavController){

    val listaContatos by remember { mutableStateOf<MutableList<Contato>>(mutableListOf()) }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    scope.launch(Dispatchers.IO) {
        contatoDAO = AppDatabase.getInstance(context).contatoDAO()
        val contato = contatoDAO.getContatos()

        for(contatos in contato){
            listaContatos.add(contatos)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Agenda de Contatos")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PURPLE500,
                    titleContentColor = WHITE
                )
            )
        },
        floatingActionButton = { //Botão flutuante
            FloatingActionButton(
                onClick = {
                    navController.navigate("salvarContato")
                },
                containerColor = PURPLE500,
                modifier = Modifier.clip(
                    shape = CircleShape //Deixar o botão flutuante redondo
                ),
                contentColor = WHITE
            ) {
                Icon(
                    imageVector = Icons.Default.Add, //Adiciona o Icone de Add(+)
                    contentDescription = "Icone de adicionar novo contato"
                )
            }
        }
    ) {paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) //Passa o paddingValues pra dentro do padding para o conteúdo ficar dentro do Scaffold
                .background(Color.LightGray)
        ) {
            LazyColumn{
                itemsIndexed(listaContatos){
                    _, contato ->
                    ContatoItem(contato)
                }
            }
        }

    }
}

@Preview
@Composable
private fun ListaContatosPreview(){
    ListaContatos(navController = rememberNavController())
}