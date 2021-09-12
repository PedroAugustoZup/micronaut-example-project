package br.com.alura.dto.response

import br.com.alura.model.Autor

class AutorResponse(autor: Autor) {
    val nome = autor.nome
}
