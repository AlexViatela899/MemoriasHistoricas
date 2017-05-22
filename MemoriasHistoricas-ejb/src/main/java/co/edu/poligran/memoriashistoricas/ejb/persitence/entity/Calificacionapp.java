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
@Table(name = "calificacionapp")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Calificacionapp.findAll", query = "SELECT c FROM Calificacionapp c"),
    @NamedQuery(name = "Calificacionapp.findByIdCalificacion", query = "SELECT c FROM Calificacionapp c WHERE c.idCalificacion = :idCalificacion"),
    @NamedQuery(name = "Calificacionapp.findByCalificacion", query = "SELECT c FROM Calificacionapp c WHERE c.calificacion = :calificacion"),
    @NamedQuery(name = "Calificacionapp.findByFechaValoracion", query = "SELECT c FROM Calificacionapp c WHERE c.fechaValoracion = :fechaValoracion")})
public class Calificacionapp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "id_calificacion")
    private String idCalificacion;
    @Size(max = 50)
    @Column(name = "calificacion")
    private String calificacion;
    @Column(name = "fecha_valoracion")
    @Temporal(TemporalType.DATE)
    private Date fechaValoracion;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario idUsuario;

    public Calificacionapp() {
    }

    public Calificacionapp(String idCalificacion) {
        this.idCalificacion = idCalificacion;
    }

    public String getIdCalificacion() {
        return idCalificacion;
    }

    public void setIdCalificacion(String idCalificacion) {
        this.idCalificacion = idCalificacion;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public Date getFechaValoracion() {
        return fechaValoracion;
    }

    public void setFechaValoracion(Date fechaValoracion) {
        this.fechaValoracion = fechaValoracion;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCalificacion != null ? idCalificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Calificacionapp)) {
            return false;
        }
        Calificacionapp other = (Calificacionapp) object;
        if ((this.idCalificacion == null && other.idCalificacion != null) || (this.idCalificacion != null && !this.idCalificacion.equals(other.idCalificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.poligran.memoriashistoricas.ejb.persitence.entity.Calificacionapp[ idCalificacion=" + idCalificacion + " ]";
    }

}
