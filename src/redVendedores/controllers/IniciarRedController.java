package redVendedores.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import redVendedores.aplication.Aplicacion;

public class IniciarRedController {

	private Aplicacion aplicacion;

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnSoyAdmin;

    @FXML
    private Label registrateAqui;

    @FXML
    private Button btnSoyVendedor;

    @FXML
    void ingresarAdminEvent(ActionEvent event) {
    	aplicacion.mostrarVentanaLoginAdmin();
    }

    @FXML
    void ingresarVendedorEvent(ActionEvent event) {
    	aplicacion.mostrarVentanaLoginVendedor();
    }

    @FXML
    void registrateEvent(MouseEvent event) {
    	registrateAqui.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->{
    		aplicacion.mostrarVentanaNuevoVendedor();
    	});

    }



    public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;

	}

}
