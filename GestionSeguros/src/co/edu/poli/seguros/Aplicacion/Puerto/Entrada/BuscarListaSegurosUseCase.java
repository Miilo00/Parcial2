package co.edu.poli.seguros.Aplicacion.Puerto.Entrada;

import co.edu.poli.seguros.Dominio.Seguro;
import java.util.List;

/**
 * Puerto de entrada para listar todos los seguros.
 * 
 * @author Cristian Camilo Vera
 * @version 1.0
 */
public interface BuscarListaSegurosUseCase {
    List<Seguro> obtenerListaSeguros();
}