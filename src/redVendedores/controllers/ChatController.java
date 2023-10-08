package redVendedores.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import redVendedores.aplication.Aplicacion;

public class ChatController {
	
	@FXML
    private TextField campoMensaje;

    @FXML
    private ImageView enviar;

    @FXML
    private ImageView camara;
    
    private Aplicacion aplicacion;


    @FXML
    void enviarEvent(ActionEvent event) {

    }

    @FXML
    void camaraEvent(ActionEvent event) {

    }
    
    public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;

	}

}
