package br.com.vini.livraria.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.vini.livraria.dao.UsuarioDao;
import br.com.vini.livraria.entity.Usuario;
import br.com.vini.livraria.util.RedirectView;

@ManagedBean
@ViewScoped
public class LoginBean {

	private Usuario usuario = new Usuario();
	
	public String efetuaLogin() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		System.out.println("Fazendo Login do usuario: " +  usuario.getEmail());
		
		boolean existe = new UsuarioDao().existe(this.usuario);
		
		if(existe) {
			context.getExternalContext().getSessionMap().put("usuario", this.usuario);
			return new RedirectView("livro").toString();
		}else {
			context.getExternalContext().getFlash().setKeepMessages(true);
			context.addMessage(null, new FacesMessage("Usuario ou senha incorretos!"));
			return "login?faces-redirect=true";
		}
	}
	
	public RedirectView deslogar() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("usuario");
		return new RedirectView("login");
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
