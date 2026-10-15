package co.edu.poli.seguros.Dominio;

import java.time.LocalDate;

/**
 * Clase que representa un Seguro de Vida, heredando de Seguro.
 * 
 * @author Cristian Camilo Vera
 * @version 1.0
 */
public class SeguroVida extends Seguro {
    private String nombreBeneficiario;
    private String parentesco;

    /**
     * Constructor parametrizado para SeguroVida.
     * 
     * @param numero Número de póliza
     * @param fechaExpedicion Fecha de expedición
     * @param valor Valor del seguro
     * @param estado Estado del seguro
     * @param asegurado Asegurado asociado
     * @param nombreBeneficiario Nombre del beneficiario de vida
     * @param parentesco Parentesco con el asegurado
     */
    public SeguroVida(String numero, LocalDate fechaExpedicion, double valor, String estado, Asegurado asegurado, String nombreBeneficiario, String parentesco) {
        super(numero, fechaExpedicion, valor, estado, asegurado);
        this.nombreBeneficiario = nombreBeneficiario;
        this.parentesco = parentesco;
    }

    public String getNombreBeneficiario() {
        return nombreBeneficiario;
    }

    public void setNombreBeneficiario(String nombreBeneficiario) {
        this.nombreBeneficiario = nombreBeneficiario;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }
}