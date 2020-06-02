/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.manager.book;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author manas
 */
@Entity
@Table(name = "LIVRO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Livro.findAll", query = "SELECT l FROM Livro l")
    , @NamedQuery(name = "Livro.findByIdLivro", query = "SELECT l FROM Livro l WHERE l.idLivro = :idLivro")
    , @NamedQuery(name = "Livro.findByNomeLivro", query = "SELECT l FROM Livro l WHERE l.nomeLivro = :nomeLivro")
    , @NamedQuery(name = "Livro.findByAutorLivro", query = "SELECT l FROM Livro l WHERE l.autorLivro = :autorLivro")
    , @NamedQuery(name = "Livro.findByEdicaoLivro", query = "SELECT l FROM Livro l WHERE l.edicaoLivro = :edicaoLivro")})
public class Livro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_LIVRO")
    private Integer idLivro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "NOME_LIVRO")
    private String nomeLivro;
    @Size(max = 255)
    @Column(name = "AUTOR_LIVRO")
    private String autorLivro;
    @Column(name = "EDICAO_LIVRO")
    private Integer edicaoLivro;
    @OneToMany(mappedBy = "idLivro")
    private List<Pedido> pedidoList;

    public Livro() {
    }

    public Livro(Integer idLivro) {
        this.idLivro = idLivro;
    }

    public Livro(Integer idLivro, String nomeLivro) {
        this.idLivro = idLivro;
        this.nomeLivro = nomeLivro;
    }

    public Integer getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(Integer idLivro) {
        this.idLivro = idLivro;
    }

    public String getNomeLivro() {
        return nomeLivro;
    }

    public void setNomeLivro(String nomeLivro) {
        this.nomeLivro = nomeLivro;
    }

    public String getAutorLivro() {
        return autorLivro;
    }

    public void setAutorLivro(String autorLivro) {
        this.autorLivro = autorLivro;
    }

    public Integer getEdicaoLivro() {
        return edicaoLivro;
    }

    public void setEdicaoLivro(Integer edicaoLivro) {
        this.edicaoLivro = edicaoLivro;
    }

    @XmlTransient
    public List<Pedido> getPedidoList() {
        return pedidoList;
    }

    public void setPedidoList(List<Pedido> pedidoList) {
        this.pedidoList = pedidoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLivro != null ? idLivro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Livro)) {
            return false;
        }
        Livro other = (Livro) object;
        if ((this.idLivro == null && other.idLivro != null) || (this.idLivro != null && !this.idLivro.equals(other.idLivro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.manager.book.Livro[ idLivro=" + idLivro + " ]";
    }
    
}
