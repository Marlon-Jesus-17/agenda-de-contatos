package com.marlebas.agendadecontatos.views

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marlebas.agendadecontatos.AppDatabase
import com.marlebas.agendadecontatos.componentes.ButtonCustom
import com.marlebas.agendadecontatos.componentes.OutlinedTextFieldCurstom
import com.marlebas.agendadecontatos.dao.ContatoDAO
import com.marlebas.agendadecontatos.model.Contato
import com.marlebas.agendadecontatos.ui.theme.PURPLE500
import com.marlebas.agendadecontatos.ui.theme.WHITE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private lateinit var contatoDAO: ContatoDAO

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SalvarContato(){

    var nome by remember { mutableStateOf("") }
    var sobrenome by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var mensagem by remember { mutableStateOf(false) }

    var scope = rememberCoroutineScope()
    var context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Salvar Contato") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PURPLE500,
                    titleContentColor = WHITE
                )
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()) //Permite rolagem mesmo com o celular deitado
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextFieldCurstom(
                value = nome ,
                onValueChange = {
                    nome = it
                },
                label = {
                    Text( text = "Nome")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp, 20.dp, 10.dp)
            )

            OutlinedTextFieldCurstom(
                value = sobrenome ,
                onValueChange = {
                    sobrenome = it
                },
                label = {
                    Text( text = "Sobrenome")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp, 20.dp, 10.dp)
            )

            OutlinedTextFieldCurstom(
                value = telefone ,
                onValueChange = {
                    telefone = it
                },
                label = {
                    Text( text = "Telefone")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp, 20.dp, 10.dp)
            )

            ButtonCustom(
                onClick = {

                    scope.launch(
                        Dispatchers.IO //Explicitando que é uma trhead paralela
                    ){
                        if(nome.isEmpty() || telefone.isEmpty()){
                            mensagem = false
                        }else{
                            mensagem = true
                            val contato = Contato(nome = nome, sobrenome = sobrenome, telefone = telefone)
                            contatoDAO = AppDatabase.getInstance(context).contatoDAO()
                            contatoDAO.salvar(contato)
                        }
                    }

                    scope.launch(
                        Dispatchers.Main //Explicitando que não é uma trhead parelela e sim a principal
                    ){
                        if(mensagem){
                            Toast.makeText(context, "Sucesso ao salvar contato", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(context, "Preencha o nome e telefone", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                "Salvar"
            )
        }
    }
}

@Preview
@Composable
private fun SalvarContatoPreview(){
    SalvarContato()
}