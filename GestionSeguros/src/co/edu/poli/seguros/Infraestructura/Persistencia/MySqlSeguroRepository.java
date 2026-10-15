package co.edu.poli.seguros.Infraestructura.Persistencia;

import co.edu.poli.seguros.Aplicacion.Puerto.Salida.RepositorySeguro;
import co.edu.poli.seguros.Dominio.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación JDBC de RepositorySeguro conectada a MySQL, manejando relación 1 a muchos.
 * 
 * @author Cristian Camilo Vera
 * @version 1.0
 */
public class MySqlSeguroRepository implements RepositorySeguro {

    @Override
    public void guardar(Seguro seguro) {
        String sqlSeguro = "INSERT INTO seguro (numero, fecha_expedicion, valor, estado, tipo, nombre_beneficiario, parentesco, marca, asegurado_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String sqlAsegurado = "INSERT IGNORE INTO asegurado (identificacion, nombre, correo) VALUES (?, ?, ?)";

        try (Connection conn = ConexionBD.obtenerConexion()) {
            conn.setAutoCommit(false);

            try (PreparedStatement pstmtAs = conn.prepareStatement(sqlAsegurado);
                 PreparedStatement pstmtSeg = conn.prepareStatement(sqlSeguro)) {

                // Guardar asegurado primero (Relación 1 a muchos: un asegurado tiene varios seguros)
                pstmtAs.setString(1, seguro.getAsegurado().getIdentificacion());
                pstmtAs.setString(2, seguro.getAsegurado().getNombre());
                pstmtAs.setString(3, seguro.getAsegurado().getCorreoElectronico());
                pstmtAs.executeUpdate();

                // Guardar seguro
                pstmtSeg.setString(1, seguro.getNumero());
                pstmtSeg.setDate(2, Date.valueOf(seguro.getFechaExpedicion()));
                pstmtSeg.setDouble(3, seguro.getValor());
                pstmtSeg.setString(4, seguro.getEstado());

                if (seguro instanceof SeguroVida) {
                    pstmtSeg.setString(5, "VIDA");
                    pstmtSeg.setString(6, ((SeguroVida) seguro).getNombreBeneficiario());
                    pstmtSeg.setString(7, ((SeguroVida) seguro).getParentesco());
                    pstmtSeg.setNull(8, Types.VARCHAR);
                } else if (seguro instanceof SeguroVehiculo) {
                    pstmtSeg.setString(5, "VEHICULO");
                    pstmtSeg.setNull(6, Types.VARCHAR);
                    pstmtSeg.setNull(7, Types.VARCHAR);
                    pstmtSeg.setString(8, ((SeguroVehiculo) seguro).getMarca());
                }
                pstmtSeg.setString(9, seguro.getAsegurado().getIdentificacion());
                pstmtSeg.executeUpdate();

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el seguro: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Seguro> buscarTodos() {
        List<Seguro> lista = new ArrayList<>();
        String sql = "SELECT s.*, a.identificacion, a.nombre, a.correo FROM seguro s JOIN asegurado a ON s.asegurado_id = a.identificacion";

        try (Connection conn = ConexionBD.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Asegurado asegurado = new Asegurado(
                    rs.getString("identificacion"),
                    rs.getString("nombre"),
                    rs.getString("correo")
                );

                String tipo = rs.getString("tipo");
                Seguro seguro;
                if ("VIDA".equalsIgnoreCase(tipo)) {
                    seguro = new SeguroVida(
                        rs.getString("numero"),
                        rs.getDate("fecha_expedicion").toLocalDate(),
                        rs.getDouble("valor"),
                        rs.getString("estado"),
                        asegurado,
                        rs.getString("nombre_beneficiario"),
                        rs.getString("parentesco")
                    );
                } else {
                    seguro = new SeguroVehiculo(
                        rs.getString("numero"),
                        rs.getDate("fecha_expedicion").toLocalDate(),
                        rs.getDouble("valor"),
                        rs.getString("estado"),
                        asegurado,
                        rs.getString("marca")
                    );
                }
                lista.add(seguro);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar todos los seguros: " + e.getMessage(), e);
        }
        return lista;
    }

    @Override
    public Seguro buscarPorNumero(String numero) {
        String sql = "SELECT s.*, a.identificacion, a.nombre, a.correo FROM seguro s JOIN asegurado a ON s.asegurado_id = a.identificacion WHERE s.numero = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, numero);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Asegurado asegurado = new Asegurado(
                        rs.getString("identificacion"),
                        rs.getString("nombre"),
                        rs.getString("correo")
                    );
                    String tipo = rs.getString("tipo");
                    if ("VIDA".equalsIgnoreCase(tipo)) {
                        return new SeguroVida(
                            rs.getString("numero"),
                            rs.getDate("fecha_expedicion").toLocalDate(),
                            rs.getDouble("valor"),
                            rs.getString("estado"),
                            asegurado,
                            rs.getString("nombre_beneficiario"),
                            rs.getString("parentesco")
                        );
                    } else {
                        return new SeguroVehiculo(
                            rs.getString("numero"),
                            rs.getDate("fecha_expedicion").toLocalDate(),
                            rs.getDouble("valor"),
                            rs.getString("estado"),
                            asegurado,
                            rs.getString("marca")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el seguro: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public void actualizar(String numero, Seguro seguro) {
        String sql = "UPDATE seguro SET fecha_expedicion=?, valor=?, estado=?, nombre_beneficiario=?, parentesco=?, marca=? WHERE numero=?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, Date.valueOf(seguro.getFechaExpedicion()));
            pstmt.setDouble(2, seguro.getValor());
            pstmt.setString(3, seguro.getEstado());

            if (seguro instanceof SeguroVida) {
                pstmt.setString(4, ((SeguroVida) seguro).getNombreBeneficiario());
                pstmt.setString(5, ((SeguroVida) seguro).getParentesco());
                pstmt.setNull(6, Types.VARCHAR);
            } else if (seguro instanceof SeguroVehiculo) {
                pstmt.setNull(4, Types.VARCHAR);
                pstmt.setNull(5, Types.VARCHAR);
                pstmt.setString(6, ((SeguroVehiculo) seguro).getMarca());
            }
            pstmt.setString(7, numero);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el seguro: " + e.getMessage(), e);
        }
    }

    @Override
    public void eliminar(String numero) {
        String sql = "DELETE FROM seguro WHERE numero = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, numero);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el seguro: " + e.getMessage(), e);
        }
    }
}
