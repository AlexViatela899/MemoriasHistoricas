package co.edu.poligran.memoriashistoricas.web.beans.menu;

import co.edu.poligran.memoriashistoricas.web.beans.sesion.InicioSesionBean;
import co.edu.poligran.memoriashistoricas.web.util.ConstantesMemoriasHistoricas;
import co.edu.poligran.memoriashistoricas.web.util.JSFUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

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

    private static final long serialVersionUID = 1L;
    private String url;

    public String irMenu(String caseMenu) throws IOException, Exception {
        ConstantesMemoriasHistoricas.ITEM_MENU seleccionado
                = ConstantesMemoriasHistoricas.ITEM_MENU.valueOf(caseMenu);
        InicioSesionBean inicioSesionBean
                = JSFUtil.getSessionBean(InicioSesionBean.class);
        List<String> roles;
        String usuarioLogueado = inicioSesionBean.getUsuarioCorreo();

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
        }
        return url;
    }

}
