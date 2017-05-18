package co.edu.poligran.memoriashistoricas.ejb.exception;

import java.io.Serializable;

/**
 * Objetivo: Administrar los errores de la Aplicación Descripcion: Administrar
 * los errores de la Aplicación
 *
 * @author Cristian Peralta
 * @versión: 1.0 Fecha Creación: 25 - mayo - 2016
 */
public class MemoriasHistoricasException extends Exception implements Serializable {

    public MemoriasHistoricasException() {
    }

    public MemoriasHistoricasException(String message) {
        super(message);
    }

    public MemoriasHistoricasException(String message, Throwable cause) {
        super(message, cause);
    }

    public MemoriasHistoricasException(Throwable cause) {
        super(cause);
    }
}
