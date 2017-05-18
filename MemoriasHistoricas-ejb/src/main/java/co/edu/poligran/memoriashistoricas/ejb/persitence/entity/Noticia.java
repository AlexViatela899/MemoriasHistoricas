/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.poligran.memoriashistoricas.ejb.persitence.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Objetivo:
 * @Descripción:
 * @Fecha creación:
 * @author Cristian Peralta
 * @Versión: 1.0
 */
@Entity
@Table(name = "noticia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Noticia.findAll", query = "SELECT n FROM Noticia n"),
    @NamedQuery(name = "Noticia.findByIdNoticia", query = "SELECT n FROM Noticia n WHERE n.idNoticia = :idNoticia"),
    @NamedQuery(name = "Noticia.findByTituloNoticia", query = "SELECT n FROM Noticia n WHERE n.tituloNoticia = :tituloNoticia"),
    @NamedQuery(name = "Noticia.findByContenido", query = "SELECT n FROM Noticia n WHERE n.contenido = :contenido"),
    @NamedQuery(name = "Noticia.findByAutor", query = "SELECT n FROM Noticia n WHERE n.autor = :autor"),
    @NamedQuery(name = "Noticia.findByFechaNoticia", query = "SELECT n FROM Noticia n WHERE n.fechaNoticia = :fechaNoticia")})
public class Noticia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "id_noticia")
    private String idNoticia;
    @Size(max = 50)
    @Column(name = "titulo_noticia")
    private String tituloNoticia;
    @Size(max = 50)
    @Column(name = "contenido")
    private String contenido;
    @Size(max = 50)
    @Column(name = "autor")
    private String autor;
    @Column(name = "fecha_noticia")
    @Temporal(TemporalType.DATE)
    private Date fechaNoticia;
    @JoinColumn(name = "id_ptoestudio", referencedColumnName = "id_ptoestudio")
    @ManyToOne(fetch = FetchType.LAZY)
    private Puntoestudio idPtoestudio;

    public Noticia() {
    }

    public Noticia(String idNoticia) {
        this.idNoticia = idNoticia;
    }

    public String getIdNoticia() {
        return idNoticia;
    }

    public void setIdNoticia(String idNoticia) {
        this.idNoticia = idNoticia;
    }

    public String getTituloNoticia() {
        return tituloNoticia;
    }

    public void setTituloNoticia(String tituloNoticia) {
        this.tituloNoticia = tituloNoticia;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getFechaNoticia() {
        return fechaNoticia;
    }

    public void setFechaNoticia(Date fechaNoticia) {
        this.fechaNoticia = fechaNoticia;
    }

    public Puntoestudio getIdPtoestudio() {
        return idPtoestudio;
    }

    public void setIdPtoestudio(Puntoestudio idPtoestudio) {
        this.idPtoestudio = idPtoestudio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNoticia != null ? idNoticia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Noticia)) {
            return false;
        }
        Noticia other = (Noticia) object;
        if ((this.idNoticia == null && other.idNoticia != null) || (this.idNoticia != null && !this.idNoticia.equals(other.idNoticia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.poligran.memoriashistoricas.ejb.persitence.entity.Noticia[ idNoticia=" + idNoticia + " ]";
    }

}
