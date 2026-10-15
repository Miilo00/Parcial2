package co.edu.poli.seguros.Aplicacion.Servicios;

import co.edu.poli.seguros.Aplicacion.Puerto.Entrada.BuscarSegurosUseCase;
import co.edu.poli.seguros.Aplicacion.Puerto.Salida.RepositorySeguro;
import co.edu.poli.seguros.Dominio.Seguro;

/**
 * Servicio que implementa el caso de uso Buscar Seguro por Número.
 * 
 * @author Cristian Camilo Vera
 * @version 1.0
 */
public class BuscarSegurosServicios implements BuscarSegurosUseCase {
    private final RepositorySeguro repositorySeguro;

    public BuscarSegurosServicios(RepositorySeguro repositorySeguro) {
        this.repositorySeguro = repositorySeguro;
    }

    @Override
    public Seguro buscarSeguroPorNumero(String numero) {
        return repositorySeguro.buscarPorNumero(numero);
    }
}