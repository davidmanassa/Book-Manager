/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.manager.bean;

import com.manager.book.Livro;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author manas
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
}
