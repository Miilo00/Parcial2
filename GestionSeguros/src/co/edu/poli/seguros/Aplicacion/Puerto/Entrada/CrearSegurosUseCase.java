package co.edu.poli.seguros.Aplicacion.Puerto.Entrada;

import co.edu.poli.seguros.Dominio.Seguro;

/**
 * Puerto de entrada para el caso de uso de Crear Seguro.
 * 
 * @author Cristian Camilo Vera
 * @version 1.0
 */
public interface CrearSegurosUseCase {
    void crearSeguro(Seguro seguro);
}