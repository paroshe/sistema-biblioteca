package br.com.sistema.biblioteca.grupo2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.com.sistema.biblioteca.grupo2.modelo.dao.CategoriaDAO;
import br.com.sistema.biblioteca.grupo2.modelo.entidade.Categoria;

@RestController
@RequestMapping("/api")
public class Categorias {
	
	@Autowired
	CategoriaDAO categoriaDAO;
	
	@RequestMapping(path = "/categorias/", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.CREATED)
	public Categoria cadastrar(@RequestBody Categoria categoria) {
		categoria.setId(0);
		return categoriaDAO.save(categoria);
	}
	
	public Iterable<Categoria> listar() {
		return categoriaDAO.findAll();
	}

}
