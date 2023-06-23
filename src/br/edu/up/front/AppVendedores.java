package br.edu.up.front;

import br.edu.up.entidades.Vendedor;
import br.edu.up.negocio.Negocio;
import br.edu.up.persistencia.VendedorPersistencia;

public class AppVendedores {
	public AppVendedores()
	{
		int opc;
		do{
			System.out.println("\n\n");
			System.out.println("*** VENDEDORES ***");
			System.out.println("1 - Novo vendedor");
			System.out.println("2 - Consultar vendedor");
			System.out.println("3 - Excluir vendedor");
			System.out.println("4 - Voltar");
			opc = Console.readInt("Opção: ");
			switch(opc){
				case 1:
					incluirVendedor();
					break;
				case 2:
					consultarVendedor();
					break;
				case 3:
					excluirVendedor();
					break;				
			}
		}while(opc != 4);
	}
	private static void incluirVendedor(){
		System.out.println("\n\n*** INCLUSÃO DE VENDEDORES ***");
		Vendedor objVendedor = new Vendedor();
		objVendedor.setCpf(Console.readString("\n\nInforme o CPF do vendedor: "));
		if(Negocio.isCPF(objVendedor.getCpf())) {			
			if(VendedorPersistencia.procurarPorCPF(objVendedor) == null) {
				objVendedor.setNome(Console.readString("Informe o nome do vendedor: "));
				objVendedor.setTaxaComissao(Console.readFloat("Informe a taxa de comissão: "));
				VendedorPersistencia.incluir(objVendedor);
				System.out.println("\n\nInclusão bem sucedida...");
			}
			else {
				System.out.println("\n\nVendedor já cadastrado...");
			}
		}
		else {
			System.out.println("\n\nCPF inválido...");
		}
	}
	
	private static void consultarVendedor() {
		System.out.println("\n\n*** CONSULTA DE VENDEDORES ***");
		Vendedor objVendedor = new Vendedor();
		objVendedor.setCpf(Console.readString("Informe o CPF a ser consultado: "));
		objVendedor = VendedorPersistencia.procurarPorCPF(objVendedor);
		if(objVendedor != null) {
			System.out.println("\n\n-----------------------");
			System.out.println("ID: " + objVendedor.getId());
			System.out.println("Nome: " + objVendedor.getNome());
			System.out.println("CPF: " + objVendedor.getCpf());
			System.out.println("Taxa de comissão: " + objVendedor.getTaxaComissao());
		}
		else {
			System.out.println("\n\nVendedor não encontrado...");
		}
	}
	
	private static void excluirVendedor() {
		System.out.println("\n\n*** EXCLUSÃO DE VENDEDORES ***");
		Vendedor objVendedor = new Vendedor();
		objVendedor.setCpf(Console.readString("Informe o CPF a ser consultado: "));
		objVendedor = VendedorPersistencia.procurarPorCPF(objVendedor);
		if(objVendedor != null) {
			System.out.println("\n\n-----------------------");
			System.out.println("ID: " + objVendedor.getId());
			System.out.println("Nome: " + objVendedor.getNome());
			System.out.println("CPF: " + objVendedor.getCpf());
			System.out.println("Taxa de comissão: " + objVendedor.getTaxaComissao());
			System.out.println("-----------------------");
			String resp = Console.readString("\n\nQuer excluir esse vendedor? ");
			if(resp.equals("S")) {
				if(VendedorPersistencia.excluir(objVendedor) == true) {
					System.out.println("\n\nA exclusão foi realizada...");
				}
				else {
					System.out.println("\n\nA exclusão não pôde ser realizada no momento...");
				}
			}
		}
		else {
			System.out.println("\n\nVendedor não encontrado...");
		}
	}
}
