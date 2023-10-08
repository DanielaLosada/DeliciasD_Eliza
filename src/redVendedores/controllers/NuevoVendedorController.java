package redVendedores.controllers;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import redVendedores.aplication.Aplicacion;
import redVendedores.exceptions.VendedorException;
import redVendedores.model.Vendedor;

public class NuevoVendedorController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtDocumento;
    
    @FXML
    private TextField txtCedula;

    @FXML
    private Button btnRegistrarse;

    @FXML
    private TextField txtContrasenia;

    private Aplicacion aplicacion;

    @FXML
    private ImageView flechaRegresar;
    
    ModelFactoryController modelFactoryController; 

    @FXML
    void registrarseEvent(ActionEvent event) {
    	registrarseAction();
    }
    
    @FXML
    void mouseEvent(MouseEvent event) {
    	btnRegistrarse.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent ->{
    		btnRegistrarse.setStyle("-fx-background-color: #999999;");
        	});
    }

    @FXML
    void mouseEvent2(MouseEvent event) {
    	
    	btnRegistrarse.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent ->{
    		btnRegistrarse.setStyle("-fx-background-color #0676b7;");
    	});
    }

    private void registrarseAction() {
    	String nombre = txtNombre.getText();
    	String apellido = txtApellido.getText();
    	String documento = txtDocumento.getText();
    	String direccion = txtDireccion.getText();
    	String contrasenia= txtContrasenia.getText();
    	
    	if(datosValidos(nombre, apellido, documento, direccion, contrasenia) == true){

    		Vendedor vendedor = null;
    		try {
				vendedor=modelFactoryController.crearVendedor(nombre, apellido, documento, direccion, contrasenia);
				mostrarMensaje("Notificaci�n registro", "Vendedor registrado", "Se ha registrado con exito en Shopify", AlertType.INFORMATION);
			 	aplicacion.mostrarVentanaLoginVendedor();
			} catch (VendedorException e) {

				mostrarMensaje("Notificaci�n registro", "Vendedor no registrado", "Ya se ha registrado un vendedor con n� identificacion"+documento+".", AlertType.ERROR);
			}
    	}
    		else{
    			mostrarMensaje("Notificaci�n registro", "Informacion invalida", "Informacion invalida", AlertType.ERROR);

    		}
    	}
		
	private boolean datosValidos(String nombre, String apellido, String documento, String direccion,
			String contrasenia) {
		if(nombre.equals("")){
			return false;
		}
		if(apellido.equals("")){
			return false;
		}
		if(documento.equals("")){
			return false;
		}
		if(direccion.equals("")){
			return false;
		}
		if(contrasenia.equals("")){
			return false;
		}
		
		return true;
		
	}

	@FXML
    void flechaRegresarEvent(MouseEvent event) {
    	flechaRegresar.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->{
    		aplicacion.mostrarVentanaIniciarRed();
    	});


    }

    @FXML
    void initialize() {

    	modelFactoryController = ModelFactoryController.getInstance();
    }

    public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;

	}
    
    private boolean mostrarMensajeConfirmacion(String mensaje) {

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirmaci�n");
		alert.setHeaderText(null);
		alert.setContentText(mensaje);

		Optional<ButtonType> action = alert.showAndWait();

		if(action.get() == ButtonType.OK){
			return true;
		}else{
			return false;
		}
	}

	public void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType) {

    	Alert alert = new Alert(alertType);
    	alert.setTitle(titulo);
    	alert.setHeaderText(header);
    	alert.setContentText(contenido);
    	alert.showAndWait();
	}
}