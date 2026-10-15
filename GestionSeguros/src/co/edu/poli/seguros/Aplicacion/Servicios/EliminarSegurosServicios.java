package co.edu.poli.seguros.Aplicacion.Servicios;

import co.edu.poli.seguros.Aplicacion.Puerto.Entrada.EliminarSegurosUseCase;
import co.edu.poli.seguros.Aplicacion.Puerto.Salida.RepositorySeguro;

/**
 * Servicio que implementa el caso de uso Eliminar Seguro.
 * 
 * @author Cristian Camilo Vera
 * @version 1.0
 */
public class EliminarSegurosServicios implements EliminarSegurosUseCase {
    private final RepositorySeguro repositorySeguro;

    public EliminarSegurosServicios(RepositorySeguro repositorySeguro) {
        this.repositorySeguro = repositorySeguro;
    }

    @Override
    public void eliminarSeguro(String numero) {
        repositorySeguro.eliminar(numero);
    }
}