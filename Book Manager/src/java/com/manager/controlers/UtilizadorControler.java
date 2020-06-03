package com.manager.controlers;

import com.manager.bean.UtilizadorBean;
import com.manager.book.Utilizador;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named (value = "UtilizadorControler")
@RequestScoped
public class UtilizadorControler {
    
    @EJB
    private UtilizadorBean utilizadorBean;
    
    String failMessage = "Password ou username errado(s).";
    
    List<Utilizador> utilizadorList = new ArrayList<>();
    
    Utilizador novoUtilizador = new Utilizador();
    
    public Utilizador getNovoUtilizador() {
        return novoUtilizador;
    }

    public void setNovoUtilizador(Utilizador novoUtilizador) {
        this.novoUtilizador = novoUtilizador;
    }
    
    public String addNewUtilizador() {
        utilizadorBean.addUtilizador(novoUtilizador);
        
        utilizadorList = utilizadorBean.getUtilizadores();
        return "registered.xhtml";
    }
    
    public Utilizador getUtilizador(int id) {
        return utilizadorBean.getUtilizador(id);
    }
    
    public List<Utilizador> getUtilizadorList() {
        utilizadorList = utilizadorBean.getUtilizadores();
        return utilizadorList;
    }
    
}
