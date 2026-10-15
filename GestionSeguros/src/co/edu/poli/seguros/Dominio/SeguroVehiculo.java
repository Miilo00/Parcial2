package co.edu.poli.seguros.Dominio;

import java.time.LocalDate;

/**
 * Clase que representa un Seguro de Vehículo, heredando de Seguro.
 * 
 * @author Cristian Camilo Vera
 * @version 1.0
 */
public class SeguroVehiculo extends Seguro {
    private String marca;

    /**
     * Constructor parametrizado para SeguroVehiculo.
     * 
     * @param numero Número de póliza
     * @param fechaExpedicion Fecha de expedición
     * @param valor Valor del seguro
     * @param estado Estado del seguro
     * @param asegurado Asegurado asociado
     * @param marca Marca del vehículo asegurado
     */
    public SeguroVehiculo(String numero, LocalDate fechaExpedicion, double valor, String estado, Asegurado asegurado, String marca) {
        super(numero, fechaExpedicion, valor, estado, asegurado);
        this.marca = marca;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}