/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.poligran.memoriashistoricas.web.beans.registro;

import co.edu.poligran.memoriashistoricas.ejb.persitence.entity.Usuario;
import co.edu.poligran.memoriashistoricas.ejb.persitence.facade.MemoriasHistoricasFacadeLocal;
import co.edu.poligran.memoriashistoricas.ejb.vo.ResultadoOperacion;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author peraltace
 */
public class RegistroUsuarioBean implements Serializable {

    @EJB
    private MemoriasHistoricasFacadeLocal memoriasHistoricasFacadeLocal;
    private Usuario usuario;

    public RegistroUsuarioBean() {
    }

    public String registrarUsuario() {
        ResultadoOperacion resultadoOperacion;

        resultadoOperacion
                = memoriasHistoricasFacadeLocal.registrarUsuario(this.usuario);
        if (resultadoOperacion.isResultadoTransaccion()) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            resultadoOperacion.getMensajeTransaccion(), ""));
            return "login";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            resultadoOperacion.getMensajeTransaccion(), ""));
            return null;
        }
    }

    public Usuario getUsuario() {
        if (usuario == null) {
            usuario = new Usuario();
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
