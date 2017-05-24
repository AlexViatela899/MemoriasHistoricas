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
@Table(name = "reporte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reporte.findAll", query = "SELECT r FROM Reporte r"),
    @NamedQuery(name = "Reporte.findByIdReporte", query = "SELECT r FROM Reporte r WHERE r.idReporte = :idReporte"),
    @NamedQuery(name = "Reporte.findByNombreReporte", query = "SELECT r FROM Reporte r WHERE r.nombreReporte = :nombreReporte"),
    @NamedQuery(name = "Reporte.findByDescripcionReporte", query = "SELECT r FROM Reporte r WHERE r.descripcionReporte = :descripcionReporte"),
    @NamedQuery(name = "Reporte.findByReporte", query = "SELECT r FROM Reporte r WHERE r.reporte = :reporte"),
    @NamedQuery(name = "Reporte.findByFechaReporte", query = "SELECT r FROM Reporte r WHERE r.fechaReporte = :fechaReporte")})
public class Reporte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "id_reporte")
    private String idReporte;
    @Size(max = 50)
    @Column(name = "nombre_reporte")
    private String nombreReporte;
    @Size(max = 50)
    @Column(name = "descripcion_reporte")
    private String descripcionReporte;
    @Size(max = 50)
    @Column(name = "reporte")
    private String reporte;
    @Column(name = "fecha_reporte")
    @Temporal(TemporalType.DATE)
    private Date fechaReporte;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario idUsuario;
    @JoinColumn(name = "id_tiporep", referencedColumnName = "id_tiporep")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tiporeporte idTiporep;

    public Reporte() {
    }

    public Reporte(String idReporte) {
        this.idReporte = idReporte;
    }

    public String getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(String idReporte) {
        this.idReporte = idReporte;
    }

    public String getNombreReporte() {
        return nombreReporte;
    }

    public void setNombreReporte(String nombreReporte) {
        this.nombreReporte = nombreReporte;
    }

    public String getDescripcionReporte() {
        return descripcionReporte;
    }

    public void setDescripcionReporte(String descripcionReporte) {
        this.descripcionReporte = descripcionReporte;
    }

    public String getReporte() {
        return reporte;
    }

    public void setReporte(String reporte) {
        this.reporte = reporte;
    }

    public Date getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(Date fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Tiporeporte getIdTiporep() {
        return idTiporep;
    }

    public void setIdTiporep(Tiporeporte idTiporep) {
        this.idTiporep = idTiporep;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReporte != null ? idReporte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reporte)) {
            return false;
        }
        Reporte other = (Reporte) object;
        if ((this.idReporte == null && other.idReporte != null) || (this.idReporte != null && !this.idReporte.equals(other.idReporte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.poligran.memoriashistoricas.ejb.persitence.entity.Reportes[ idReporte=" + idReporte + " ]";
    }

}
