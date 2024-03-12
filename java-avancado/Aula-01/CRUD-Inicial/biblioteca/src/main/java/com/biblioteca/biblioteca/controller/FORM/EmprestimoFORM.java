package com.biblioteca.biblioteca.controller.FORM;

import java.time.LocalDateTime;

import com.biblioteca.biblioteca.model.Emprestimo;

public class EmprestimoFORM {
	
	private Long id_livro;
	private Long id_cliente;
	private LocalDateTime data_emprestimo;
	private LocalDateTime data_devolucao;
	

	
    public EmprestimoFORM(Long id_livro, Long id_cliente, LocalDateTime data_emprestimo, LocalDateTime data_devolucao) {
        super();
        this.id_livro = id_livro;
        this.id_cliente = id_cliente;
        this.data_emprestimo = (data_emprestimo==null) ?  LocalDateTime.now() : data_emprestimo;
        this.data_devolucao = data_devolucao;
    }

    public EmprestimoFORM(Long id_livro, Long id_cliente) {
        this(id_livro, id_cliente, LocalDateTime.now(), null);
    }
    
	
    public EmprestimoFORM(Long id_livro, Long id_cliente, LocalDateTime data_emprestimo) {
        this(id_livro, id_cliente, data_emprestimo, null);

    }
    


	public EmprestimoFORM() {
		super();
	}

	
	

	public LocalDateTime getData_emprestimo() {
		return data_emprestimo;
	}

	public void setData_emprestimo(LocalDateTime data_emprestimo) {
		this.data_emprestimo = data_emprestimo;
	}

	public LocalDateTime getData_devolucao() {
		return data_devolucao;
	}

	public void setData_devolucao(LocalDateTime data_devolucao) {
		this.data_devolucao = data_devolucao;
	}

	public Long getId_livro() {
		return id_livro;
	}


	public void setId_livro(Long id_livro) {
		this.id_livro = id_livro;
	}


	public Long getId_cliente() {
		return id_cliente;
	}


	public void setId_cliente(Long id_cliente) {
		this.id_cliente = id_cliente;
	}
	
	
	public Emprestimo toEmprestimo() {
	    Emprestimo emprestimo = new Emprestimo();
	    emprestimo.setData_emprestimo(this.data_emprestimo);
	    emprestimo.setData_devolucao(this.data_devolucao); 
	    return emprestimo;
	}

	@Override
	public String toString() {
		return "EmprestimoFORM [id_livro=" + id_livro + ", id_cliente=" + id_cliente + ", data_emprestimo="
				+ data_emprestimo + ", data_devolucao=" + data_devolucao + "]";
	}

	
	

}
