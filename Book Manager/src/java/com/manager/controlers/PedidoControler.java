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
    UtilizadorControler uc = (UtilizadorControler) elContext1.getELResolver().getValue(elContext1, null, "UtilizadorControler");
    
    List<Pedido> pedidoList = new ArrayList<>(); // ALL REQUESTS
    List<Pedido> myPedidoList = new ArrayList<>();
    
    Pedido novoPedido = new Pedido();
   
    String removePedidoID = "0";
    
    String pedidoErrorMessage = "";

    public String getPedidoErrorMessage() {
        return pedidoErrorMessage;
    }

    public void setPedidoErrorMessage(String pedidoErrorMessage) {
        this.pedidoErrorMessage = pedidoErrorMessage;
    }

    public String getRemovePedidoID() {
        return removePedidoID;
    }

    public void setRemovePedidoID(String removePedidoID) {
        this.removePedidoID = removePedidoID;
    }

    public Pedido getNovoPedido() {
        return novoPedido;
    }
    
    public String getEstado(Pedido pedido) {
        int counter = 0;
        for (Pedido p : pedidoList) {
            if (p.getId().intValue() == pedido.getId().intValue() && counter == 0)
                return "Requisitado";
            if (p.getBookID().getId().intValue() == pedido.getBookID().getId().intValue()) {
                if (p.getUserID().getId().intValue() == pedido.getUserID().getId().intValue())
                    return "Reservado (" + counter + "º na fila)";
                else
                    counter += 1;
            }
        }
        return "Pedido não encontrado";
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
    
    public String removePedido(Pedido pedido) {
        pedidoBean.removePedido(pedido);
        return "registered.xhtml";
    }
    
    public String addNewPedido(Livro livro) {
        
        novoPedido.setBookID(livro);
        
        novoPedido.setUserID(uc.getMyUser());
        
        novoPedido.setDate((BigInteger.valueOf(java.lang.System.currentTimeMillis())));
        
        for (Pedido p : pedidoList) {
            if (p.getUserID().getId().intValue() == novoPedido.getUserID().getId().intValue())
                if (p.getBookID().getId().intValue() == livro.getId().intValue()) {
                    setPedidoErrorMessage("Já fizeste um pedido a este objeto.");
                    return "registered.xhtml";    
                }
        }
        
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
        if (uc.getMyUser() == null) {
            return new ArrayList<>();
        }
        if (getPedidoList() == null)
            return new ArrayList<>();
        myPedidoList = new ArrayList<>();
        for (Pedido p : getPedidoList())
            if (p.getUserID().getId().intValue() == uc.getMyUser().getId().intValue())
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
