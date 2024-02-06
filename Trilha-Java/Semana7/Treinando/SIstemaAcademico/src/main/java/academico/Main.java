package academico;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Main {
	
	
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit_academico");
		EntityManager em = emf.createEntityManager();
		mostrarEstudantesPorCurso(em);
	}
	
	
	
	private static void mostrarEstudantesPorCurso(EntityManager em) {
		String jpql = "select c from Curso c where c.id = 1";
		TypedQuery<Curso> typedQuery = em.createQuery(jpql,Curso.class);
		Curso c = typedQuery.getSingleResult();
		String jpqlEstudante = "select e from Estudante e where e.curso = :curso";
		TypedQuery<Estudante> typedQueryEstudante = em.createQuery(jpqlEstudante,Estudante.class);
		typedQueryEstudante.setParameter("curso", c);
		List<Estudante> lista = typedQueryEstudante.getResultList();
		imprimeLista(lista);
	} 
	
	
	private static void cursoDTO(EntityManager em) {
		String jpql = "select new academicoDTO.CursoDTO(c.nome)from Curso c where c.numSemestres < 9";
		TypedQuery<academicoDTO.CursoDTO> typedQuery = em.createQuery(jpql,academicoDTO.CursoDTO.class);
		List<academicoDTO.CursoDTO> lista = typedQuery.getResultList();
		imprimeLista(lista);

	}
	
	private static void gerarEstudanteDTO(EntityManager em) {
		String jpql = "select new academicoDTO.EstudanteDTO(e.nome,e.matricula , e.curso.nome) from Estudante e";
		TypedQuery<academicoDTO.EstudanteDTO> typedQuery = em.createQuery(jpql,academicoDTO.EstudanteDTO.class);
		List<academicoDTO.EstudanteDTO> lista = typedQuery.getResultList();
		imprimeLista(lista);
	}
	
	private static void buscarTodosNomesEstudante(EntityManager em) {
		String jpql = "select e.nome from Estudante e";
		TypedQuery<String> typedQuery = em.createQuery(jpql,String.class);
		List<String> list = typedQuery.getResultList();
		imprimeLista(list);
	}
	
	
	private static void alterarEstudante(EntityManager em) {
		String jpql = "select e from Estudante e where id=44";
		TypedQuery<Estudante> typedQuery = em.createQuery(jpql,Estudante.class);
		Estudante e = typedQuery.getSingleResult();
	      em.getTransaction().begin();
	      e.setEmail("vamo@vamo.com");
	      em.merge(e);
	      em.getTransaction().commit();
	}
	
	private static void buscarEstudante(EntityManager em) {
		String jpql = "select e from Estudante e where id=44";
		TypedQuery<Estudante> typedQuery = em.createQuery(jpql,Estudante.class);
		Estudante e = typedQuery.getSingleResult();
		System.out.println(e);
	}
	
	private static void listarTodosOsEstudantes(EntityManager em ) {
		String jpql = "select e from Estudante e";
		TypedQuery<Estudante> typedQuery = em.createQuery(jpql,Estudante.class);
		List<Estudante> list = typedQuery.getResultList();
		imprimeLista(list);
	}
	
	private static <T> void imprimeLista(List<T> lista) {
		for (T valor : lista) {
			System.out.println(valor);
		}
	}
	
	
	private static void preparaDB(EntityManager em) {
		try {
			Curso c1 = new Curso("Mátematica",8);
			Curso c2 = new Curso("Computação",10);
			Curso c3 = new Curso("Geografia",8);
			Estudante e1 = new Estudante(c1,"Tõe","toe@gmail.com","11111");
			Estudante e2 = new Estudante(c1,"Lia","lia@gmail.com","222222");
			Estudante e3 = new Estudante(c1,"Tuca","tuca@gmail.com","33333");
			Estudante e4 = new Estudante(c1,"Peu","peu@gmail.com","444444");
			Estudante e5 = new Estudante(c1,"Leo","leo@gmail.com","55555");
			Estudante e6 = new Estudante(c1,"Va","val@gmail.com","66666");
			
			em.getTransaction().begin();
			em.persist(c1);
			em.persist(c2);
			em.persist(c3);
			em.persist(e1);
			em.persist(e2);
			em.persist(e3);
			em.persist(e4);
			em.persist(e5);
			em.persist(e6);
			em.getTransaction().commit();

			

		}catch(Exception e ) {
			e.getStackTrace();
		}

	}
	
//
//	private static void create() {
//		ESTUDANTE e1 = new ESTUDANTE("Jao","jvitorsb98@otlook.com","201711226");
//		ESTUDANTE e2 = new ESTUDANTE("Victor","vituxn@gmai.br","201411226");
//		ESTUDANTE e3 = new ESTUDANTE("Laio","rlaio@uesc.br","201711026");
//		
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit_academico");
//		EntityManager em = emf.createEntityManager();
//		em.getTransaction().begin();
//		em.persist(e1);
//		em.persist(e2);
//		em.persist(e3);
//		em.getTransaction().commit();
//		em.close();
//		emf.close();
//	}
//	
//	private static void read() {
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit_academico");
//        EntityManager em = emf.createEntityManager();
//        ESTUDANTE e1 = em.find(ESTUDANTE.class, 4);
//        System.out.println(e1);
//        em.close();
//        emf.close();
//	}
//	
//	
//	public static void delete() {
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit_academico");
//        EntityManager em = emf.createEntityManager();
//        ESTUDANTE e1 = em.find(ESTUDANTE.class, 5);
//        em.getTransaction().begin();
//        em.remove(e1);
//        em.getTransaction().commit();
//        em.close();
//        emf.close();
//	}
//	
//	
//	public static void update() {
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit_academico");
//        EntityManager em = emf.createEntityManager();
//        ESTUDANTE e1 = em.find(ESTUDANTE.class, 4);
//        e1.setMatricula("444444");
//        em.getTransaction().begin();
//        em.merge(e1);
//        em.getTransaction().commit();
//        em.close();
//        emf.close();	
//	}

}
