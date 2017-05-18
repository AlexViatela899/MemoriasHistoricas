/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.poligran.memoriashistoricas.ejb.persitence.entity;

import java.io.Serializable;
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
@Table(name = "materialgrafico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Materialgrafico.findAll", query = "SELECT m FROM Materialgrafico m"),
    @NamedQuery(name = "Materialgrafico.findByIdMaterial", query = "SELECT m FROM Materialgrafico m WHERE m.idMaterial = :idMaterial"),
    @NamedQuery(name = "Materialgrafico.findByNombreMaterial", query = "SELECT m FROM Materialgrafico m WHERE m.nombreMaterial = :nombreMaterial"),
    @NamedQuery(name = "Materialgrafico.findByDescripcionMaterial", query = "SELECT m FROM Materialgrafico m WHERE m.descripcionMaterial = :descripcionMaterial"),
    @NamedQuery(name = "Materialgrafico.findByMaterial", query = "SELECT m FROM Materialgrafico m WHERE m.material = :material")})
public class Materialgrafico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "id_material")
    private String idMaterial;
    @Size(max = 50)
    @Column(name = "nombre_material")
    private String nombreMaterial;
    @Size(max = 50)
    @Column(name = "descripcion_material")
    private String descripcionMaterial;
    @Size(max = 100)
    @Column(name = "material")
    private String material;
    @JoinColumn(name = "id_ptoestudio", referencedColumnName = "id_ptoestudio")
    @ManyToOne(fetch = FetchType.LAZY)
    private Puntoestudio idPtoestudio;

    public Materialgrafico() {
    }

    public Materialgrafico(String idMaterial) {
        this.idMaterial = idMaterial;
    }

    public String getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(String idMaterial) {
        this.idMaterial = idMaterial;
    }

    public String getNombreMaterial() {
        return nombreMaterial;
    }

    public void setNombreMaterial(String nombreMaterial) {
        this.nombreMaterial = nombreMaterial;
    }

    public String getDescripcionMaterial() {
        return descripcionMaterial;
    }

    public void setDescripcionMaterial(String descripcionMaterial) {
        this.descripcionMaterial = descripcionMaterial;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
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
        hash += (idMaterial != null ? idMaterial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Materialgrafico)) {
            return false;
        }
        Materialgrafico other = (Materialgrafico) object;
        if ((this.idMaterial == null && other.idMaterial != null) || (this.idMaterial != null && !this.idMaterial.equals(other.idMaterial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.poligran.memoriashistoricas.ejb.persitence.entity.Materialgrafico[ idMaterial=" + idMaterial + " ]";
    }

}
