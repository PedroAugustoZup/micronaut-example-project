package br.com.alura.dto.request

import br.com.alura.model.Autor
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
data class AutorRequest(
    @field:NotBlank val nome: String,
    @field:NotBlank @field:Email val email: String,
    @field:Size(max = 400) val descricao: String
){
    override fun toString(): String {
        return "AutorRequest(nome='$nome', email='$email', descricao='$descricao')"
    }

    fun toModel(): Autor {
        return Autor(nome = this.nome)
    }
}
