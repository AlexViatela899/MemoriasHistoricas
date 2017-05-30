/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.poligran.memoriashistoricas.ejb.persitence.facade;

import co.edu.poligran.memoriashistoricas.ejb.persitence.entity.Usuario;
import co.edu.poligran.memoriashistoricas.ejb.vo.ResultadoOperacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author peraltace
 */
@Local
public interface MemoriasHistoricasFacadeLocal {

    public Usuario autenticar(String usuarioCorreo, String clave);
    
    public ResultadoOperacion cambiarClave(String usuario,
            String clave, String nuevaClave);
    
    public List<String> obtenerRolesUsuario(String usuarioCorreo);
    
    public ResultadoOperacion registrarUsuario(Usuario usuario);

}
