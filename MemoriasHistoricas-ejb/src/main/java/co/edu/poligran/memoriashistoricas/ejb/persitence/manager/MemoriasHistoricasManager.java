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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

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
        try {
            claveEncriptada = getHash512(clave);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MemoriasHistoricasManager.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        Usuario usuario = usuarioDaoImpl.autenticar(
                usuarioCorreo, claveEncriptada);
        return usuario;
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
