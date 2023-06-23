package br.edu.up.front;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.edu.up.entidades.Cliente;
import br.edu.up.entidades.ItemVenda;
import br.edu.up.entidades.Produto;
import br.edu.up.entidades.Venda;
import br.edu.up.entidades.Vendedor;
import br.edu.up.negocio.ItemVendaNegocio;
import br.edu.up.negocio.ProdutoNegocio;
import br.edu.up.persistencia.ClientePersistencia;
import br.edu.up.persistencia.ProdutoPersistencia;
import br.edu.up.persistencia.VendaPersistencia;
import br.edu.up.persistencia.VendedorPersistencia;

public class AppVendas {
	public AppVendas() {
		int opc;
		do{
			System.out.println("\n\n");
			System.out.println("*** VENDAS ***");
			System.out.println("1 - Nova venda");
			System.out.println("2 - Consultar venda");
			System.out.println("3 - Excluir venda");
			System.out.println("4 - Voltar");
			opc = Console.readInt("Opção: ");
			switch(opc){
				case 1:
					incluirVenda();
					break;
				case 2:
					consultarVenda();
					break;
				case 3:
					//excluirVenda();
					break;				
			}
		}while(opc != 4);
	}
	
	private static void incluirVenda() {
		System.out.println("\n\n*** INCLUSÃO DE VENDAS ***");
		SimpleDateFormat formato = new SimpleDateFormat("dd/mm/yyyy");
		boolean dataInvalida = true;
		String dataVenda, resposta;
		Date dataDate;
		Venda objVenda = new Venda();
		objVenda.setCodigoVenda(Console.readInt("\n\nInforme o código da venda: "));
		if(VendaPersistencia.procurarPorId(objVenda) == null) {
			do {
				dataVenda = Console.readString("Informe a data da venda: ");
				try{
					dataDate = (Date) formato.parse(dataVenda);
					objVenda.setDataVenda(dataDate);
					dataInvalida = false;
				} 
				catch (ParseException e){		
					System.out.println("Data inválida. Informe novamente.");
				}
			}while(dataInvalida == true);			
			Cliente objCliente = new Cliente();
			objCliente.setCpf(Console.readString("Informe o CPF do cliente: "));
			objCliente = ClientePersistencia.procurarPorCPF(objCliente);
			if(objCliente != null) {
				//Aqui está sendo associado o OBJETO CLIENTE ao OBJETO VENDA
				objVenda.setCliente(objCliente);
				System.out.println("Cliente -->");
				System.out.println("     Nome: " + objCliente.getNome());
				Vendedor objVendedor = new Vendedor();
				objVendedor.setCpf(Console.readString("Informe o CPF do vendedor: "));
				objVendedor = VendedorPersistencia.procurarPorCPF(objVendedor);
				if(objVendedor != null) {
					//Aqui está sendo associado o OBJETO VENDEDOR ao OBJETO VENDA
					objVenda.setVendedor(objVendedor);
					System.out.println("Vendedor -->");
					System.out.println("     Nome: " + objVendedor.getNome());
					System.out.println("-------------------------");
					System.out.println("DETERMINE OS ÍTENS DA VENDA");
					System.out.println("-------------------------");
					do {
						ItemVenda objItemVenda = new ItemVenda();
						Produto objProduto = new Produto();
						objProduto.setCodigo(Console.readString("Informe o código do produto: "));
						objProduto = ProdutoPersistencia.procurarPorCodigo(objProduto);
						if(objProduto != null) {
							//Aqui está sendo associado o OBJETO PRODUTO ao OBJETO ÍTEMVENDA
							objItemVenda.setProduto(objProduto);
							System.out.println("Produto -->");
							System.out.println("     Nome: " + objProduto.getNome());
							objItemVenda.setQuantidade(Console.readInt("Informe a quantidade do produto: "));
							objItemVenda.setUnitario(ProdutoNegocio.calcularPrecoVenda(objProduto));
							System.out.println("Valor unitário: " + objItemVenda.getUnitario());
							System.out.println("Subtotal: " + ItemVendaNegocio.calcularSubTotal(objItemVenda));
							//Adicionar o objeto ítem ao array de ítens na venda
							objVenda.getItens().add(objItemVenda);							
						}
						else {
							System.out.println("\n\nProduto não cadastrado...");
						}
						resposta = Console.readString("Mais ítens? ");
					}while(resposta.equals("S"));
					if(VendaPersistencia.incluir(objVenda) == true) {
						System.out.println("\n\nVenda foi incluída...");
					}
					else {
						System.out.println("\n\nNão foi possível cadastrar a venda...");
					}
				}
			}
			else {
				System.out.println("\n\nCliente não cadastrado...");
			}
		}
		else {
			System.out.println("\n\nVenda já cadastrada...");
		}
	}
	
	private static void consultarVenda() {
		System.out.println("\n\n*** INCLUSÃO DE VENDAS ***");
		SimpleDateFormat formato = new SimpleDateFormat("dd/mm/yyyy");
		Venda objVenda = new Venda();
		objVenda.setCodigoVenda(Console.readInt("\n\nInforme o código da venda: "));
		objVenda = VendaPersistencia.procurarPorId(objVenda);
		if(objVenda != null) {
			System.out.println("Data: " + formato.format(objVenda.getDataVenda()));
			System.out.println("Cliente: " + objVenda.getCliente().getNome());
			System.out.println("Vendedor: " + objVenda.getVendedor().getNome());
			System.out.println("---------------------------");
			System.out.println("***ÍTENS DA VENDA***");
			System.out.println("---------------------------");
			for(ItemVenda item: objVenda.getItens()) {
				System.out.println("Produto: " + item.getProduto().getNome());
				System.out.println("Quantidade: " + item.getQuantidade());
				System.out.println("Unitário: " + item.getUnitario());
				System.out.println("Subtotal: " + ItemVendaNegocio.calcularSubTotal(item));
				System.out.println("---------------------------");
			}
		}
		else {
			System.out.println("\n\nVenda não cadastrada...");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
