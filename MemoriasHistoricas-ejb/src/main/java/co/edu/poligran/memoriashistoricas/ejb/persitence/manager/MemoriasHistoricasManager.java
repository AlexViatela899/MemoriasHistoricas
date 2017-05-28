/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.poligran.memoriashistoricas.ejb.persitence.manager;

import co.edu.poligran.memoriashistoricas.ejb.persitence.dao.UsuarioDaoImpl;
import co.edu.poligran.memoriashistoricas.ejb.persitence.entity.Usuario;
import co.edu.poligran.memoriashistoricas.ejb.vo.ResultadoOperacion;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 * @Objetivo:
 * @Descripción:
 * @Fecha creación:
 * @author Cristian Peralta
 * @Versión: 1.0
 */
public class MemoriasHistoricasManager {
    
    private UsuarioDaoImpl usuarioDaoImpl;

    public MemoriasHistoricasManager(EntityManager em) {
        usuarioDaoImpl = new UsuarioDaoImpl(em);
    }
    
    public Usuario autenticar(String usuarioCorreo, String clave) {
        String claveEncriptada = null;
        Usuario usuario = null;
        try {
            claveEncriptada = getHash512(clave);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MemoriasHistoricasManager.class.getName())
                    .log(Level.SEVERE, ex.getMessage(), ex);
        }
        try {
            usuario = usuarioDaoImpl.autenticar(
                    usuarioCorreo, claveEncriptada);
        } catch (NoResultException nre) {
            Logger.getLogger(MemoriasHistoricasManager.class.getName())
                    .log(Level.SEVERE, nre.getMessage(), nre);
        }
        return usuario;
    }
    
    public ResultadoOperacion cambiarClave(String usuarioCorreo, String clave,
            String nuevaClave) {
        ResultadoOperacion resultadoOperacion = new ResultadoOperacion();
        String claveEncriptada = null;
        try {
            claveEncriptada = getHash512(clave);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MemoriasHistoricasManager.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        Usuario usuario = usuarioDaoImpl.autenticar(
                usuarioCorreo, claveEncriptada);
        if (usuario == null) {
            resultadoOperacion.setResultadoTransaccion(false);
            resultadoOperacion.setMensajeTransaccion(
                    "Usuario o contraseña incorrectos.");
            return resultadoOperacion;
        }
        claveEncriptada = null;
        try {
            claveEncriptada = getHash512(nuevaClave);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MemoriasHistoricasManager.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        usuario.setClave(claveEncriptada);
        Usuario cambioUsuario = usuarioDaoImpl.merge(usuario);
        if (cambioUsuario != null) {
            if (cambioUsuario.getClave() != null
                    && !cambioUsuario.getClave().trim().isEmpty()) {
                if (cambioUsuario.getClave().equals(claveEncriptada)) {
                    resultadoOperacion.setResultadoTransaccion(true);
                    resultadoOperacion.setMensajeTransaccion(
                            "Se cambió correctamente la contraseña.");
                }
            } else {
                resultadoOperacion.setResultadoTransaccion(false);
                resultadoOperacion.setMensajeTransaccion(
                        "Error cambiando la contraseña. Intente nuevamente");
                return resultadoOperacion;
            }
        } else {
            resultadoOperacion.setResultadoTransaccion(false);
            resultadoOperacion.setMensajeTransaccion(
                    "Error cambiando la contraseña. Intente nuevamente");
            return resultadoOperacion;
        }
        return resultadoOperacion;
    }
    
    public List<String> obtenerRolesUsuario(String usuarioCorreo) {
        Usuario usuario = usuarioDaoImpl.findByNombreOCorreo(usuarioCorreo);
        List<String> rolesUsuario = new LinkedList<>();
        if (usuario != null) {
            if (usuario.getIdRol() != null) {
                rolesUsuario.add(usuario.getIdRol().getNombreRol());
            } else {
                    return null;
            }
        } else {
            return null;
        }
        return rolesUsuario;
    }

    public String getHash512(String message) throws NoSuchAlgorithmException {
        MessageDigest md;
        byte[] buffer, digest;
        String hash = "";

        buffer = message.getBytes();
        md = MessageDigest.getInstance("SHA-512");
        md.update(buffer);
        digest = md.digest();

        for (byte aux : digest) {
            int b = aux & 0xff;
            if (Integer.toHexString(b).length() == 1) {
                hash += "0";
            }
            hash += Integer.toHexString(b);
        }
        return hash;
    }

}
