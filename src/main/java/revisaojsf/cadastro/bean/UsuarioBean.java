package revisaojsf.cadastro.bean;

import java.util.ArrayList;
import java.util.List;

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

@ManagedBean(name = "usuarioBean")
@ViewScoped
public class UsuarioBean {

	private Usuario usuario;
	private UsuarioCheck usuarioCheck;
	private List<Usuario> usuarios;
	private Usuario usuarioLogado;
	private Telefone telefone;
	private List<Telefone> telefones;
	
	public UsuarioBean() {
		usuarioCheck = new UsuarioCheck();
		this.atualizarUsuarioLogado();
		this.telefone = new Telefone();
		this.telefones = new ArrayList<Telefone>();
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

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@PostConstruct
	public void initPage() {
		usuario = new Usuario();
		
		try {
			usuarios = usuarioCheck.pesquisarTodos();
		} catch (Exception e) {
			mostrarMensagemErro("Erro ao consultar: "+e.getMessage());
		}

	}

	public void excluir(ActionEvent event) {
		usuario = (Usuario) event.getComponent().getAttributes().get("usuarioSelecionado");

		try {
			usuarioCheck.excluirUsuario(usuario);
			usuarios = usuarioCheck.pesquisarTodos();
		
		} catch (Exception e) {
			mostrarMensagemErro("Erro ao excluir: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void selecionarUsuario(ActionEvent event) {
		usuario = (Usuario) event.getComponent().getAttributes().get("usuarioSelecionado");
		telefones = usuario.getTelefones();
	}
	
	public void salvarUsuario() {
		usuario.setTelefones(telefones);
		try {
			usuarioCheck.editarUsuario(usuario);
			mostrarMensagemInfo("Usuario Editado com Sucesso!!!");
			usuarios = usuarioCheck.pesquisarTodos();
		} catch (Exception e) {
			mostrarMensagemErro("Erro ao Editar usuario: "+ e.getMessage());
			e.printStackTrace();
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

	public void atualizarUsuarioLogado() {
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

	}

	private void mostrarMensagemInfo(String mensagem) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, null);
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, message);
	}

	private void mostrarMensagemErro(String mensagem) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, null);
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, message);
	}
}
