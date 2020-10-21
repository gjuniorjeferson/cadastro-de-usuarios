package revisaojsf.cadastro.dao.generic;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;
import revisaojsf.cadastro.dao.UsuarioDAO;

/**
 * @author Jeferson Junior
 *
 */

public abstract class DAOFactory {
	
	private static final EntityManagerFactory factory;


	
	static {
		Map<String, String> env = System.getenv();
		Map<String, Object> configOverrides = new HashMap<String, Object>();
		for (String envName : env.keySet()) {
		    if (envName.contains("JDBC_URL")) {    	
		        configOverrides.put("javax.persistence.jdbc.url", env.get(envName));    
		    }
		    if (envName.contains("JDBC_USER")) {	
		        configOverrides.put("javax.persistence.jdbc.user", env.get(envName));    
		    }
		    if (envName.contains("JDBC_PASSWD")) {
		        configOverrides.put("javax.persistence.jdbc.password", env.get(envName));    
		    }
		    // You can put more code in here to populate configOverrides...
		}
		factory = Persistence.createEntityManagerFactory("cadastro-de-usuarios", configOverrides); //setar o nome do persistence-unit aqui
	}
	
	public static UsuarioDAO getUsuarioDAO(){
		UsuarioDAO  dao = new UsuarioDAO(factory);
		return dao;
	}
}

