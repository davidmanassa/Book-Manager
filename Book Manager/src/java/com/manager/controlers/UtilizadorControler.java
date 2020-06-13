package com.manager.controlers;

import com.manager.bean.UtilizadorBean;
import com.manager.book.Utilizador;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named (value = "UtilizadorControler")
@SessionScoped
public class UtilizadorControler implements Serializable {
    
    @EJB
    private UtilizadorBean utilizadorBean;
    
    String failMessage = "";
    
    List<Utilizador> utilizadorList = new ArrayList<>();
    
    Utilizador novoUtilizador = new Utilizador();
    
    Utilizador myUser = null;
    
    String removeUtilizadorID = "0";
    
    String username = "admin";
    String password = "admin";
    
    String removeUtilizadorError = "";
    String addUtilizadorError = "";
    
    public void canAccess() throws IOException {
        if (myUser == null)
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }

    public String getAddUtilizadorError() {
        return addUtilizadorError;
    }

    public void setAddUtilizadorError(String addUtilizadorError) {
        this.addUtilizadorError = addUtilizadorError;
    }

    public String getFailMessage() {
        return failMessage;
    }

    public void setFailMessage(String failMessage) {
        this.failMessage = failMessage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRemoveUtilizadorError() {
        return removeUtilizadorError;
    }

    public void setRemoveUtilizadorError(String removeUtilizadorError) {
        this.removeUtilizadorError = removeUtilizadorError;
    }

    public String getRemoveUtilizadorID() {
        return removeUtilizadorID;
    }

    public void setRemoveUtilizadorID(String removeUtilizadorID) {
        this.removeUtilizadorID = removeUtilizadorID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String removeUtilizador() {
        Utilizador toRemove = null;
        try {
            int id = Integer.parseInt(removeUtilizadorID);
            toRemove = utilizadorBean.getUtilizador(id);
        } catch (NumberFormatException e) {
            setRemoveUtilizadorError("O ID do utilizador deve ser um inteiro.");
            return "listUtilizadores.xhtml";
        } catch (Exception e) {
            setRemoveUtilizadorError("Utilizador não encontrado.");
            return "listUtilizadores.xhtml";
        }
        if (toRemove == null) {
            setRemoveUtilizadorError("Utilizador não encontrado.");
            return "listUtilizadores.xhtml";
        }
        utilizadorBean.removeUtilizador(toRemove);
        return "listUtilizadores.xhtml";
    }
    public Utilizador getNovoUtilizador() {
        return novoUtilizador;
    }

    public void setNovoUtilizador(Utilizador novoUtilizador) {
        this.novoUtilizador = novoUtilizador;
    }
    
    public String addNewUtilizador(int page) {
        
        try {
            
            for (Utilizador u : utilizadorList) {
                if (u.getUsername().equalsIgnoreCase(username)) {
                    setAddUtilizadorError("Já existe um utilizador com esse username.");
                    if (page == 1)
                        return "listUtilizadores.xhtml";
                    else
                        return "index.xhtml";
                }
            }
            
            String encrypted = Password.getSaltedHash(password);
            
            novoUtilizador.setUsername(username);
            novoUtilizador.setPassword(encrypted);
            
            utilizadorBean.addUtilizador(novoUtilizador);
        
            utilizadorList = utilizadorBean.getUtilizadores();
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (page == 1)
            return "listUtilizadores.xhtml";
        else
            return "index.xhtml";
    }
    
    public Utilizador getUtilizador(int id) {
        return utilizadorBean.getUtilizador(id);
    }
    
    public Utilizador getUtilizador(String username) {
        for (Utilizador u : getUtilizadorList())
            if (u.getUsername().equals(username))
                return u;
        return null;
    }
    
    public List<Utilizador> getUtilizadorList() {
        utilizadorList = utilizadorBean.getUtilizadores();
        return utilizadorList;
    }
    
    public String Login() {
        if (getUtilizador("admin") == null) { // Sem admin -> cria admin
            try {
                myUser = new Utilizador(1, "admin");
                myUser.setPassword(Password.getSaltedHash("admin"));
                setNovoUtilizador(myUser);
                addNewUtilizador(1);
            } catch (Exception e) {
                
            }
        }
        
        //login action
        Utilizador user = getUtilizador(username);
        if (user == null) {
            setFailMessage("Username inexistente.");
            return "index.xhtml";
        }
        try {
            boolean success = Password.check(password, user.getPassword());
            if (success) {
                myUser = user;
                return "registered.xhtml";
            } else {
               setFailMessage("Password ou username errado(s).");
            }
        } catch (Exception e) {
            setFailMessage("Erro no sistema de Login.");
        }
        return "index.xhtml";
    }

    public Utilizador getMyUser() {
        return myUser;
    }

    public void setMyUser(Utilizador myUser) {
        this.myUser = myUser;
    }
    
}
