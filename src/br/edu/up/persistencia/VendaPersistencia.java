package br.edu.up.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.edu.up.entidades.Cliente;
import br.edu.up.entidades.Venda;

public class VendaPersistencia {
	public static boolean incluir(Venda venda){
		try{
			EntityManager manager = EntityManagerFactory.getInstance();
			manager.getTransaction().begin();
			manager.persist(venda);
			manager.getTransaction().commit();	
			return true;			
		}
		catch(Exception e){
			return false;
		}
	}
	
	public static boolean excluir(Venda venda){
		try{
			EntityManager manager = EntityManagerFactory.getInstance();
			manager.getTransaction().begin();
			manager.remove(venda);
			manager.getTransaction().commit();
			return true;			
			
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	public static Venda procurarPorId(Venda venda){
		EntityManager manager = EntityManagerFactory.getInstance();
		Query consulta = manager.createQuery("from Venda where codigoVenda = :param");
		consulta.setParameter("param", venda.getCodigoVenda());
		List<Venda> vendas = consulta.getResultList();
		if(!vendas.isEmpty()){
			return vendas.get(0);
		}
		return null;
	}
}
