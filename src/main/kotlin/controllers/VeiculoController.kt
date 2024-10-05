package br.com.devbean.controllers

import br.com.devbean.controllers.dtos.responses.VeiculoStatusResponseDTO
import br.com.devbean.services.VeiculoService
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

/**
 * Controlador REST responsável por gerenciar as operações CRUD (Create, Read, Update, Delete)
 * relacionadas aos veículos. Utiliza o serviço {@link VeiculoService} para executar a lógica
 * de negócio e manipulação dos dados.
 *
 * @param service Instância do serviço {@link VeiculoService} utilizada para manipular as entidades de veículos.
 */
@RestController
@RequestMapping("/veiculos")
@Validated
class VeiculoController(private val service: VeiculoService) {

    /**
     * Endpoint para buscar a lista completa de veículos.
     *
     * @return Lista de todos os veículos cadastrados.
     */
    @GetMapping
    fun findListaVeiculos() = service.read()

    /**
     * Endpoint para buscar um veículo específico pelo ID.
     *
     * @param id Identificador do veículo.
     * @return Veículo correspondente ao ID informado.
     */
    @GetMapping("/{id}")
    fun findVeiculoById(@PathVariable("id") id: Long) = service.read(id)

    /**
     * Endpoint para salvar um novo veículo.
     *
     * @param veiculoRequestDTO DTO que contém as informações do veículo a ser salvo.
     * @return O veículo criado.
     */
    @PostMapping
    fun saveVeiculo(@RequestBody veiculoRequestDTO: VeiculoRequestDTO) = service.create(veiculoRequestDTO.toEntity())

    /**
     * Endpoint para atualizar um veículo existente.
     *
     * @param id Identificador do veículo a ser atualizado.
     * @param novoVeiculo DTO com os novos dados do veículo.
     * @return Um {@link ResponseEntity} contendo o DTO {@link VeiculoAtualizadoResponse} com os dados atualizados
     *         do veículo ou uma resposta de erro (404) caso o veículo não seja encontrado.
     */
    @PutMapping("/{id}")
    fun updateVeiculo(
        @PathVariable id: Long,
        @RequestBody novoVeiculo: VeiculoRequestDTO
    ): ResponseEntity<VeiculoAtualizadoResponse> {
        return try {
            // Atualiza o veículo no serviço e recebe a entidade atualizada
            val veiculoAtualizado = service.update(id, novoVeiculo.toEntity())

            // Converte a entidade para o DTO VeiculoAtualizadoResponse
            val veiculoResponse = veiculoAtualizado.toResponse()

            // Retorna o DTO atualizado com o status 200 OK
            ResponseEntity.ok(veiculoResponse)

        } catch (e: Exception) {
            // Em caso de erro (ex: veículo não encontrado), retorna 404 Not Found
            ResponseEntity.notFound().build()
        }
    }

    /**
     * Endpoint para deletar um veículo pelo ID.
     *
     * @param id Identificador do veículo a ser deletado.
     * @return Um {@link VeiculoStatusResponseDTO} contendo o status da remoção (sucesso ou falha) e uma mensagem.
     */
    @DeleteMapping
    fun deleteVeiculo(@RequestParam id: Long): VeiculoStatusResponseDTO {
        val deleted = service.delete(id)

        return VeiculoStatusResponseDTO(
            status = deleted,
            mensagem = "Registro Removido!".takeIf { deleted } ?: "Registro $id não encontrado!" // Mensagem condicional
        )
    }
}
