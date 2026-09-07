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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
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
fun AtualizarContato(
    navController: NavController,
    id: String,
    nome: String,
    sobrenome: String,
    telefone: String
){

    var novoNome by remember { mutableStateOf(nome) }
    var novoSobrenome by remember { mutableStateOf(sobrenome) }
    var novoTelefone by remember { mutableStateOf(telefone) }
    var mensagem by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Atualizar Contato") },
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
                value = novoNome ,
                onValueChange = {
                    novoNome = it
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
                value = novoSobrenome ,
                onValueChange = {
                    novoSobrenome = it
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
                value = novoTelefone ,
                onValueChange = {
                    novoTelefone = it
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
                    scope.launch(Dispatchers.IO) { 
                        if(novoNome.isEmpty() || novoTelefone.isEmpty()){
                            mensagem = false
                        }else{
                            mensagem = true
                            contatoDAO = AppDatabase.getInstance(context).contatoDAO()
                            contatoDAO.atualizar(id.toInt(), novoNome, novoSobrenome, novoTelefone)
                        }
                    }
                    
                    scope.launch(Dispatchers.Main) { 
                        if(mensagem){
                            Toast.makeText(context, "Sucesso ao atualizar o contato", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(context, "Preencha o nome e o telefone", Toast.LENGTH_SHORT).show()
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
private fun AtualizarContatoPreview(){
    AtualizarContato(navController = rememberNavController(), "", "", "", "")
}