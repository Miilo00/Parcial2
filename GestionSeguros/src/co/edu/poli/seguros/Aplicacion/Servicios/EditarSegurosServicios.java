package co.edu.poli.seguros.Aplicacion.Servicios;

import co.edu.poli.seguros.Aplicacion.Puerto.Entrada.EditarSegurosUseCase;
import co.edu.poli.seguros.Aplicacion.Puerto.Salida.RepositorySeguro;
import co.edu.poli.seguros.Dominio.Seguro;

/**
 * Servicio que implementa el caso de uso Editar Seguro.
 * 
 * @author Cristian Camilo Vera
 * @version 1.0
 */
public class EditarSegurosServicios implements EditarSegurosUseCase {
    private final RepositorySeguro repositorySeguro;

    public EditarSegurosServicios(RepositorySeguro repositorySeguro) {
        this.repositorySeguro = repositorySeguro;
    }

    @Override
    public void editarSeguro(String numero, Seguro seguro) {
        repositorySeguro.actualizar(numero, seguro);
    }
}