package com.marlebas.agendadecontatos

import android.content.Context
import androidx.room3.Database
import androidx.room3.Room
import androidx.room3.RoomDatabase
import com.marlebas.agendadecontatos.constantes.Constantes
import com.marlebas.agendadecontatos.dao.ContatoDAO
import com.marlebas.agendadecontatos.model.Contato

@Database(entities = [Contato::class], version = 1)
abstract class AppDatabase: RoomDatabase(){

    abstract fun contatoDAO(): ContatoDAO

    companion object{ //Basicamente serve para transformar as funções dentro dele em 'static' como existe no Java

        @Volatile // Garante que qualquer alteração na variável INSTANCE seja imediatamente visível para todas as Threads do aplicativo.
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase{
            return INSTANCE ?: synchronized(this){ //O bloco synchronized(this). Esse bloco trava o acesso para que apenas uma Thread por vez execute o código interno, evitando que duas requisições simultâneas criem dois bancos de dados ao mesmo tempo.
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, //Informa ao Room qual é a classe abstrata que serve de modelo para o banco.
                    Constantes.DB_CONTATOS
                ).build() //Constrói efetivamente a instância do banco de dados com as configurações passadas.

                INSTANCE = instance
                instance //Retorna a instância criada como resultado final da função
            }
        }
    }
}