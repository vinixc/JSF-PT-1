package br.com.vini.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.PersistenceException;

import br.com.vini.livraria.dao.DAO;
import br.com.vini.livraria.entity.Autor;
import br.com.vini.livraria.util.RedirectView;

@Named
@ViewScoped 
public class AutorBean implements Serializable{
	private static final long serialVersionUID = -1458672100805487164L;
	
	private Autor autor = new Autor();
	
	private Integer autorId;

	public Autor getAutor() {
		return autor;
	}

	public RedirectView gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());
		
		if(this.autor.getId() != null) {
			new DAO<Autor>(Autor.class).atualiza(this.autor);
		}else {
			new DAO<Autor>(Autor.class).adiciona(this.autor);
		}
		
		this.autor = new Autor();
		
		return new RedirectView("livro");
	}
	
	public List<Autor> listaAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}
	
	public void removerAutor(Autor autor) {
		try {
			new DAO<Autor>(Autor.class).remove(autor);
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
		this.autor = new DAO<Autor>(Autor.class).buscaPorId(autorId);
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
