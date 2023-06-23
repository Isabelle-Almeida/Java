package br.edu.up.front;
import br.edu.up.entidades.Cliente;
import br.edu.up.negocio.Negocio;
import br.edu.up.persistencia.ClientePersistencia;
public class AppClientes {
	public AppClientes()
	{
		int opc;
		do{
			System.out.println("\n\n");
			System.out.println("*** CLIENTES ***");
			System.out.println("1 - Novo cliente");
			System.out.println("2 - Consultar cliente");
			System.out.println("3 - Excluir cliente");
			System.out.println("4 - Voltar");
			opc = Console.readInt("Opção: ");
			switch(opc){
				case 1:
					incluirCliente();
					break;
				case 2:
					consultarClientes();
					break;
				case 3:
					excluirClientes();
					break;				
			}
		}while(opc != 4);
	}
	private static void incluirCliente(){
		System.out.println("\n\n*** INCLUSÃO DE CLIENTES ***");
		Cliente objCliente = new Cliente();
		objCliente.setCpf(Console.readString("\n\nInforme o CPF do cliente: "));
		if(Negocio.isCPF(objCliente.getCpf())) {			
			if(ClientePersistencia.procurarPorCPF(objCliente) == null) {
				objCliente.setNome(Console.readString("Informe o nome do cliente: "));
				ClientePersistencia.incluir(objCliente);
				System.out.println("\n\nInclusão bem sucedida...");
			}
			else {
				System.out.println("\n\nCliente já cadastrado...");
			}
		}
		else {
			System.out.println("\n\nCPF inválido...");
		}
	}
	
	private static void consultarClientes() {
		System.out.println("\n\n*** CONSULTA DE CLIENTES ***");
		Cliente objCliente = new Cliente();
		objCliente.setCpf(Console.readString("Informe o CPF a ser consultado: "));
		objCliente = ClientePersistencia.procurarPorCPF(objCliente);
		if(objCliente != null) {
			System.out.println("\n\n-----------------------");
			System.out.println("ID: " + objCliente.getId());
			System.out.println("Nome: " + objCliente.getNome());
			System.out.println("CPF: " + objCliente.getCpf());			
		}
		else {
			System.out.println("\n\nCliente não encontrado...");
		}
	}
	
	private static void excluirClientes() {
		System.out.println("\n\n*** EXCLUSÃO DE CLIENTES ***");
		Cliente objCliente = new Cliente();
		objCliente.setCpf(Console.readString("Informe o CPF a ser consultado: "));
		objCliente = ClientePersistencia.procurarPorCPF(objCliente);
		if(objCliente != null) {
			System.out.println("\n\n-----------------------");
			System.out.println("ID: " + objCliente.getId());
			System.out.println("Nome: " + objCliente.getNome());
			System.out.println("CPF: " + objCliente.getCpf());
			System.out.println("-----------------------");
			String resp = Console.readString("\n\nQuer excluir esse cliente? ");
			if(resp.equals("S")) {
				if(ClientePersistencia.excluir(objCliente) == true) {
					System.out.println("\n\nA exclusão foi realizada...");
				}
				else {
					System.out.println("\n\nA exclusão não pôde ser realizada no momento...");
				}
			}
		}
		else {
			System.out.println("\n\nCliente não encontrado...");
		}
	}
}
