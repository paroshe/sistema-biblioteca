package br.com.sistema.biblioteca.grupo2.modelo.entidade;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ElementCollection;


@Entity
public class Usuario implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(unique=true)
	private String login;
	
	@JsonIgnore  
	private String senha;
	
	@Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String novaSenha;
	
	@ElementCollection(fetch = FetchType.EAGER)
    private List<String> permissoes;  
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getNovaSenha() {
	        return novaSenha;
	}

	 public void setNovaSenha(String novaSenha) {
	        this.novaSenha = novaSenha;
	}
	       
	public void setPermissoes(List<String> permissoes) {
        this.permissoes = permissoes;
    }

    public List<String> getPermissoes() {
        return permissoes;
    }

	

}
