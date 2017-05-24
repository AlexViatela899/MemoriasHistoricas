/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.poligran.memoriashistoricas.ejb.persitence.facade;

import co.edu.poligran.memoriashistoricas.ejb.persitence.entity.Usuario;
import co.edu.poligran.memoriashistoricas.ejb.persitence.manager.MemoriasHistoricasManager;
import co.edu.poligran.memoriashistoricas.ejb.vo.ResultadoOperacion;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author peraltace
 */
@Named
@Stateless
public class MemoriasHistoricasFacade implements MemoriasHistoricasFacadeLocal {

    @PersistenceContext(unitName = "MemoriasHistoricasPU")
    private EntityManager em;
    private MemoriasHistoricasManager memoriasHistoricasManager;

    @PostConstruct
    public void init() {
        memoriasHistoricasManager = new MemoriasHistoricasManager(em);
    }
    
    @Override
    public Usuario autenticar(String usuarioCorreo, String clave) {
        return memoriasHistoricasManager.autenticar(usuarioCorreo, clave);
    }

    @Override
    public ResultadoOperacion cambiarClave(String usuarioCorreo, String clave,
            String nuevaClave) {
        return memoriasHistoricasManager.cambiarClave(usuarioCorreo, clave,
                nuevaClave);
    }
}
