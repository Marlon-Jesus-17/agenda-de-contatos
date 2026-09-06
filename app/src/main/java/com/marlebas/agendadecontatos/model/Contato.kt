package com.marlebas.agendadecontatos.model

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.PrimaryKey
import com.marlebas.agendadecontatos.constantes.Constantes

@Entity(tableName = Constantes.TABELA_CONTATOS)
data class Contato(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "nome")
    val nome: String,

    @ColumnInfo(name = "sobrenome")
    val sobrenome: String,

    @ColumnInfo(name = "telefone")
    val telefone: String
)
