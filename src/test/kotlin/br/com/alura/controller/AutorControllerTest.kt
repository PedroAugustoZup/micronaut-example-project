package br.com.alura.controller

import br.com.alura.dto.response.AutorResponse
import br.com.alura.model.Autor
import br.com.alura.repository.AutorRepository
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import io.micronaut.http.client.HttpClient
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.annotation.TransactionMode
import org.junit.jupiter.api.Assertions.*

import org.mockito.Mockito.mock

@MicronautTest(
    rollback = false, // O @TEST NAO IRA FAZER ROLL BACK NO FINAL DE CADA TESTE, POR PADRAO ELE FAZ
    transactionMode = TransactionMode.SINGLE_TRANSACTION, //O @TEST E O @BEFOREEACH E @AFTEREACH IRAO FUNCIONAR NA MESMA TRANSACAO
    transactional = false // OS METODOS NAO IRÁ ABRIR TRANSAÇÕES
)
internal class AutorControllerTest {

    @field:Inject
    lateinit var autorRepository: AutorRepository

//    @field:Inject
//    lateinit var exemploMock: ExemploMock

    @field:Inject
    @field:Client("/")
    lateinit var client:HttpClient

    lateinit var autor:Autor
    @BeforeEach
    fun setUp() {
        autor = Autor("Pedro")
        autorRepository.save(autor)
    }

    @AfterEach
    fun tearDown() {
        autorRepository.deleteAll()
    }

    @Test
    fun listar() {

        var response = client.toBlocking().exchange("/autor?email=${autor.nome}", AutorResponse::class.java)

        assertEquals(HttpStatus.OK, response.status)
        assertNotNull(response.body())
        assertEquals(autor.nome, response.body()!!.nome)
    }

//    @MockBean(ExemploMock::class)
//    fun exemploMock:ExemploMock{
//        return mock(ExemploMock::class.java)
//    }
}