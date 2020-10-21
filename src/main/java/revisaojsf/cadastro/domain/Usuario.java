/**
 * 
 */
package revisaojsf.cadastro.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Jeferson
 *
 */
@Entity
@Table(name="USUARIO")
public class Usuario {
	
	@Id
	@Column(name="ID_USUARIO", nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id_usuario;
	@Column(name="NOME", nullable = false)
	private String nome;
	@Column(name="EMAIL", nullable = false)
	private String email;
	@Column(name="SENHA", nullable = false)
	private String senha;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="id_usuario")
	private List<Telefone> telefones;
    
    
	
	public Usuario() {
		
	}
	
	public Usuario(String nome, String email, String senha, List<Telefone> telefones) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.telefones = telefones;
	}
	
	/**
	 * @return the telefones
	 */
	public List<Telefone> getTelefones() {
		return telefones;
	}
	/**
	 * @param telefones the telefones to set
	 */
	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}
	/**
	 * @return the id_usuario
	 */
	public long getId_usuario() {
		return id_usuario;
	}
	/**
	 * @param id_usuario the id_usuario to set
	 */
	public void setId_usuario(long id_usuario) {
		this.id_usuario = id_usuario;
	}
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}
	/**
	 * @param senha the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@Override
	public String toString() {
		return "Usuario [id_usuario=" + id_usuario + ", nome=" + nome + ", email=" + email + ", senha=" + senha
				+ ", telefones=" + telefones + "]";
	}
	
}
