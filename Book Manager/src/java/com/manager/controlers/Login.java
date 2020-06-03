package com.manager.controlers;

import com.manager.book.Utilizador;
import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named (value = "Login")
@RequestScoped
public class Login {

    Utilizador myUser = null;
    String failMessage = "";
    
    ELContext elContext = FacesContext.getCurrentInstance().getELContext();
    UtilizadorControler uc = (UtilizadorControler) elContext.getELResolver().getValue(elContext, null, "UtilizadorControler");

    public String getFailMessage() {
        return failMessage;
    }
    
    public String Login() {
        //login action
        
        boolean success = true;
        if (success) {
            return "registered.xhtml";
        } else {
            failMessage = "Password ou username errado(s).";
        }
        return "index.xhtml";
    }

    public Utilizador getMyUser() {
        if (myUser == null) {
            myUser = uc.getUtilizador(1);
        }
        if (myUser == null) {
            myUser = new Utilizador(1, "admin");
            uc.setNovoUtilizador(myUser);
            uc.addNewUtilizador();
            myUser = uc.getUtilizador(1);
        }
        return myUser;
    }

    public void setMyUser(Utilizador myUser) {
        this.myUser = myUser;
    }
    
}
