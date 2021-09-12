package br.com.alura.repository

import br.com.alura.model.Autor
import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface AutorRepository: JpaRepository<Autor, Long> {
    fun findByNome(nome: String): Optional<Autor>

    @Query("SELECT a FROM Autor a WHERE a.nome = :nome")
    fun buscaPorNome(nome: String): Optional<Autor>
}
