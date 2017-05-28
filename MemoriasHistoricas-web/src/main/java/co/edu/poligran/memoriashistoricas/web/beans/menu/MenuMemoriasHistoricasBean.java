package co.edu.poligran.memoriashistoricas.web.beans.menu;

import co.edu.poligran.memoriashistoricas.ejb.persitence.facade.MemoriasHistoricasFacadeLocal;
import co.edu.poligran.memoriashistoricas.web.beans.sesion.InicioSesionBean;
import co.edu.poligran.memoriashistoricas.web.util.ConstantesMemoriasHistoricas;
import co.edu.poligran.memoriashistoricas.web.util.JSFUtil;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * @Objetivo:
 * @Descripción:
 * @Fecha creación:
 * @author Cristian Peralta
 * @Versión: 1.0
 */
@ManagedBean(name = "menuMemoriasHistoricasBean")
@SessionScoped
public class MenuMemoriasHistoricasBean implements Serializable {

    @EJB
    private MemoriasHistoricasFacadeLocal memoriasHistoricasFacadeLocal;
    private List<String> rolesUsuario;
    private String usuarioCorreo;
    private static final long serialVersionUID = 1L;
    private FacesContext contex;
    private String url;

    public String irMenu(String caseMenu) {
        url = "loginExito";
        contex = FacesContext.getCurrentInstance();
        ConstantesMemoriasHistoricas.ITEM_MENU seleccionado
                = ConstantesMemoriasHistoricas.ITEM_MENU.valueOf(caseMenu);
        HttpSession httpSession
                = (HttpSession) contex.getExternalContext().getSession(false);
        InicioSesionBean inicioSesionBean
                = JSFUtil.getSessionBean(InicioSesionBean.class);
        usuarioCorreo = inicioSesionBean.getUsuarioCorreo();

        switch (seleccionado) {
            case INICIO:
                inicioSesionBean.setMenu(
                        ConstantesMemoriasHistoricas.ITEM_MENU.INICIO
                        .getDescription());
                return "loginExito";
            case RECORRIDO:
                inicioSesionBean.setMenu(
                        ConstantesMemoriasHistoricas.ITEM_MENU.RECORRIDO
                        .getDescription());
                return "mapa";
            case GESTION:
                inicioSesionBean.setMenu(
                        ConstantesMemoriasHistoricas.ITEM_MENU.GESTION
                        .getDescription());
                return "gestion";
        }
        return url;
    }

    public boolean isUserInRole(String rol) {
        if (rolesUsuario == null || rolesUsuario.isEmpty()) {
            rolesUsuario = memoriasHistoricasFacadeLocal.obtenerRolesUsuario(
                    usuarioCorreo);
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
}
