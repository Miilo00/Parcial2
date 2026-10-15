package co.edu.poli.seguros.Dominio;

/**
 * Clase que representa a un Asegurado en el sistema de seguros.
 * 
 * @author Cristian Camilo Vera
 * @version 1.0
 */
public class Asegurado {
    private String identificacion;
    private String nombre;
    private String correoElectronico;

    public Asegurado(String identificacion, String nombre, String correoElectronico) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
    }

    public String getIdentificacion() { return identificacion; }
    public void setIdentificacion(String identificacion) { this.identificacion = identificacion; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }

    @Override
    public String toString() {
        return nombre + " (" + identificacion + ")";
    }
}