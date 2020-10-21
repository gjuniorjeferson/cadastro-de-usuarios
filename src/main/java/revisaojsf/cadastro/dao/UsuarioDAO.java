package revisaojsf.cadastro.dao;

import javax.persistence.EntityManagerFactory;
import revisaojsf.cadastro.domain.Usuario;
import revisaojsf.cadastro.dao.generic.DAOGenerico;

/**
 * @author Jeferson
 *
 */
public class UsuarioDAO extends DAOGenerico<Usuario> {
	
	public  UsuarioDAO(EntityManagerFactory emf) {
		super(emf);
	}
	
}
