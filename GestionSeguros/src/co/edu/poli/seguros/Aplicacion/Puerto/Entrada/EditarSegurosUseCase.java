package co.edu.poli.seguros.Aplicacion.Puerto.Entrada;

import co.edu.poli.seguros.Dominio.Seguro;

/**
 * Puerto de entrada para editar un seguro existente.
 * 
 * @author Cristian Camilo Vera
 * @version 1.0
 */
public interface EditarSegurosUseCase {
    void editarSeguro(String numero, Seguro seguro);
}