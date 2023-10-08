package redVendedores.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import redVendedores.aplication.Aplicacion;

public class RestablecerContrasenaAdminController {
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnRestablecer;

    @FXML
    private Label regresar;

    @FXML
    private TextField txtDocumento;
    
    ModelFactoryController modelFactoryController; 

    @FXML
    void restablecerContrasenaEvent(ActionEvent event) {
    	restablecerAction();
    }

    private void restablecerAction() {

    	String documento = "";

    	documento = txtDocumento.getText();

    	if(datosValidos(documento)){

    		boolean documentoValido = modelFactoryController.verificarDocumentoAdmin(documento);
    		if(documentoValido){
    			aplicacion.mostrarVentanaRestablecerContrasena2Admin(documento);
    		}else{
    			mostrarMensaje("Notificación Inicio sesion", "Usuario no existe", "Los datos ingresados no corresponde a un usuario valido", AlertType.INFORMATION);

    		}
    	}else{
			mostrarMensaje("Notificación Inicio sesion", "Datos Incompletos", "Debe ingresar los datos correctamente, despues de 3 intentos se bloqueara el usuario", AlertType.WARNING);

    	}

	}

    public void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType) {

  		Alert alert = new Alert(alertType);
  		alert.setTitle(titulo);
  		alert.setHeaderText(header);
  		alert.setContentText(contenido);
  		alert.showAndWait();
  	}

  	private boolean datosValidos(String usuario) {
  		if(usuario.equals("")){
  			return false;
  		}
  		return true;
  	}


    @FXML
    void regresarEvent(MouseEvent event) {
    	regresar.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->{
    		aplicacion.mostrarVentanaLoginAdmin();
    	});
    }


	private Aplicacion aplicacion;

	@FXML
    void initialize() {
		modelFactoryController = ModelFactoryController.getInstance();

    }

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;

	}

}
