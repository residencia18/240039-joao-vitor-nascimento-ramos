package br.com.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.CategoriaId;
import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.ItemPedido;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {
	
	public static void main(String[] args) {
		popularBanco();
		EntityManager em = JPAUtil.getEntityManager();
		
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		em.find(Categoria.class, new CategoriaId("CELULARES","XPTO"));

//		produtoDao.buscarPorParametros("PS5", null, LocalDate.now());
//		em.close();

//	    em.getTransaction().begin();


//		
//		em.getTransaction().commit();
//		System.out.println(pedidoDao.valorTotalVendido());
//		
//		List<RelatorioVendasVO> relatorio = pedidoDao.relatorioDeVendas();
//		relatorio.forEach(System.out::println);
//		
	
	}

	private static void popularBanco() {
		Categoria celulares = new Categoria("CELULARES");
		Categoria videoGames = new Categoria("VIDEOGAMES");
		Categoria notebooks = new Categoria("NOTEBOOKS");

		Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares );
		Produto mac = new Produto("MacBook", "Muito legal", new BigDecimal("16000"), notebooks );
		Produto ps5 = new Produto("PS5", "Muito legal", new BigDecimal("4500"), notebooks );

		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(celulares);
		categoriaDao.cadastrar(videoGames);
		categoriaDao.cadastrar(notebooks);

		produtoDao.cadastrar(celular);
		produtoDao.cadastrar(mac);
		produtoDao.cadastrar(ps5);
		
		Cliente cliente = new Cliente("João","04999695537");
		ClienteDao clienteDao = new ClienteDao(em);
		clienteDao.cadastrar(cliente);
		Pedido pedido = new Pedido(cliente);
		ItemPedido item = new  ItemPedido(10,pedido,produtoDao.buscarPorId(1l));
		ItemPedido item2 = new  ItemPedido(15,pedido,produtoDao.buscarPorId(3l));
		ItemPedido item3 = new  ItemPedido(2,pedido,produtoDao.buscarPorId(2l));

		pedido.adicionarItem(item);
		pedido.adicionarItem(item2);
		pedido.adicionarItem(item3);


		PedidoDao pedidoDao = new PedidoDao(em);
		pedidoDao.cadastrar(pedido);

		
		em.getTransaction().commit();
		em.close();
	}

}
