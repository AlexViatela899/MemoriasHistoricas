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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "puntoestudio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Puntoestudio.findAll", query = "SELECT p FROM Puntoestudio p"),
    @NamedQuery(name = "Puntoestudio.findByIdPtoestudio", query = "SELECT p FROM Puntoestudio p WHERE p.idPtoestudio = :idPtoestudio"),
    @NamedQuery(name = "Puntoestudio.findByNombrePtoestudio", query = "SELECT p FROM Puntoestudio p WHERE p.nombrePtoestudio = :nombrePtoestudio")})
public class Puntoestudio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "id_ptoestudio")
    private String idPtoestudio;
    @Size(max = 50)
    @Column(name = "nombre_ptoestudio")
    private String nombrePtoestudio;
    @OneToMany(mappedBy = "idPtoestudio", fetch = FetchType.LAZY)
    private List<Noticia> noticiaList;
    @OneToMany(mappedBy = "idPtoestudio", fetch = FetchType.LAZY)
    private List<Materialgrafico> materialgraficoList;
    @JoinColumn(name = "id_departamento", referencedColumnName = "id_departamento")
    @ManyToOne(fetch = FetchType.LAZY)
    private Departamento idDepartamento;

    public Puntoestudio() {
    }

    public Puntoestudio(String idPtoestudio) {
        this.idPtoestudio = idPtoestudio;
    }

    public String getIdPtoestudio() {
        return idPtoestudio;
    }

    public void setIdPtoestudio(String idPtoestudio) {
        this.idPtoestudio = idPtoestudio;
    }

    public String getNombrePtoestudio() {
        return nombrePtoestudio;
    }

    public void setNombrePtoestudio(String nombrePtoestudio) {
        this.nombrePtoestudio = nombrePtoestudio;
    }

    @XmlTransient
    public List<Noticia> getNoticiaList() {
        return noticiaList;
    }

    public void setNoticiaList(List<Noticia> noticiaList) {
        this.noticiaList = noticiaList;
    }

    @XmlTransient
    public List<Materialgrafico> getMaterialgraficoList() {
        return materialgraficoList;
    }

    public void setMaterialgraficoList(List<Materialgrafico> materialgraficoList) {
        this.materialgraficoList = materialgraficoList;
    }

    public Departamento getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Departamento idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPtoestudio != null ? idPtoestudio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Puntoestudio)) {
            return false;
        }
        Puntoestudio other = (Puntoestudio) object;
        if ((this.idPtoestudio == null && other.idPtoestudio != null) || (this.idPtoestudio != null && !this.idPtoestudio.equals(other.idPtoestudio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.poligran.memoriashistoricas.ejb.persitence.entity.Puntoestudio[ idPtoestudio=" + idPtoestudio + " ]";
    }

}
