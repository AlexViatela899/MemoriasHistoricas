/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.poligran.memoriashistoricas.ejb.persitence.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * @Objetivo:
 * @Descripción:
 * @Fecha creación:
 * @author Cristian Peralta
 * @Versión: 1.0
 */
@Entity
@Table(name = "tiporeporte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tiporeporte.findAll", query = "SELECT t FROM Tiporeporte t"),
    @NamedQuery(name = "Tiporeporte.findByIdTiporep", query = "SELECT t FROM Tiporeporte t WHERE t.idTiporep = :idTiporep"),
    @NamedQuery(name = "Tiporeporte.findByNombreTiporep", query = "SELECT t FROM Tiporeporte t WHERE t.nombreTiporep = :nombreTiporep"),
    @NamedQuery(name = "Tiporeporte.findByDescripcionTiporep", query = "SELECT t FROM Tiporeporte t WHERE t.descripcionTiporep = :descripcionTiporep")})
public class Tiporeporte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "id_tiporep")
    private String idTiporep;
    @Size(max = 50)
    @Column(name = "nombre_tiporep")
    private String nombreTiporep;
    @Size(max = 50)
    @Column(name = "descripcion_tiporep")
    private String descripcionTiporep;
    @OneToMany(mappedBy = "idTiporep", fetch = FetchType.LAZY)
    private List<Reportes> reportesList;

    public Tiporeporte() {
    }

    public Tiporeporte(String idTiporep) {
        this.idTiporep = idTiporep;
    }

    public String getIdTiporep() {
        return idTiporep;
    }

    public void setIdTiporep(String idTiporep) {
        this.idTiporep = idTiporep;
    }

    public String getNombreTiporep() {
        return nombreTiporep;
    }

    public void setNombreTiporep(String nombreTiporep) {
        this.nombreTiporep = nombreTiporep;
    }

    public String getDescripcionTiporep() {
        return descripcionTiporep;
    }

    public void setDescripcionTiporep(String descripcionTiporep) {
        this.descripcionTiporep = descripcionTiporep;
    }

    @XmlTransient
    public List<Reportes> getReportesList() {
        return reportesList;
    }

    public void setReportesList(List<Reportes> reportesList) {
        this.reportesList = reportesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTiporep != null ? idTiporep.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tiporeporte)) {
            return false;
        }
        Tiporeporte other = (Tiporeporte) object;
        if ((this.idTiporep == null && other.idTiporep != null) || (this.idTiporep != null && !this.idTiporep.equals(other.idTiporep))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.poligran.memoriashistoricas.ejb.persitence.entity.Tiporeporte[ idTiporep=" + idTiporep + " ]";
    }

}
