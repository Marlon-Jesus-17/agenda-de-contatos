package com.marlebas.agendadecontatos.dao

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.Query
import com.marlebas.agendadecontatos.model.Contato

@Dao
interface ContatoDAO {

    @Insert
    fun salvar(contatos: Contato)

    @Query("SELECT * FROM tabela_contatos ORDER BY nome ASC")
    fun getContatos(): MutableList<Contato>

    @Query("UPDATE tabela_contatos SET nome = :novoNome, sobrenome = :novoSobrenome, telefone = :novoTelefone " +
            "WHERE id = :id")
    fun atualizar(id: Int, novoNome: String, novoSobrenome: String, novoTelefone: String)
}