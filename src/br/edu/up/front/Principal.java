package br.edu.up.front;
public class Principal {
	public static void main(String[] args) {
		int opc;
		do{
			System.out.println("\n\n");
			System.out.println("*** MENU PRINCIPAL ***");
			System.out.println("1 - Clientes");
			System.out.println("2 - Vendedores");
			System.out.println("3 - Produtos");
			System.out.println("4 - Vendas");
			System.out.println("5 - Sair");
			opc = Console.readInt("Opção: ");
			switch(opc){
				case 1:
					new AppClientes();
					break;
				case 2:
					new AppVendedores();
					break;
				case 3:
					new AppProdutos();
					break;
				case 4:
					new AppVendas();
					break;
			}
		}while(opc != 5);
	}
}
