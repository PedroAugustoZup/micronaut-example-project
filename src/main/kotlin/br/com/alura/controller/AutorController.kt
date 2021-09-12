package br.com.alura.controller

import br.com.alura.dto.request.AutorRequest
import br.com.alura.dto.response.AutorResponse
import br.com.alura.model.Autor
import br.com.alura.repository.AutorRepository
import io.micronaut.data.jpa.repository.JpaRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Controller("/autor")
class AutorController(val autorRepository: AutorRepository) {
    private val log: Logger = LoggerFactory.getLogger(javaClass)

    @Post
    fun salvar(@Body @Valid request: AutorRequest):HttpResponse<Any>{
        log.info(request.toString())
        val autor = autorRepository.save(request.toModel())
        val uri = UriBuilder.of("/autor/{id}")
            .expand(mutableMapOf(Pair("id", autor.id)))
        return HttpResponse.created(uri)
    }

    @Get("/{id}")
    fun listarPorId(@PathVariable("id") id:Long):HttpResponse<Any>{
        val autor = autorRepository.findById(id)
        if(autor.isPresent) return HttpResponse.ok(autor.get())
        return HttpResponse.notFound()
    }

    @Get
    fun listar(@QueryValue(defaultValue = "") nome:String):HttpResponse<Any>{
        if(nome.isBlank()){
            val autores = autorRepository.findAll();
            val resposta = autores.map { autor -> AutorResponse(autor) }
            return HttpResponse.ok(resposta)
        }
//        val possivelAutor = autorRepository.findByNome(nome)
        val possivelAutor = autorRepository.buscaPorNome(nome)
        if(possivelAutor.isEmpty) return HttpResponse.notFound()

        return HttpResponse.ok(AutorResponse(possivelAutor.get()))
    }
}