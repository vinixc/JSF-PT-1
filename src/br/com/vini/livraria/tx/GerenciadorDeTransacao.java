package br.com.vini.livraria.tx;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Transacional
@Interceptor
public class GerenciadorDeTransacao implements Serializable{
	private static final long serialVersionUID = -8763996698548745200L;
	
	@Inject
	private EntityManager manager;
	
	@AroundInvoke
	public Object executaTX(InvocationContext contexto) throws Exception {
		try {
		// abre transacao
		manager.getTransaction().begin();
		
		//chama dao
		Object resultado = contexto.proceed();
		
		// commita a transacao
		manager.getTransaction().commit();
		
		return resultado;
		
		}catch(Exception e) {
			manager.getTransaction().rollback();
			return null;
		}
	}
}
