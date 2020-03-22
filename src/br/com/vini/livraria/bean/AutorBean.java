package br.com.vini.livraria.bean;

import javax.faces.bean.ManagedBean;

import br.com.vini.livraria.dao.DAO;
import br.com.vini.livraria.entity.Autor;
import br.com.vini.livraria.util.RedirectView;

@ManagedBean
public class AutorBean {

	private Autor autor = new Autor();

	public Autor getAutor() {
		return autor;
	}

	public RedirectView gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		new DAO<Autor>(Autor.class).adiciona(this.autor);
		
		this.autor = new Autor();
		
		return new RedirectView("livro");
	}
}
