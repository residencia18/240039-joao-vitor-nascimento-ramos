package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.modelo.RelatorioVendasVO;

public class PedidoDao {
	
	private EntityManager em;

	
	
	public PedidoDao(EntityManager em) {
		super();
		this.em = em;
	}



	public void cadastrar(Pedido pedido) {
		this.em.persist(pedido);
	}
	
	
	public BigDecimal valorTotalVendido() {
		String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
		return em.createQuery(jpql, BigDecimal.class).getSingleResult();
	}
	
	public List<RelatorioVendasVO> relatorioDeVendas(){
		String jpql = "SELECT new br.com.alura.loja.modelo.RelatorioVendasVO("
				+ "produto.nome, "
				+ "SUM(item.quantidade), "
				+ "MAX(pedido.data) "
				+ ")"
				+ "FROM Pedido pedido "
				+ "JOIN pedido.itens item "
				+ "JOIN item.produto produto "
				+ "GROUP BY produto.nome "
				+ "ORDER BY SUM(item.quantidade) DESC"; 
		return em.createQuery(jpql,RelatorioVendasVO.class).getResultList();
	}
	
	
	public Pedido buscarPedidoComCliente(Long id) {
		return em.createQuery("SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id = :id",Pedido.class).setParameter("id", id).getSingleResult();
	}
	


}
