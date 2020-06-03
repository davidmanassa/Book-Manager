package com.manager.bean;

import com.manager.book.Utilizador;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UtilizadorBean {

    @PersistenceContext(unitName = "Book_ManagerPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    public List<Utilizador> getUtilizadores() {
        return (List<Utilizador>) em.createNamedQuery("Utilizador.findAll").getResultList();
    }
    
    public Utilizador addUtilizador(Utilizador utilizador) {
        em.persist(utilizador);
        return utilizador;
    }
    
    public Utilizador getUtilizador(int id) {
        return em.find(Utilizador.class, id);
    }
    
    public void removeUtilizador(Utilizador utilizador) {
        if (!em.contains(utilizador)) {
            utilizador = em.merge(utilizador);
        }
            
        em.remove(utilizador);
    }
    
}
