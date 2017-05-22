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
@Table(name = "evaluacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evaluacion.findAll", query = "SELECT e FROM Evaluacion e"),
    @NamedQuery(name = "Evaluacion.findByIdEvalucion", query = "SELECT e FROM Evaluacion e WHERE e.idEvalucion = :idEvalucion"),
    @NamedQuery(name = "Evaluacion.findByResultado", query = "SELECT e FROM Evaluacion e WHERE e.resultado = :resultado")})
public class Evaluacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "id_evalucion")
    private String idEvalucion;
    @Size(max = 50)
    @Column(name = "resultado")
    private String resultado;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario idUsuario;
    @JoinColumn(name = "id_departamento", referencedColumnName = "id_departamento")
    @ManyToOne(fetch = FetchType.LAZY)
    private Departamento idDepartamento;

    public Evaluacion() {
    }

    public Evaluacion(String idEvalucion) {
        this.idEvalucion = idEvalucion;
    }

    public String getIdEvalucion() {
        return idEvalucion;
    }

    public void setIdEvalucion(String idEvalucion) {
        this.idEvalucion = idEvalucion;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
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
        hash += (idEvalucion != null ? idEvalucion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evaluacion)) {
            return false;
        }
        Evaluacion other = (Evaluacion) object;
        if ((this.idEvalucion == null && other.idEvalucion != null) || (this.idEvalucion != null && !this.idEvalucion.equals(other.idEvalucion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.poligran.memoriashistoricas.ejb.persitence.entity.Evaluacion[ idEvalucion=" + idEvalucion + " ]";
    }

}
