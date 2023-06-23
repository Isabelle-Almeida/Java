package br.edu.up.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.edu.up.entidades.Vendedor;

public class VendedorPersistencia {
	public static boolean incluir(Vendedor vendedor){
		try{
			EntityManager manager = EntityManagerFactory.getInstance();
			manager.getTransaction().begin();
			manager.persist(vendedor);
			manager.getTransaction().commit();	
			return true;			
		}
		catch(Exception e){
			return false;
		}
	}
	
	public static boolean excluir(Vendedor vendedor){
		try{
			EntityManager manager = EntityManagerFactory.getInstance();
			manager.getTransaction().begin();
			manager.remove(vendedor);
			manager.getTransaction().commit();
			return true;			
			
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	public static Vendedor procurarPorCPF(Vendedor vendedor){
		EntityManager manager = EntityManagerFactory.getInstance();
		Query consulta = manager.createQuery("from Vendedor where cpf = :param");
		consulta.setParameter("param", vendedor.getCpf());
		List<Vendedor> vendedores = consulta.getResultList();
		if(!vendedores.isEmpty()){
			return vendedores.get(0);
		}
		return null;
	}
}
