/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.poligran.memoriashistoricas.ejb.vo;

/**
 * @Objetivo:
 * @Descripción:
 * @Fecha creación:
 * @author Cristian Peralta
 * @Versión: 1.0
 */
public class ResultadoOperacion {

    private boolean resultadoTransaccion;
    private String mensajeTransaccion;

    public boolean isResultadoTransaccion() {
        return resultadoTransaccion;
    }

    public void setResultadoTransaccion(boolean resultadoTransaccion) {
        this.resultadoTransaccion = resultadoTransaccion;
    }

    public String getMensajeTransaccion() {
        return mensajeTransaccion;
    }

    public void setMensajeTransaccion(String mensajeTransaccion) {
        this.mensajeTransaccion = mensajeTransaccion;
    }
    
}
