package co.edu.poli.seguros.Aplicacion.Servicios;

import co.edu.poli.seguros.Aplicacion.Puerto.Entrada.CrearSegurosUseCase;
import co.edu.poli.seguros.Aplicacion.Puerto.Salida.RepositorySeguro;
import co.edu.poli.seguros.Dominio.Seguro;

/**
 * Servicio que implementa el caso de uso Crear Seguro.
 * 
 * @author Cristian Camilo Vera
 * @version 1.0
 */
public class CrearSegurosServicios implements CrearSegurosUseCase {
    private final RepositorySeguro repositorySeguro;

    public CrearSegurosServicios(RepositorySeguro repositorySeguro) {
        this.repositorySeguro = repositorySeguro;
    }

    @Override
    public void crearSeguro(Seguro seguro) {
        repositorySeguro.guardar(seguro);
    }
}