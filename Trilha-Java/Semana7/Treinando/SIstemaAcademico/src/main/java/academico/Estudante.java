package academico;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Estudante {
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	private Integer id;
	@ManyToOne
	@JoinColumn(name="CodCurso")
	private Curso curso;
	private String nome;
	private String email;
	private String matricula;
	
	Estudante(){
		this.id = null;
	}
		

	public Estudante(Curso curso, String nome, String email, String matricula) {
		this.id = null;
		this.curso = curso;
		this.nome = nome;
		this.email = email;
		this.matricula = matricula;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	@Override
	public String toString() {
		return "Estudante [id=" + id + ", nome=" + nome + ", email=" + email + ", matricula=" + matricula + "]";
	}
	
	
}
