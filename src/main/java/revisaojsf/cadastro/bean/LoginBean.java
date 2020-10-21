/**
 * 
 */
package revisaojsf.cadastro.bean;

import java.util.List;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import revisaojsf.cadastro.datacheck.UsuarioCheck;
import revisaojsf.cadastro.domain.Telefone;
import revisaojsf.cadastro.domain.Usuario;

/**
 * @author Jeferson
 *
 */

@ManagedBean(name = "loginBean")
@ViewScoped
public class LoginBean {
	
	private Usuario usuario;
	private List<Telefone> telefones;
	private Telefone telefone;
	private UsuarioCheck usuarioCheck;
	
	public LoginBean() {
		 usuarioCheck = new UsuarioCheck();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	

	public List<Telefone> getTelefones() {
		return telefones;
	}


	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}
	
	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	@PostConstruct
	public void initPage() {
		usuario = new Usuario();
	}
	
	public void novo() {
		telefone = new Telefone();
		telefones = new ArrayList<Telefone>();
	}
	
	public void cadastrarNovoUsuario() {
		try {
			usuario.setTelefones(telefones);
			usuarioCheck.inserirNovoUsuario(usuario);
			mostrarMensagemInfo("Usuario salvo com sucesso! ");
		} catch (Exception e) {
			mostrarMensagemErro("Falha no cadastro do Usuario :"+ e.getMessage());
			e.printStackTrace();
		}finally {
			 clean();
		}
	}
	
	public void adicionarNovoTelefone() {
		telefones.add(telefone);
		telefone = new Telefone();
	}
	
	public void removerTelefone(ActionEvent event) {
		telefone = (Telefone) event.getComponent().getAttributes().get("telefoneSelecionado");
		if (telefones.remove(telefone)) {
			mostrarMensagemInfo("Telefone Removido!");
		}
	}
	
	public String login() {
		try {
			for(Usuario usuarioCadastrado : usuarioCheck.pesquisarTodos()) {
				if (usuarioCadastrado.getEmail().equals(usuario.getEmail())) {
					if (usuarioCadastrado.getSenha().equals(usuario.getSenha())) {
						HttpSession	sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
						sessao.setAttribute("usuarioLogado", usuarioCadastrado);
						return "usuario.xhtml?faces-redirect=true&amp;includeViewParams=true";
						
					}else {
						mostrarMensagemErro("Senha invalida");
						return "login.xhtml";				
					}
				}		
			}
		} catch (Exception e) {
			mostrarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
		mostrarMensagemErro("Login invalido, verifique os dados e tente novamente");
		return "login.xhtml";
	}
	
	public void clean() {
		usuario = new Usuario();
		telefone = new Telefone();
		telefones = new ArrayList<Telefone>();
	}
	
	private void mostrarMensagemInfo(String mensagem) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem,null);
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, message);	
	}
	
	private void mostrarMensagemErro(String mensagem) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem,null);
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, message);	
	}
	
}
