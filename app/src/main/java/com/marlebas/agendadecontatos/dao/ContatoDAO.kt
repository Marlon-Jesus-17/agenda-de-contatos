package com.marlebas.agendadecontatos.dao

import androidx.room3.Dao
import androidx.room3.Insert
import com.marlebas.agendadecontatos.model.Contato

@Dao
interface ContatoDAO {

    @Insert
    fun salvar(contatos: Contato)
}