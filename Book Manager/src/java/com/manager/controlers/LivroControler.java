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

    public Livro getNovoLivro() {
        return novoLivro;
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
    
}
