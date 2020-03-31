package br.com.vini.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PersistenceException;

import br.com.vini.livraria.dao.AutorDao;
import br.com.vini.livraria.entity.Autor;
import br.com.vini.livraria.util.RedirectView;

@Named
@ViewScoped 
public class AutorBean implements Serializable{
	private static final long serialVersionUID = -1458672100805487164L;
	
	private Autor autor = new Autor();
	
	private Integer autorId;
	
	@Inject
	private AutorDao dao;

	public Autor getAutor() {
		return autor;
	}

	public RedirectView gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());
		
		if(this.autor.getId() != null) {
			this.dao.atualiza(this.autor);
		}else {
			this.dao.adiciona(this.autor);
		}
		
		this.autor = new Autor();
		
		return new RedirectView("livro");
	}
	
	public List<Autor> listaAutores() {
		return this.dao.listaTodos();
	}
	
	public void removerAutor(Autor autor) {
		try {
			this.dao.remove(autor);
		}catch(PersistenceException e) {
			e.printStackTrace();

			FacesMessage message = new FacesMessage();
			message.setSummary("O Autor n�o deve ter livros cadastrados para remo��o.");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			
			FacesContext.getCurrentInstance().addMessage(null, message);
			return;
		}
	}
	
	public void carregaPeloId() {
		this.autor = this.dao.buscaPorId(autorId);
	}
	
	public void carregaAutor(Autor autor) {
		this.autor = autor;
	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}
}
