package co.edu.poligran.memoriashistoricas.web.beans.menu;

import co.edu.poligran.memoriashistoricas.ejb.persitence.facade.MemoriasHistoricasFacadeLocal;
import co.edu.poligran.memoriashistoricas.web.beans.sesion.LoginBean;
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
public class MenuMemoriasHistoricasBean implements Serializable {

    @EJB
    private MemoriasHistoricasFacadeLocal memoriasHistoricasFacadeLocal;
    private List<String> rolesUsuario;
    private String usuarioCorreo;
    private static final long serialVersionUID = 1L;
    private FacesContext contex;
    private String url;

    public MenuMemoriasHistoricasBean() {
    }

    public String irMenu(String caseMenu) {
        url = "loginExito";
        contex = FacesContext.getCurrentInstance();
        ConstantesMemoriasHistoricas.ITEM_MENU seleccionado
                = ConstantesMemoriasHistoricas.ITEM_MENU.valueOf(caseMenu);
        HttpSession httpSession
                = (HttpSession) contex.getExternalContext().getSession(false);
        LoginBean loginBean
                = (LoginBean) JSFUtil.getBean("loginBean");
        usuarioCorreo = loginBean.getUsuarioCorreo();

        switch (seleccionado) {
            case INICIO:
                loginBean.setMenu(
                        ConstantesMemoriasHistoricas.ITEM_MENU.INICIO
                                .getDescription());
                return "loginExito";
            case RECORRIDO:
                loginBean.setMenu(
                        ConstantesMemoriasHistoricas.ITEM_MENU.RECORRIDO
                                .getDescription());
                return "mapa";
            case GESTION:
                loginBean.setMenu(
                        ConstantesMemoriasHistoricas.ITEM_MENU.GESTION
                                .getDescription());
                return "gestion";
        }
        return url;
    }

    public boolean isUserInRole(String rol) {
        LoginBean loginBean
                = (LoginBean) JSFUtil.getBean("loginBean");
        usuarioCorreo = loginBean.getUsuarioCorreo();
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
