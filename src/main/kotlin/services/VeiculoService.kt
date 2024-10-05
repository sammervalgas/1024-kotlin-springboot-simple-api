package br.com.devbean.services

import br.com.devbean.entities.VeiculoEntity
import java.util.*

interface VeiculoService {

    fun read() : List<VeiculoEntity>

    fun read(id: Long) : Optional<VeiculoEntity>

    fun create(veiculoEntity: VeiculoEntity): VeiculoEntity

    fun delete(id: Long) : Boolean

    fun update(id: Long, veiculoNovo: VeiculoEntity) : VeiculoEntity

}