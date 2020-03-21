package br.com.vini.livraria.bean;

import javax.faces.bean.ManagedBean;

import br.com.vini.livraria.entity.Livro;

@ManagedBean
public class LivroBean {
	
	private Livro livro = new Livro();

	public void gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());
		
		this.livro = new Livro();
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}
}
