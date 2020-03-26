package br.com.vini.livraria.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.vini.livraria.entity.Usuario;

public class UsuarioDao {

	public boolean existe(Usuario usuario) {
		try {
		EntityManager em = new JPAUtil().getEntityManager();
		Usuario resultado = em.createQuery("select u from Usuario u where u.email = :pEmail and u.senha = :pSenha", Usuario.class)
				.setParameter("pEmail", usuario.getEmail())
				.setParameter("pSenha", usuario.getSenha())
				.getSingleResult();
		
		em.close();

		return resultado != null;
		
		}catch(NoResultException e) {
			return false;
		}
	}

}
