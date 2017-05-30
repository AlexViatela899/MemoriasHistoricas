package co.edu.poligran.memoriashistoricas.web.util;

/**
 *  
 * @author Cristian Peralta
 */
public final class ConstantesMemoriasHistoricas {

    public enum ITEM_MENU {

        INICIO("INICIO"),
        RECORRIDO("RECORRIDO"),
        FARC("FARC"),
        AUC("AUC"),
        CCALI("CCALI"),
        CMEDELLIN("CMEDELLIN"),
        ELN("ELN"),
        ERPAC("ERPAC"),
        M19("M19"),
        RASTROJOS("RASTROJOS"),
        URABENOS("URABENOS"),
        GUAJIRA("GUAJIRA"),
        GUAJIRA_1("GUAJIRA_1"),
        GUAJIRA_2("GUAJIRA_2"),
        MAGDALENA("MAGDALENA"),
        MAGDALENA_1("MAGDALENA_1"),
        MAGDALENA_2("MAGDALENA_2"),
        GPARM("GPARM"),
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
