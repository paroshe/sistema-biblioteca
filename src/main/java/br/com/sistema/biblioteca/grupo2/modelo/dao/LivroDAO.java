package br.com.sistema.biblioteca.grupo2.modelo.dao;

import java.util.List;

import br.com.sistema.biblioteca.grupo2.modelo.entidade.Categoria;
import br.com.sistema.biblioteca.grupo2.modelo.entidade.Editora;
import br.com.sistema.biblioteca.grupo2.modelo.entidade.Livro;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LivroDAO extends CrudRepository<Livro, Integer>{
	
	List<Livro> findByTituloContaining(String titulo);
    List<Livro> findByTituloStartingWith(String titulo);
    List<Livro> findByAutorContaining(String autor);
    List<Livro> findByAutorStartingWith(String autor);
    List<Livro> findByCategoria(Categoria categoria);
    List<Livro> findByEditoras(Editora editora);

}
