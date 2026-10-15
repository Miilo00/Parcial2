package co.edu.poli.seguros.Dominio;

import java.time.LocalDate;

/**
 * Clase abstracta que representa un Seguro genérico.
 * 
 * @author Cristian Camilo Vera
 * @version 1.0
 */
public abstract class Seguro {
    protected String numero;
    protected LocalDate fechaExpedicion;
    protected double valor;
    protected String estado;
    protected Asegurado asegurado;

    /**
     * Constructor parametrizado para la clase Seguro.
     * 
     * @param numero Número de póliza del seguro
     * @param fechaExpedicion Fecha en que se expidió el seguro
     * @param valor Valor monetario del seguro
     * @param estado Estado actual del seguro (Activo, Inactivo, etc.)
     * @param asegurado Objeto Asegurado asociado a la póliza
     */
    public Seguro(String numero, LocalDate fechaExpedicion, double valor, String estado, Asegurado asegurado) {
        this.numero = numero;
        this.fechaExpedicion = fechaExpedicion;
        this.valor = valor;
        this.estado = estado;
        this.asegurado = asegurado;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDate getFechaExpedicion() {
        return fechaExpedicion;
    }

    public void setFechaExpedicion(LocalDate fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Asegurado getAsegurado() {
        return asegurado;
    }

    public void setAsegurado(Asegurado asegurado) {
        this.asegurado = asegurado;
    }
}