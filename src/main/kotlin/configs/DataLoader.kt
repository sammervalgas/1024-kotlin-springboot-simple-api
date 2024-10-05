package br.com.devbean.configs

import br.com.devbean.entities.VeiculoEntity
import br.com.devbean.repositories.VeiculoRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

/**
 * Classe responsável por carregar dados iniciais no banco de dados assim que a aplicação é iniciada.
 * Implementa a interface CommandLineRunner, que permite executar código após o contexto do Spring Boot
 * estar completamente carregado.
 *
 * @param repository O repositório que será utilizado para persistir os dados dos veículos.
 */
@Component
class DataLoader(private val repository: VeiculoRepository) : CommandLineRunner {

    /**
     * Método executado automaticamente ao iniciar a aplicação. Verifica se o banco de dados está vazio,
     * e se estiver, preenche-o com uma lista de veículos predefinidos.
     *
     * @param args Argumentos de linha de comando passados para a aplicação (não utilizados aqui).
     */
    override fun run(vararg args: String?) {
        // Verifica se o repositório está vazio (nenhum veículo cadastrado)
        if (repository.count() == 0L) {
            // Cria uma lista de veículos pré-definidos para adicionar ao repositório
            val veiculos = listOf(
                VeiculoEntity(1, "Toyota", "Corolla", 2020, false),
                VeiculoEntity(2, "Honda", "Civic", 2019, false),
                VeiculoEntity(3, "Ford", "Focus", 2018, false),
                VeiculoEntity(4, "VW", "Fusca Stringray", 1963, true)
            )
            // Salva a lista de veículos no repositório
            repository.saveAll(veiculos)
        }
    }
}
