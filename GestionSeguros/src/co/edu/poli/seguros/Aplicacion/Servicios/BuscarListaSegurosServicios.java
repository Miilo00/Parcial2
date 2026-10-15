package co.edu.poli.seguros.Aplicacion.Servicios;

import co.edu.poli.seguros.Aplicacion.Puerto.Entrada.BuscarListaSegurosUseCase;
import co.edu.poli.seguros.Aplicacion.Puerto.Salida.RepositorySeguro;
import co.edu.poli.seguros.Dominio.Seguro;
import java.util.List;

/**
 * Servicio que implementa el caso de uso Buscar Lista de Seguros.
 * 
 * @author Cristian Camilo Vera
 * @version 1.0
 */
public class BuscarListaSegurosServicios implements BuscarListaSegurosUseCase {
    private final RepositorySeguro repositorySeguro;

    public BuscarListaSegurosServicios(RepositorySeguro repositorySeguro) {
        this.repositorySeguro = repositorySeguro;
    }

    @Override
    public List<Seguro> obtenerListaSeguros() {
        return repositorySeguro.buscarTodos();
    }
}