package com.manager.bean;

import com.manager.book.Livro;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * 
 * Comunicação com a base de dados
 * Persistência de dados
 * 
 */

@Stateless
public class LivroBean {

    @PersistenceContext(unitName = "Book_ManagerPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    public List<Livro> getLivros() {
        return (List<Livro>) em.createNamedQuery("Livro.findAll").getResultList();
    }
    
    public Livro addLivro(Livro livro) {
        em.persist(livro);
        return livro;
    }
    
    public Livro getLivro(int id) {
        return em.find(Livro.class, id);
    }
    
    public void removeLivro(Livro livro) {
        if (!em.contains(livro)) {
            livro = em.merge(livro);
        }
            
        em.remove(livro);
    }
}
