package com.manager.controlers;

import com.manager.bean.LivroBean;
import com.manager.book.Livro;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named (value = "LivroControler")
@RequestScoped
public class LivroControler {

    @EJB
    private LivroBean livroBean;
    
    List<Livro> livroList = new ArrayList<>();
    
    Livro novoLivro = new Livro();
    
    String removeLivroID;
    String errorMessageRemove = "";

    public String getErrorMessageRemove() {
        return errorMessageRemove;
    }

    public void setErrorMessageRemove(String errorMessageRemove) {
        this.errorMessageRemove = errorMessageRemove;
    }

    public String getRemoveLivroID() {
        return removeLivroID;
    }

    public void setRemoveLivroID(String removeLivroID) {
        this.removeLivroID = removeLivroID;
    }

    public Livro getNovoLivro() {
        return novoLivro;
    }
    
    public String removeLivro() {
        Livro toRemove = null;
        try {
            int id = Integer.parseInt(removeLivroID);
            toRemove = livroBean.getLivro(id);
        } catch (NumberFormatException e) {
            setErrorMessageRemove("O ID do livro deve ser um inteiro.");
            return "listLivros.xhtml";
        } catch (Exception e) {
            setErrorMessageRemove("Livro não encontrado.");
            return "listLivros.xhtml";
        }
        if (toRemove == null) {
            setErrorMessageRemove("Livro não encontrado.");
            return "listLivros.xhtml";
        }
        livroBean.removeLivro(toRemove);
        return "listLivros.xhtml";
    }

    public void setNovoLivro(Livro novoLivro) {
        this.novoLivro = novoLivro;
    }
    
    public String addNewLivro() {
        livroBean.addLivro(novoLivro);
        
        livroList = livroBean.getLivros();
        return "listLivros.xhtml";
    }
    
    public List<Livro> getLivroList() {
        livroList = livroBean.getLivros();
        return livroList;
    }
    
    public Livro getLivro(int id) {
        return livroBean.getLivro(id);
    }
    
}
