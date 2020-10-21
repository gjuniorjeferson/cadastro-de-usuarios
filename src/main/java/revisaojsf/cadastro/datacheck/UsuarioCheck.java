/**
 * 
 */
package revisaojsf.cadastro.datacheck;

import java.util.List;

import revisaojsf.cadastro.dao.UsuarioDAO;
import revisaojsf.cadastro.dao.generic.DAOFactory;
import revisaojsf.cadastro.domain.Usuario;

/**
 * @author Jeferson
 *
 */
public class UsuarioCheck {

	private UsuarioDAO dao;

	public UsuarioCheck() {
		dao = DAOFactory.getUsuarioDAO();
	}

	/**
	 * Insere um novo usuario no banco de dados
	 * 
	 * @param usuario
	 * @throws Exception
	 */

	public void inserirNovoUsuario(Usuario usuario) throws Exception {
		verificaDados(usuario);
		insert(usuario);
	}
	
	/**
	 * Lista todos os usuarios cadastrados
	 * 
	 * @return List com todos os usuarios cadastrados
	 */
	
	public List<Usuario> pesquisarTodos(){
		return getAll();	
	}
	
	/**
	 * Exclui um usuario do banco de dados
	 * 
	 * @param objeto 
	 * @throws Exception
	 */
	
	public void excluirUsuario(Usuario usuario) throws Exception {
		verificaDados(usuario);
		remove(usuario);	
	}
	
	/**
	 * Edita um Usuario cadastrado no banco de dados
	 * 
	 * @param usuario
	 * @throws Exception 
	 */
	public void editarUsuario(Usuario usuario) throws Exception {
		verificaDados(usuario);
		merge(usuario);	
	}
	
	/* Funcoes internas */

	private void verificaDados(Usuario usuario) throws Exception {
		if (usuario == null)
			throw new Exception("Usuario nao pode ser null");
		if (usuario.getNome().isEmpty())
			throw new Exception("Nome invalido");
		if (usuario.getEmail().isEmpty())
			throw new Exception("E-mail invalido");
		if (usuario.getSenha().isEmpty())
			throw new Exception("Senha invalida");
		
	}

	private void insert(Usuario usuario) {
		dao.insert(usuario);
	}
	
	private List<Usuario> getAll() {
		return dao.getAll();
	}
	
	private void remove(Usuario usuario) {
		dao.remove(usuario);
	}
	
	private void merge(Usuario usuario) {
		dao.update(usuario);
	}

}
