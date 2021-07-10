package br.com.sistema.biblioteca.grupo2.controller;

import br.com.sistema.biblioteca.grupo2.erro.NaoEncontrado;
import br.com.sistema.biblioteca.grupo2.erro.RequisicaoInvalida;
import br.com.sistema.biblioteca.grupo2.modelo.dao.CategoriaDAO;
import br.com.sistema.biblioteca.grupo2.modelo.dao.EditoraDAO;
import br.com.sistema.biblioteca.grupo2.modelo.dao.LivroDAO;
import br.com.sistema.biblioteca.grupo2.modelo.entidade.Categoria;
import br.com.sistema.biblioteca.grupo2.modelo.entidade.Editora;
import br.com.sistema.biblioteca.grupo2.modelo.entidade.Livro;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Livros {
	
	@Autowired
	LivroDAO livroDAO;
	
	@Autowired
	CategoriaDAO categoriaDAO;
	
	@Autowired
	EditoraDAO editoraDAO;
	
	@RequestMapping(path = "/livros/pesquisar/editora", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Livro> pesquisaEditora(@RequestParam int id) {
		Optional<Editora> optionalEditora = editoraDAO.findById(id);
		if(!optionalEditora.isPresent())
			throw new NaoEncontrado("ID não encontrado!");
		Editora editora = optionalEditora.get();
		return livroDAO.findByEditoras(editora);
	}
	
	@RequestMapping(path = "/livros/pesquisar/categoria", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Livro> pesquisarCategoria(@RequestParam int id){
		Optional<Categoria> optionalCategoria = categoriaDAO.findById(id);
		if(!optionalCategoria.isPresent())
			throw new NaoEncontrado("ID não encontrado!");
		Categoria categoria = optionalCategoria.get();
		return livroDAO.findByCategoria(categoria);
	}
	
	@RequestMapping(path = "/livros/pesquisar/titulo", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
	public Iterable<Livro> pesquisaTitulo(
			@RequestParam(required = false) String contem,
			@RequestParam(required = false) String comeca){
		if(contem!=null)
			return livroDAO.findByTituloContaining(contem);
		else if (comeca!=null)
			return livroDAO.findByTituloStartingWith(comeca);
		else
			throw new RequisicaoInvalida("Indicar contem ou começa");
			
	}
	
	@RequestMapping(path = "/livros/pesquisar/autor", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
	public Iterable<Livro> pesquisaAutor(
			@RequestParam(required = false) String contem,
			@RequestParam(required = false) String comeca){
		if(contem!=null)
			return livroDAO.findByAutorContaining(contem);
		else if (comeca!=null)
			return livroDAO.findByAutorStartingWith(comeca);
		else
			throw new RequisicaoInvalida("Indicar contem ou começa");
			
	}
	
	@RequestMapping(path = "/livros/", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Livro> listar() {
		return livroDAO.findAll();
	}
	
	@RequestMapping(path = "/livros/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Livro buscar(@PathVariable int id) {
		final Optional<Livro> findById = livroDAO.findById(id);
		if(findById.isPresent()) {
			return findById.get();
		} else {
			throw new NaoEncontrado("ID não encontrado!");
		}
	}
	
	@RequestMapping(path="/livros/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apagar(@PathVariable int id) {
        if(livroDAO.existsById(id)) {
            livroDAO.deleteById(id);
        } else {
            throw new NaoEncontrado("ID não encontrada!");
        }
                 
    }
	
	@RequestMapping(path="/livros/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable int id, @RequestBody Livro livro) {
        final Livro livroBanco = this.buscar(id);

        if(livro.getValor()<0) {
            throw new RequisicaoInvalida("Valor do produto deve ser maior que 0");
        }
        
        livroBanco.setTitulo(livro.getTitulo());
        livroBanco.setValor(livro.getValor());
        livroBanco.setCategoria(livro.getCategoria());
        livroDAO.save(livroBanco);
    }
	
	@RequestMapping(path = "/livros/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Livro cadastrar(@RequestBody Livro livro) {
        if(livro.getValor()<0) {
            throw new RequisicaoInvalida("Valor do produto deve ser maior que 0!");
        } else {
            Livro livroBanco = livroDAO.save(livro);
            return livroBanco;
        }
    }
	
	@RequestMapping(path = "/livros/{idLivro}/editoras/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Editora cadastrarEditora(
            @PathVariable int idLivro, 
            @RequestBody Editora editora) {
        Livro livro = this.buscar(idLivro);
        livro.getEditoras().add(editora);
        livroDAO.save(livro);
        return editora;
    }
	
	@RequestMapping(path = "/livros/{idLivro}/editoras/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Editora> listarEditora(@PathVariable int idLivro) {
        return this.buscar(idLivro).getEditoras();
    }

}
