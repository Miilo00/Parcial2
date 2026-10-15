package co.edu.poli.seguros.Infraestructura.UI;

import co.edu.poli.seguros.Aplicacion.Servicios.*;
import co.edu.poli.seguros.Dominio.*;
import co.edu.poli.seguros.Infraestructura.Persistencia.MySqlSeguroRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

/**
 * Controlador de JavaFX para la interfaz gráfica de Gestión de Seguros.
 * 
 * @author Cristian Camilo Vera
 * @version 1.0
 */
public class SeguroControlador {

    @FXML private TextField txtNumero;
    @FXML private DatePicker dpFecha;
    @FXML private TextField txtValor;
    @FXML private ComboBox<String> cbEstado;
    @FXML private ComboBox<String> cbTipo;
    
    @FXML private VBox paneVida;
    @FXML private TextField txtBeneficiario;
    @FXML private TextField txtParentesco;

    @FXML private VBox paneVehiculo;
    @FXML private TextField txtMarca;

    @FXML private ComboBox<Asegurado> cbAsegurado;

    @FXML private TableView<Seguro> tablaSeguros;
    @FXML private TableColumn<Seguro, String> colNumero;
    @FXML private TableColumn<Seguro, String> colTipo;
    @FXML private TableColumn<Seguro, String> colAsegurado;
    @FXML private TableColumn<Seguro, LocalDate> colFecha;
    @FXML private TableColumn<Seguro, Double> colValor;
    @FXML private TableColumn<Seguro, String> colEstado;
    @FXML private TableColumn<Seguro, String> colDetalle;

    private final MySqlSeguroRepository repo = new MySqlSeguroRepository();
    private final CrearSegurosServicios crearServicio = new CrearSegurosServicios(repo);
    private final BuscarListaSegurosServicios listarServicio = new BuscarListaSegurosServicios(repo);
    private final EditarSegurosServicios editarServicio = new EditarSegurosServicios(repo);
    private final EliminarSegurosServicios eliminarServicio = new EliminarSegurosServicios(repo);

    @FXML
    public void initialize() {
        cbEstado.setItems(FXCollections.observableArrayList("Activo", "Inactivo", "Suspendido"));
        cbTipo.setItems(FXCollections.observableArrayList("Vida", "Vehículo"));

        cbAsegurado.setItems(FXCollections.observableArrayList(
            new Asegurado("1010", "Ana María Gómez", "ana.gomez@mail.com"),
            new Asegurado("2020", "Carlos Andrés Pérez", "carlos.perez@mail.com")
        ));

        // Configuración de las columnas de la tabla
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        
        // Tipo de seguro basado en la instancia
        colTipo.setCellValueFactory(cellData -> {
            String tipo = (cellData.getValue() instanceof SeguroVida) ? "Vida" : "Vehículo";
            return new javafx.beans.property.SimpleStringProperty(tipo);
        });

        // Nombre del asegurado asociado
        colAsegurado.setCellValueFactory(cellData -> {
            String nombreAs = (cellData.getValue().getAsegurado() != null) ? cellData.getValue().getAsegurado().getNombre() : "N/A";
            return new javafx.beans.property.SimpleStringProperty(nombreAs);
        });

        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaExpedicion"));
        colValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        // Detalle dinámico (Muestra Beneficiario o Marca según corresponda)
        colDetalle.setCellValueFactory(cellData -> {
            Seguro s = cellData.getValue();
            String detalle = "";
            if (s instanceof SeguroVida) {
                detalle = "Ben: " + ((SeguroVida) s).getNombreBeneficiario() + " (" + ((SeguroVida) s).getParentesco() + ")";
            } else if (s instanceof SeguroVehiculo) {
                detalle = "Marca: " + ((SeguroVehiculo) s).getMarca();
            }
            return new javafx.beans.property.SimpleStringProperty(detalle);
        });

        cbTipo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if ("Vida".equals(newValue)) {
                paneVida.setVisible(true);
                paneVida.setManaged(true);
                paneVehiculo.setVisible(false);
                paneVehiculo.setManaged(false);
            } else if ("Vehículo".equals(newValue)) {
                paneVida.setVisible(false);
                paneVida.setManaged(false);
                paneVehiculo.setVisible(true);
                paneVehiculo.setManaged(true);
            }
        });

        paneVida.setVisible(false);
        paneVida.setManaged(false);
        paneVehiculo.setVisible(false);
        paneVehiculo.setManaged(false);

        cargarTabla();
    }

    @FXML
    private void handleCrear() {
        try {
            validarCampos();
            Seguro seguro = construirSeguroDesdeFormulario();
            crearServicio.crearSeguro(seguro);
            mostrarAlerta("Éxito", "Seguro creado correctamente.", Alert.AlertType.INFORMATION);
            limpiarCampos();
            cargarTabla();
        } catch (Exception e) {
            mostrarAlerta("Error de Validación", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleEditar() {
        try {
            String numero = txtNumero.getText();
            if (numero == null || numero.isEmpty()) {
                throw new IllegalArgumentException("Debe ingresar el número del seguro a editar.");
            }
            Seguro seguro = construirSeguroDesdeFormulario();
            editarServicio.editarSeguro(numero, seguro);
            mostrarAlerta("Éxito", "Seguro actualizado correctamente.", Alert.AlertType.INFORMATION);
            limpiarCampos();
            cargarTabla();
        } catch (Exception e) {
            mostrarAlerta("Error", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleEliminar() {
        try {
            Seguro seleccionado = tablaSeguros.getSelectionModel().getSelectedItem();
            if (seleccionado == null) {
                throw new IllegalArgumentException("Seleccione un seguro de la tabla para eliminar.");
            }
            eliminarServicio.eliminarSeguro(seleccionado.getNumero());
            mostrarAlerta("Éxito", "Seguro eliminado correctamente.", Alert.AlertType.INFORMATION);
            cargarTabla();
        } catch (Exception e) {
            mostrarAlerta("Error", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleLimpiar() {
        limpiarCampos();
    }

    private void cargarTabla() {
        ObservableList<Seguro> datos = FXCollections.observableArrayList(listarServicio.obtenerListaSeguros());
        tablaSeguros.setItems(datos);
        tablaSeguros.refresh();
    }

    private Seguro construirSeguroDesdeFormulario() {
        String numero = txtNumero.getText();
        LocalDate fecha = dpFecha.getValue();
        double valor = Double.parseDouble(txtValor.getText());
        String estado = cbEstado.getValue();
        String tipo = cbTipo.getValue();
        Asegurado asegurado = cbAsegurado.getValue();

        if ("Vida".equalsIgnoreCase(tipo)) {
            return new SeguroVida(numero, fecha, valor, estado, asegurado, txtBeneficiario.getText(), txtParentesco.getText());
        } else {
            return new SeguroVehiculo(numero, fecha, valor, estado, asegurado, txtMarca.getText());
        }
    }

    private void validarCampos() {
        if (txtNumero.getText().isEmpty() || dpFecha.getValue() == null || txtValor.getText().isEmpty() ||
            cbEstado.getValue() == null || cbTipo.getValue() == null || cbAsegurado.getValue() == null) {
            throw new IllegalArgumentException("Todos los campos principales son obligatorios.");
        }
    }

    private void limpiarCampos() {
        txtNumero.clear();
        dpFecha.setValue(null);
        txtValor.clear();
        cbEstado.setValue(null);
        cbTipo.setValue(null);
        txtBeneficiario.clear();
        txtParentesco.clear();
        txtMarca.clear();
        cbAsegurado.setValue(null);
        paneVida.setVisible(false);
        paneVida.setManaged(false);
        paneVehiculo.setVisible(false);
        paneVehiculo.setManaged(false);
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}