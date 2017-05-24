/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.poligran.memoriashistoricas.ejb.persitence.facade;

import co.edu.poligran.memoriashistoricas.ejb.persitence.entity.Usuario;
import javax.ejb.Local;

/**
 *
 * @author peraltace
 */
@Local
public interface MemoriasHistoricasFacadeLocal {

    public Usuario autenticar(String usuarioCorreo, String clave);
    
}
