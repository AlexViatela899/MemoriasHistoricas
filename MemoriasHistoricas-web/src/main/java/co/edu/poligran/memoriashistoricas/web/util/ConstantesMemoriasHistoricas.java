package co.edu.poligran.memoriashistoricas.web.util;

/**
 *  
 * @author Cristian Peralta
 */
public final class ConstantesMemoriasHistoricas {

    public enum ITEM_MENU {

        INICIO("INICIO"),
        RECORRIDO("RECORRIDO"),
        GESTION("GESTION");
        String descripcion;

        private ITEM_MENU(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescription() {
            return descripcion;
        }
    }
}
