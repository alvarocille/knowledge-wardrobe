package acceso.dam.proyectosql.util;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

/**
 * La clase {@code R} proporciona métodos estáticos para acceder a recursos
 * como imágenes, archivos de propiedades, archivos de interfaz de usuario y
 * estilos dentro de la aplicación.
 */
public class R {

    /**
     * Obtiene un flujo de entrada para una imagen especificada por su nombre.
     *
     * @param name el nombre del archivo de imagen.
     * @return un {@code InputStream} para el archivo de imagen, o {@code null} si no se encuentra.
     */
    public static InputStream getImage(String name) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("images" + File.separator + name);
    }

    /**
     * Obtiene un flujo de entrada para un archivo de propiedades especificado por su nombre.
     *
     * @param name el nombre del archivo de propiedades.
     * @return un {@code InputStream} para el archivo de propiedades, o {@code null} si no se encuentra.
     */
    public static InputStream getProperties(String name) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("configuration" + File.separator + name);
    }

    /**
     * Obtiene la URL de un archivo de interfaz de usuario especificado por su nombre.
     *
     * @param name el nombre del archivo de interfaz de usuario.
     * @return un {@code URL} para el archivo de interfaz de usuario, o {@code null} si no se encuentra.
     */
    public static URL getUI(String name) {
        return Thread.currentThread().getContextClassLoader().getResource("ui" + File.separator + name);
    }

    /**
     * Obtiene la URL de un archivo de estilo especificado por su nombre.
     *
     * @param name el nombre del archivo de estilo.
     * @return un {@code URL} para el archivo de estilo, o {@code null} si no se encuentra.
     */
    public static URL getEstilos(String name) {
        return Thread.currentThread().getContextClassLoader().getResource("styles" + File.separator + name);
    }
}
