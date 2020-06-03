package com.manager.controlers;

import com.manager.bean.PedidoBean;
import com.manager.book.Livro;
import com.manager.book.Pedido;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named (value = "PedidoControler")
@RequestScoped
public class PedidoControler {
    
    @EJB
    private PedidoBean pedidoBean;
    
    ELContext elContext = FacesContext.getCurrentInstance().getELContext();
    LivroControler lc = (LivroControler) elContext.getELResolver().getValue(elContext, null, "LivroControler");
    ELContext elContext1 = FacesContext.getCurrentInstance().getELContext();
    Login login = (Login) elContext1.getELResolver().getValue(elContext1, null, "Login");
    
    List<Pedido> pedidoList = new ArrayList<>(); // ALL REQUESTS
    List<Pedido> myPedidoList = new ArrayList<>();
    
    Pedido novoPedido = new Pedido();
    
    String novoLivroID = "1";
    String removePedidoID = "0";

    public String getRemovePedidoID() {
        return removePedidoID;
    }

    public void setRemovePedidoID(String removePedidoID) {
        this.removePedidoID = removePedidoID;
    }

    public void setNovoLivroID(String novoLivroID) {
        this.novoLivroID = novoLivroID;
    }

    public String getNovoLivroID() {
        return novoLivroID;
    }

    public Pedido getNovoPedido() {
        return novoPedido;
    }

    public void setNovoPedido(Pedido novoPedido) {
        this.novoPedido = novoPedido;
    }
    
    public String removePedido() {
        Integer pedidoID = -1;
        try {
            pedidoID = Integer.parseInt(removePedidoID);
        } catch (NumberFormatException e) {
            return "registered.xhtml";
        }
        pedidoBean.removePedido(getPedido(pedidoID));
        return "registered.xhtml";
    }
    
    public String addNewPedido() {
        
        Integer bookID = -1;
        try {
            bookID = Integer.parseInt(novoLivroID);
        } catch (NumberFormatException e) {
            
        }
        novoPedido.setIdLivro(lc.getLivro(bookID));
        
        novoPedido.setIdUtilizador(login.getMyUser());
        
        novoPedido.setDate((BigInteger.valueOf(java.lang.System.currentTimeMillis())));
        
        pedidoBean.addPedido(novoPedido);
        
        pedidoList = pedidoBean.getPedidos();
        return "registered.xhtml";
    }
    
    public List<Pedido> getPedidoList() {
        pedidoList = pedidoBean.getPedidos();
        return pedidoList;
    }

    public List<Pedido> getMyPedidoList() {
        pedidoList = pedidoBean.getPedidos();
        if (login.getMyUser() == null) {
            return new ArrayList<>();
        }
        myPedidoList = new ArrayList<>();
        for (Pedido p : pedidoList)
            if (p.getIdUtilizador().getIdUtilizador().intValue() == login.getMyUser().getIdUtilizador().intValue())
                myPedidoList.add(p);
        return myPedidoList;
    }

    public void setMyPedidoList(List<Pedido> myPedidoList) {
        this.myPedidoList = myPedidoList;
    }
    
    public Pedido getPedido(int id) {
        return pedidoBean.getPedido(id);
    }
    
    public String getDate(Pedido p) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(p.getDate().longValue());
        SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy 'at' h:mm a");
        return format.format(calendar.getTime());
    }
}
