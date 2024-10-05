package br.com.devbean.repositories

import br.com.devbean.entities.VeiculoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VeiculoRepository : JpaRepository<VeiculoEntity, Long>