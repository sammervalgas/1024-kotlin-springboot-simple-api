package br.com.devbean.services

import br.com.devbean.entities.VeiculoEntity
import br.com.devbean.repositories.VeiculoRepository
import org.springframework.stereotype.Service
import java.util.*

/**
 * Implementação do serviço {@link VeiculoService} responsável por gerenciar as operações de negócios
 * relacionadas aos veículos, como criação, leitura, atualização e remoção de veículos.
 * Utiliza o repositório {@link VeiculoRepository} para realizar as operações de persistência de dados.
 *
 * @param veiculoRepository Repositório de veículos usado para acessar os dados.
 */
@Service
class VeiculoServiceImpl(private val veiculoRepository: VeiculoRepository) : VeiculoService {

    /**
     * Busca a lista de todos os veículos cadastrados.
     *
     * @return Lista de todas as entidades de veículos.
     */
    override fun read(): List<VeiculoEntity> {
        return veiculoRepository.findAll()
    }

    /**
     * Busca um veículo específico pelo ID.
     *
     * @param id Identificador do veículo.
     * @return Um {@link Optional} contendo a entidade do veículo, ou vazio se o veículo não for encontrado.
     */
    override fun read(id: Long): Optional<VeiculoEntity> {
        return veiculoRepository.findById(id)
    }

    /**
     * Cria e persiste um novo veículo no banco de dados.
     *
     * @param veiculoEntity Entidade do veículo a ser criada.
     * @return A entidade do veículo que foi salva.
     */
    override fun create(veiculoEntity: VeiculoEntity): VeiculoEntity {
        return veiculoRepository.save(veiculoEntity)
    }

    /**
     * Remove um veículo existente com base no ID.
     *
     * @param id Identificador do veículo a ser removido.
     * @return {@code true} se o veículo foi removido com sucesso, ou {@code false} se o veículo não foi encontrado.
     */
    override fun delete(id: Long): Boolean {
        val veiculoOptional = read(id)

        if (veiculoOptional.isPresent) {
            val veiculo = veiculoOptional.get()
            veiculoRepository.delete(veiculo)
            return true
        }

        return false
    }

    /**
     * Atualiza os dados de um veículo existente com base no ID.
     *
     * @param id Identificador do veículo a ser atualizado.
     * @param veiculoNovo Entidade contendo os novos dados do veículo.
     * @return A entidade atualizada do veículo.
     * @throws IllegalArgumentException Se o veículo com o ID especificado não for encontrado.
     */
    override fun update(id: Long, veiculoNovo: VeiculoEntity): VeiculoEntity {
        // Busca o veículo existente no banco de dados, lança exceção se não for encontrado
        val veiculoBase = read(id).orElseThrow { IllegalArgumentException("Veículo $id não existe!") }

        // Atualiza os dados do veículo, mantendo os valores antigos para campos nulos
        val veiculoAtualizado = VeiculoEntity(
            id = veiculoNovo.id ?: veiculoBase.id,
            marca = veiculoNovo.marca ?: veiculoBase.marca,
            modelo = veiculoNovo.modelo ?: veiculoBase.modelo,
            ano = veiculoNovo.ano ?: veiculoBase.ano,
            isClassico = veiculoNovo.isClassico // Valor booleano não permite nulos, substituído diretamente
        )

        // Salva o veículo atualizado no banco de dados
        return create(veiculoAtualizado)
    }
}
