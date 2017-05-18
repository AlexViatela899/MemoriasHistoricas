package co.edu.poligran.memoriashistoricas.web.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Cristian Peralta
 */
public final class InputFile {

    private static final String CONFIG_PATH = System.getProperty(
            "poli.util.config_path", "config/poli/config.properties");
    private static Properties properties;

    /**
     * Obtiene el valor de un parametro de configuracion
     *
     * @param key una de las constantes de esta clase
     * @return el valor en el archivo de configuracion
     */
    public synchronized static String getProperty(String key) {
        properties = new Properties();
        InputFile.reload();
        String value = properties.getProperty(key);
        assert value != null : "Propiedad : \"" + key + "\" no encontrada";
        return value;
    }

    public synchronized static void reload() {
        properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream(CONFIG_PATH);
            properties.load(fis);
        } catch (IOException ex) {
            properties = null;
            throw new RuntimeException(ex);
        }
    }

    public static Properties getProperties() {
        if (properties == null) {
            InputFile.reload();
        }
        return properties;
    }

    public synchronized static void save(Properties properties) {
        try {
            FileOutputStream fos = new FileOutputStream(CONFIG_PATH);
            properties.store(fos, CONFIG_PATH);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
