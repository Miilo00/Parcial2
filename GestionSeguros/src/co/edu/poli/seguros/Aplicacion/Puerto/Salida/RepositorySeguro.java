package co.edu.poli.seguros.Aplicacion.Puerto.Salida;

import co.edu.poli.seguros.Dominio.Seguro;
import java.util.List;

/**
 * Puerto de salida (Repositorio) para la persistencia de los seguros.
 * 
 * @author Cristian Camilo Vera
 * @version 1.0
 */
public interface RepositorySeguro {
    void guardar(Seguro seguro);
    List<Seguro> buscarTodos();
    Seguro buscarPorNumero(String numero);
    void actualizar(String numero, Seguro seguro);
    void eliminar(String numero);
}