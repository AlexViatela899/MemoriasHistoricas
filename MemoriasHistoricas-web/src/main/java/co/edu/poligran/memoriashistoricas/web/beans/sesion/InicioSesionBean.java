/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.poligran.memoriashistoricas.web.beans.sesion;

import co.edu.poligran.memoriashistoricas.ejb.exception.MemoriasHistoricasException;
import co.edu.poligran.memoriashistoricas.ejb.persitence.entity.Usuario;
import co.edu.poligran.memoriashistoricas.ejb.persitence.facade.MemoriasHistoricasFacadeLocal;
import co.edu.poligran.memoriashistoricas.ejb.vo.ResultadoOperacion;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * @Objetivo:
 * @Descripción:
 * @Fecha creación:
 * @author Cristian Peralta
 * @Versión: 1.0
 */
@ManagedBean(name = "inicioSesionBean")
@SessionScoped
public class InicioSesionBean implements Serializable {

    @EJB
    private MemoriasHistoricasFacadeLocal memoriasHistoricasFacadeLocal;
    private boolean autenticado;
    private List<String> rolesUsuario;
    private String usuarioCorreo;
    private String clave;
    private String menu;
    private Usuario usuario;
    
    public static HttpServletRequest getRequest() {
        Object request = FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();

        return request instanceof HttpServletRequest
                ? (HttpServletRequest) request
                : null;
    }
    
    public InicioSesionBean() {
        
    }

    @PostConstruct
    public void init() {
        
    }
    
    public String autenticar() throws IOException {
        String login = "loginFalla";
        if (!autenticado) {
            ResultadoOperacion resultado = new ResultadoOperacion();
            usuario = new Usuario();
            if ((usuarioCorreo != null && !usuarioCorreo.isEmpty())
                    && (clave != null && !clave.isEmpty())) {
                try {
                    usuario = memoriasHistoricasFacadeLocal.autenticar(
                            usuarioCorreo, clave);
                    if (usuario == null) {
                        resultado.setResultadoTransaccion(false);
                        resultado.setMensajeTransaccion(
                                "Usuario o contraseña incorrectos.");
                    } else if (usuario.getIdRol() == null) {
                        resultado.setResultadoTransaccion(false);
                        resultado.setMensajeTransaccion(
                                "Usuario sin rol.");
                    } else {
                        getRolesUsuario().add(
                                usuario.getIdRol().getNombreRol());
                        menu = "INICIO";
                        resultado.setResultadoTransaccion(true);
                        resultado.setMensajeTransaccion(
                                "loginExito");
                    }
                    login = resultado.getMensajeTransaccion();
                } catch (Exception ex) {
                    login = "loginFalla";
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    ex.getMessage(), ""));
                    autenticado = false;
                    return login;
                }

            } else {
                login = "loginFalla";
                return login;
            }
        }
        return login;
    }
    
    public String cerrarSesion() throws MemoriasHistoricasException {
        FacesContext.getCurrentInstance()
                .getExternalContext().invalidateSession();
        return "login";
    }

    public boolean isUserInRole(String rol) {
        if (rolesUsuario == null || rolesUsuario.isEmpty()) {
            return false;
        }
        try {
            List<String> grupos = rolesUsuario;
            if (grupos.contains(rol)) {
                return true;
            }
        } catch (Exception e) {

            return false;
        }
        return false;
    }
    
    public boolean isAutenticado() {
        return autenticado;
    }

    public void setAutenticado(boolean autenticado) {
        this.autenticado = autenticado;
    }

    public List<String> getRolesUsuario() {
        if (rolesUsuario == null) {
            rolesUsuario = new LinkedList<>();
        }
        return rolesUsuario;
    }

    public void setRolesUsuario(List<String> rolesUsuario) {
        this.rolesUsuario = rolesUsuario;
    }

    public String getUsuarioCorreo() {
        return usuarioCorreo;
    }

    public void setUsuarioCorreo(String usuarioCorreo) {
        this.usuarioCorreo = usuarioCorreo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
