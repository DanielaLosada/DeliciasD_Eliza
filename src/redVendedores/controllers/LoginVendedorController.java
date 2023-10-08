package redVendedores.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import redVendedores.aplication.Aplicacion;

public class LoginVendedorController {
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtNumDocumento;

    @FXML
    private ImageView flechaRegresar;

    @FXML
    private Button btnIngresar;

    @FXML
    private PasswordField txtContrasenia;

    private Aplicacion aplicacion;

    @FXML
    private Label olvideContrasena;
    
    ModelFactoryController modelFactoryController; 

    @FXML
    void flechaRegresarEvent(MouseEvent event) {
    	flechaRegresar.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->{
    		aplicacion.mostrarVentanaIniciarRed();
    	});

    }

    @FXML
    void olvideContrasenaEvent(MouseEvent event) {
    	olvideContrasena.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->{
    		aplicacion.mostrarVentanaRestablecerContrasena();
    	});
    }
    
    @FXML
    void mouseEvent(MouseEvent event) {
    	olvideContrasena.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent ->{
    		olvideContrasena.setStyle("-fx-text-fill: #0676b7;");
        	});
    }

    @FXML
    void mouseEvent2(MouseEvent event) {
    	
    	olvideContrasena.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent ->{
    		olvideContrasena.setStyle("-fx-text-fill: #fd7b7b;");
    	});
    }


    @FXML
    void ingresarEvent(ActionEvent event) {
    	ingresarAction();
    }

    private void ingresarAction() {

    	String documento = "";
    	String contrasenia = "";

    	documento = txtNumDocumento.getText();
    	contrasenia = txtContrasenia.getText();

    	if(datosValidos(documento,contrasenia)){
    		boolean documentoValido = modelFactoryController.verificarDocumento(documento,contrasenia);
    		if(documentoValido){
    			aplicacion.mostrarVentanaPrincipal(documento);
    		}else{
    			mostrarMensaje("Notificacinn Inicio sesion", "Usuario no existe", "Los datos ingresados no corresponde a un usuario valido", AlertType.INFORMATION);

    		}
    	}else{
			mostrarMensaje("Notificacinn Inicio sesion", "Datos Incompletos", "Debe ingresar los datos correctamente, despues de 3 intentos se bloqueara el usuario", AlertType.WARNING);

    	}

	}
    public void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType) {

		Alert alert = new Alert(alertType);
		alert.setTitle(titulo);
		alert.setHeaderText(header);
		alert.setContentText(contenido);
		alert.showAndWait();
	}

	private boolean datosValidos(String usuario, String contrasenia) {
		if(usuario.equals("") || contrasenia.equals("")){
			return false;
		}
		return true;
	}

	@FXML
    void initialize() {
		modelFactoryController = ModelFactoryController.getInstance();
		

    }

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;

	}

}
