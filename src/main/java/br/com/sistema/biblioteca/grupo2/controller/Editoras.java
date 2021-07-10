package br.com.sistema.biblioteca.grupo2.controller;

import br.com.sistema.biblioteca.grupo2.modelo.dao.EditoraDAO;
import br.com.sistema.biblioteca.grupo2.modelo.entidade.Editora;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class Editoras {
	
	@Autowired
	EditoraDAO editoraDAO;
	
	@RequestMapping(path = "/editoras/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Editora cadastrar(@RequestBody Editora editora) {
		editora.setId(0);
		return editoraDAO.save(editora);
	}
	
	public Iterable<Editora> listar() {
		return editoraDAO.findAll();
	}

}
