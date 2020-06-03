package com.manager.bean;

import com.manager.book.Pedido;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PedidoBean {

    @PersistenceContext(unitName = "Book_ManagerPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    public List<Pedido> getPedidos() {
        return (List<Pedido>) em.createNamedQuery("Pedido.findAll").getResultList();
    }
    
    public Pedido getPedido(int id) {
        return em.find(Pedido.class, id);
    }
    
    public Pedido addPedido(Pedido pedido) {
        em.persist(pedido);
        return pedido;
    }
    
    public void removePedido(Pedido pedido) {
        if (!em.contains(pedido)) {
            pedido = em.merge(pedido);
}
        em.remove(pedido);
    }
    
}
