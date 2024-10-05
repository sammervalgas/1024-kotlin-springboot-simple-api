package br.com.devbean.controllers

import br.com.devbean.entities.VeiculoEntity

data class VeiculoAtualizadoResponse(
    val modelo: String? = null,
    val marca: String? = null,
    val ano: Int? = null,
    val isClassico: Boolean = false
)

fun VeiculoEntity.toResponse(): VeiculoAtualizadoResponse {
    return VeiculoAtualizadoResponse(
        modelo = this.modelo,       // Garante que o modelo seja uma string não nula
        marca = this.marca,         // Garante que a marca seja uma string não nula
        ano = this.ano,              // Garante que o ano seja um inteiro não nulo
        isClassico = this.isClassico // Garante que o isClassico seja booleano não nulo
    )
}

