package br.com.devbean.controllers

import br.com.devbean.entities.VeiculoEntity

data class VeiculoRequestDTO (
    val modelo:String,
    val marca: String,
    val ano: Int,
    val isClassico: Boolean = false
)

fun VeiculoRequestDTO.toEntity() = VeiculoEntity (
    modelo = modelo,
    marca = marca,
    ano = ano,
    isClassico = isClassico
)