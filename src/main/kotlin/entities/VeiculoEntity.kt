package br.com.devbean.entities

import jakarta.persistence.*
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

@Entity
@Table(name = "veiculos")
data class VeiculoEntity (

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,

    @field:NotEmpty(message = "A marca não pode ser vazia")
    val marca: String? = null,

    @field:NotEmpty(message = "O modelo não pode ser vazio")
    val modelo: String? = null,

    @field:NotNull(message = "O ano não pode ser nulo")
    val ano: Int? = null,

    val isClassico: Boolean = false

)
