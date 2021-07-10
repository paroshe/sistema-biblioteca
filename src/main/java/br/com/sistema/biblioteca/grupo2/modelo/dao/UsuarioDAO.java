package br.com.sistema.biblioteca.grupo2.modelo.dao;

import br.com.sistema.biblioteca.grupo2.modelo.entidade.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioDAO extends CrudRepository<Usuario, Integer>{
	 
	public Usuario findByLogin(String login);
}
