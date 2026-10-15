module GestionSeguros {
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.controls; // Asegúrate de incluir esta línea para los componentes

	// 1. Permite a JavaFX iniciar tu aplicación (Arregla el error actual)
	exports co.edu.poli.seguros.Vista to javafx.graphics;
	
	// 2. Abre todos los paquetes para que FXML y los controladores funcionen sin restricciones
	opens co.edu.poli.seguros.Vista to javafx.fxml, javafx.graphics;
	opens co.edu.poli.seguros.Infraestructura.UI to javafx.fxml, javafx.graphics;
}
