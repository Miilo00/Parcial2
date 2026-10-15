package co.edu.poli.seguros.Aplicacion.Puerto.Entrada;

import co.edu.poli.seguros.Dominio.Seguro;

/**
 * Puerto de entrada para buscar un seguro por su número.
 * 
 * @author Cristian Camilo Vera
 * @version 1.0
 */
public interface BuscarSegurosUseCase {
    Seguro buscarSeguroPorNumero(String numero);
}