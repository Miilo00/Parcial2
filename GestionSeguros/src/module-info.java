module GestionSeguros {
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.controls;
	requires java.sql; // Conexión JDBC para la base de datos

	// Permite a JavaFX iniciar tu aplicación
	exports co.edu.poli.seguros.Vista to javafx.graphics;
	
	// Abre los paquetes necesarios para FXML, UI y la reflexión de la tabla (Dominio)
	opens co.edu.poli.seguros.Vista to javafx.fxml, javafx.graphics;
	opens co.edu.poli.seguros.Infraestructura.UI to javafx.fxml, javafx.graphics;
	opens co.edu.poli.seguros.Dominio to javafx.base;
}