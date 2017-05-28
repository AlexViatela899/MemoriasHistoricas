/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.poligran.memoriashistoricas.ejb.persitence.dao;

import co.edu.poligran.memoriashistoricas.ejb.persitence.entity.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * @Objetivo:
 * @Descripción:
 * @Fecha creación:
 * @author Cristian Peralta
 * @Versión: 1.0
 */
public class UsuarioDaoImpl {
    private EntityManager em;
    
    public UsuarioDaoImpl(EntityManager em) {
        this.em = em;
    }
    
    public Usuario autenticar(String usuario, String clave) {
        Query query = em.createNamedQuery(
                "Usuario.findByNombreUsuarioOrCorreoAndClave");
        query.setParameter("nombreUsuario", usuario);
        query.setParameter("correoElectronico", usuario);
        query.setParameter("clave", clave);
        return (Usuario) query.getSingleResult();
    }
    
    public Usuario merge(Usuario usuario) {
        return em.merge(usuario);
    }
    
    public Usuario findByNombreOCorreo(String usuarioCorreo) {
        Query query = em.createNamedQuery(
                "Usuario.findByNombreUsuarioOrCorreo");
        query.setParameter("nombreUsuario", usuarioCorreo);
        query.setParameter("correoElectronico", usuarioCorreo);
        return (Usuario) query.getSingleResult();
    }
}
