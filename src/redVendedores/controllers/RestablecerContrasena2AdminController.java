package redVendedores.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import redVendedores.aplication.Aplicacion;

public class RestablecerContrasena2AdminController {
	 @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private Label txtCodigoVerificacion;

	    @FXML
	    private Button btnRestablecer;

	    @FXML
	    private PasswordField txtContraseniaAgain;

	    @FXML
	    private Label ingresa;

	    @FXML
	    private TextField txtCodigoIngresado;

	    @FXML
	    private PasswordField txtNuevaContrasenia;

	    @FXML
	    private Button btnVerificar;

	    private String documento;

	    private Aplicacion aplicacion;
	    
	    ModelFactoryController modelFactoryController; 

	    @FXML
	    void verificarCodigoEvent(ActionEvent event) {
	    	verificarCodigo();
	    }

	    @FXML
	    void restablecerContrasenaFinalEvent(ActionEvent event) {
	    	verificarCodigo();
	    }

	    @FXML
	    void ingresaEvent(MouseEvent event) {
	    	ingresa.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->{
	    		aplicacion.mostrarVentanaLoginAdmin();
	    	});
	    }

	@FXML
 void initialize() {
		modelFactoryController = ModelFactoryController.getInstance();
		//this.txtCodigoVerificacion=cadenaAleatoria();
 }

	public void setAplicacion(Aplicacion aplicacion, String documento) {
		this.aplicacion = aplicacion;
		this.documento= documento;
	}

//	private Label cadenaAleatoria() {
//     String banco = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
//     String cadenaAux= "";
//     for (int x = 0; x < 8; x++) {
//         int indiceAleatorio = numeroAleatorioEnRango(0, banco.length() - 1);
//         char caracterAleatorio = banco.charAt(indiceAleatorio);
//         cadenaAux+=caracterAleatorio;
//         txtCodigoVerificacion.setText(cadenaAux);
//     }
//     return txtCodigoVerificacion;
//
// }
//	private int numeroAleatorioEnRango(int minimo, int maximo) {
//     return ThreadLocalRandom.current().nextInt(minimo, maximo + 1);
// }

	private void verificarCodigo(){
		String codigoGenerado = txtCodigoVerificacion.getText();
		String codigoIngresado = txtCodigoIngresado.getText();

 		if(datosValidos(codigoGenerado, codigoIngresado) == true){
     		if(codigoGenerado.equals(codigoIngresado)){
     			mostrarMensaje("Notificación codigo", "Codigo correcto", "El codigo fue ingresado correctamente, restablezca su contraseña", AlertType.INFORMATION);
     			actualizarContrasenia();
     		}else{
     			mostrarMensaje("Notificación codigo", "Codigo incorrecto", "El codigo ingresado es incorrecto, vuelva a escribirlo", AlertType.ERROR);
     		}
     	}else{
     		mostrarMensaje("Notificación codigo", "Datos Incompletos", "Debe ingresar el codigo para poder restablecer su contraseña, despues de 3 intentos se bloqueara el usuario", AlertType.WARNING);
     	}
 }

	private void actualizarContrasenia(){
		String nuevaContrasenia = txtNuevaContrasenia.getText();
		String contraseniaAgain = txtContraseniaAgain.getText();
		String identificacion = documento;

			if(datosValidos(nuevaContrasenia, contraseniaAgain)){
				if(nuevaContrasenia.equals(contraseniaAgain)){
					modelFactoryController.actualizarContraseniaAdmin(identificacion, nuevaContrasenia);
					mostrarMensaje("Notificación contraseña", "Contraseña correcta", "Su contraseña fue bien escrita, se ha actualizado correctamente", AlertType.INFORMATION);
				}else{
					mostrarMensaje("Notificación contraseña", "Contraseña incorrecto", "Las contraseñas no son iguales, vuelva a escribirlas", AlertType.ERROR);
				}
			}else{
				mostrarMensaje("Notificación contraseña", "Datos Incompletos", "Debe ingresar la nueva contraseña para poder restablecerla, despues de 3 intentos se bloqueara el usuario", AlertType.WARNING);
			}
	}


	public void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType) {

		Alert alert = new Alert(alertType);
		alert.setTitle(titulo);
		alert.setHeaderText(header);
		alert.setContentText(contenido);
		alert.showAndWait();
	}

	private boolean datosValidos(String codigoGenerado, String codigoIngresado) {
		if(codigoGenerado.equals("") || codigoIngresado.equals("")){
			return false;
		}
		return true;
	}







}
