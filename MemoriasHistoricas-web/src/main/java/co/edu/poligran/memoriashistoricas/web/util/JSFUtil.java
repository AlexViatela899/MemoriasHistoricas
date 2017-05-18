/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.poligran.memoriashistoricas.web.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.metamodel.IdentifiableType;
import javax.persistence.metamodel.Metamodel;
import javax.persistence.metamodel.SingularAttribute;
import javax.servlet.ServletRequest;

/**
 *
 * @author Admin
 */
public class JSFUtil {

    public static Object getBean(String name) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return facesContext.getApplication().getELResolver().getValue(
                facesContext.getELContext(), null, name);
    }

    public static <T extends Object> T getSessionBean(Class<T> clase) {
        ManagedBean sessionBean = clase.getAnnotation(ManagedBean.class);
        String nombre = "#{" + sessionBean.name() + "}";
        FacesContext contx = FacesContext.getCurrentInstance();
        return (T) contx.getApplication().getExpressionFactory()
                .createValueExpression(contx.getELContext(), nombre, clase)
                .getValue(contx.getELContext());

    }

    /**
     * Metodo utilizado para poner en el FacesContext un atributo - valor.
     *
     * @param name Nombre del Atributo.
     * @param value Valor de tipo Object.
     */
    public static void setBean(String name, Object value) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getApplication().getELResolver().setValue(
                facesContext.getELContext(), null, name, value);
    }

    public static <T extends Object> T getBean(
            String name, Class<T> beanClass) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return (T) facesContext.getApplication().getELResolver().getValue(
                facesContext.getELContext(), null, name);
    }

    public <T> SingularAttribute<? super T, ?> getIdAttribute(EntityManager em,
            Class<T> clazz) {
        Metamodel m = em.getMetamodel();
        IdentifiableType<T> of = (IdentifiableType<T>) m.managedType(clazz);
        return of.getId(of.getIdType().getJavaType());
    }

    public static String getServerName() {
        ServletRequest request
                = (ServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
        return request.getServerName();
    }

    public static String getUserName() {
        return FacesContext.getCurrentInstance().getExternalContext()
                .getRemoteUser();
    }

    public static String getHost() {
        String hostNumber = "";
        try {
            InetAddress host = InetAddress.getLocalHost();
            hostNumber = host.getHostAddress();
        } catch (UnknownHostException ex) {
            Logger.getLogger(JSFUtil.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return hostNumber;
    }
}
