package br.edu.up.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.edu.up.entidades.Cliente;
import br.edu.up.entidades.Produto;

public class ProdutoPersistencia {
	public static boolean incluir(Produto produto){
		try{
			EntityManager manager = EntityManagerFactory.getInstance();
			manager.getTransaction().begin();
			manager.persist(produto);
			manager.getTransaction().commit();	
			return true;			
		}
		catch(Exception e){
			return false;
		}
	}
	
	public static boolean excluir(Produto produto){
		try{
			EntityManager manager = EntityManagerFactory.getInstance();
			manager.getTransaction().begin();
			manager.remove(produto);
			manager.getTransaction().commit();
			return true;			
			
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	public static Produto procurarPorNome(Produto produto){
		EntityManager manager = EntityManagerFactory.getInstance();
		Query consulta = manager.createQuery("from Produto where nome = :param");
		consulta.setParameter("param", produto.getNome());
		List<Produto> produtos = consulta.getResultList();
		if(!produtos.isEmpty()){
			return produtos.get(0);
		}
		return null;
	}
	
	public static Produto procurarPorCodigo(Produto produto){
		EntityManager manager = EntityManagerFactory.getInstance();
		Query consulta = manager.createQuery("from Produto where codigo = :param");
		consulta.setParameter("param", produto.getCodigo());
		List<Produto> produtos = consulta.getResultList();
		if(!produtos.isEmpty()){
			return produtos.get(0);
		}
		return null;
	}
}
