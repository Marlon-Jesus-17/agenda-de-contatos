package com.marlebas.agendadecontatos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.marlebas.agendadecontatos.ui.theme.AgendaDeContatosTheme
import com.marlebas.agendadecontatos.views.AtualizarContato
import com.marlebas.agendadecontatos.views.ListaContatos
import com.marlebas.agendadecontatos.views.SalvarContato

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController() //Controlador de navegação entre telas

            NavHost(
                navController = navController,
                startDestination = "listaContatos" //Rota da primeira tela que será mostrada
            ){
                composable("listaContatos") { //Rota da tela que para qual migrará
                    ListaContatos(navController = navController) //Tela que será rendirizada
                }

                composable("salvarContato") {
                    SalvarContato()
                }

                composable("atualizarContato") {
                    AtualizarContato()
                }
            }
        }
    }
}