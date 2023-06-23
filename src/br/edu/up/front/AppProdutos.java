package br.edu.up.front;

import br.edu.up.entidades.Produto;
import br.edu.up.negocio.ProdutoNegocio;
import br.edu.up.persistencia.ProdutoPersistencia;

public class AppProdutos {
	public AppProdutos() {
		int opc;
		do{
			System.out.println("\n\n");
			System.out.println("*** PRODUTOS ***");
			System.out.println("1 - Novo produto");
			System.out.println("2 - Consultar produto");
			System.out.println("3 - Excluir produto");
			System.out.println("4 - Voltar");
			opc = Console.readInt("Opção: ");
			switch(opc){
				case 1:
					incluirProduto();
					break;
				case 2:
					consultarProduto();
					break;
				case 3:
					excluirProduto();
					break;				
			}
		}while(opc != 4);
	}
	
	private static void incluirProduto() {
		System.out.println("\n\n***INCLUSÃO DE PRODUTOS***");
		Produto objProduto = new Produto();
		objProduto.setNome(Console.readString("\n\nInforme o nome do produto: "));
		if(ProdutoPersistencia.procurarPorNome(objProduto) == null) {
			objProduto.setCodigo(Console.readString("Informe o código do produto: "));
			objProduto.setPrecoCompra(Console.readFloat("Preço de compra: "));
			objProduto.setMarkup(Console.readFloat("Markup: "));
			if(ProdutoPersistencia.incluir(objProduto) == true) {
				System.out.println("\n\nO produto foi incluido...");
			}
			else {
				System.out.println("\n\nNão foi possível incluir o produto...");
			}
		}
		else {
			System.out.println("\n\nProduto já cadastrado...");
		}
	}
	
	private static void consultarProduto() {
		System.out.println("\n\n***CONSULTA DE PRODUTOS***");
		Produto objProduto = new Produto();
		objProduto.setNome(Console.readString("\n\nInforme o nome do produto: "));
		objProduto = ProdutoPersistencia.procurarPorNome(objProduto);
		if(objProduto != null) {
			System.out.println("ID: " + objProduto.getId());
			System.out.println("Nome: " + objProduto.getNome());
			System.out.println("Código: " + objProduto.getCodigo());
			System.out.println("Preço de compra: " + objProduto.getPrecoCompra());
			System.out.println("Markup: " + objProduto.getMarkup());
			System.out.println("Preço de venda: " + ProdutoNegocio.calcularPrecoVenda(objProduto));
		}
		else {
			System.out.println("\n\nProduto não cadastrado...");
		}
	}
	
	private static void excluirProduto() {
		System.out.println("\n\n***CONSULTA DE PRODUTOS***");
		Produto objProduto = new Produto();
		objProduto.setNome(Console.readString("\n\nInforme o nome do produto: "));
		objProduto = ProdutoPersistencia.procurarPorNome(objProduto);
		if(objProduto != null) {
			System.out.println("ID: " + objProduto.getId());
			System.out.println("Nome: " + objProduto.getNome());
			System.out.println("Código: " + objProduto.getCodigo());
			System.out.println("Preço de compra: " + objProduto.getPrecoCompra());
			System.out.println("Markup: " + objProduto.getMarkup());
			System.out.println("Preço de venda: " + ProdutoNegocio.calcularPrecoVenda(objProduto));
			String resp = Console.readString("\n\nQuer excluir o produto? ");
			if(resp.equals("S")) {
				if(ProdutoPersistencia.excluir(objProduto) == true) {
					System.out.println("\n\nO produto foi excluúdo...");
				}
			}
		}
		else {
			System.out.println("\n\nProduto não cadastrado...");
		}
	}
}
