package br.com.sistema.biblioteca.grupo2.modelo.dao;

import br.com.sistema.biblioteca.grupo2.modelo.entidade.Categoria;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaDAO extends CrudRepository<Categoria,Integer> {

}
