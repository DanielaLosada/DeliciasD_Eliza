package redVendedores.controllers;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import redVendedores.aplication.Aplicacion;
import redVendedores.exceptions.ComentarioException;
import redVendedores.model.Producto;

public class VentanaAmigoController {

	private Aplicacion aplicacion;

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAgregarAmigo;

    @FXML
    private Label codigo;

    @FXML
    private TableColumn<Producto, String> columnNombreProducto;

    @FXML
    private Label nombreAmigo;

    @FXML
    private Label fechaHora;

    @FXML
    private Label categoria;

    @FXML
    private TableView<Producto> tableProductosAmigos;

    @FXML
    private Label nombreProducto;

    @FXML
    private ImageView comentario;

    @FXML
    private ImageView imagenProducto;

    @FXML
    private Label precio;

    @FXML
    private ImageView usuario;

    @FXML
    private ImageView meGusta;
    
    @FXML
    private ImageView chat;

    private String documento;
    
    private String nombre;
    
    @FXML
    private Label numeroMeGustas;
    
    ObservableList<Producto> listaProductosData = FXCollections.observableArrayList();

    Producto productoSeleccionado;
    
    ModelFactoryController modelFactoryController; 
    
    

    @FXML
    void usuarioEvent(MouseEvent event) {
    	usuario.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->{
    		JOptionPane.showMessageDialog(null, "Perfil del vendedor: "+nombre,null, JOptionPane.INFORMATION_MESSAGE);
    	});
    }

    @FXML
    void meGustaEvent(MouseEvent event) {
    	meGusta.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->{
    		darMeGusta(productoSeleccionado, nombre, documento);
    		
    	});
    }
    
    @FXML
    void chatEvent(MouseEvent event) {
    	chat.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->{
    		aplicacion.mostrarChat();
    	});
    }
    
    

    @FXML
    void ComentarioEvent(MouseEvent event) {
    	comentario.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->{
    		try {
				escribirComentario(productoSeleccionado, nombre);
			} catch (ComentarioException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	});

    }

    @FXML
    void agregarEvent(ActionEvent event) {
    	agregarAmigo(nombre, documento);
    }

	@FXML
    private ImageView devolver;
    
    @FXML
    void devolverEvent(MouseEvent event) {

    	devolver.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->{
    		aplicacion.mostrarVentanaPrincipal(documento);
    	});
    }
    
    
   

	@FXML
    void initialize() {
		modelFactoryController = ModelFactoryController.getInstance();
		this.columnNombreProducto.setCellValueFactory(new PropertyValueFactory<>("nombre"));

    	tableProductosAmigos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

    		productoSeleccionado = newSelection;
    		mostrarInformacionProducto(productoSeleccionado, nombre);
    		obtenerNumeroMeGustas(productoSeleccionado, nombre);
  
    	});
    	

    }
	
	private void agregarAmigo(String nombre, String documento) {
		if(modelFactoryController.agregarAmigo(nombre, documento)){
			mostrarMensaje("Notificaci�n Solicitud", "Solicitud enviada", "La solicitud se ha enviado correctamente", AlertType.ERROR);
		}else{
			mostrarMensaje("Notificaci�n Solicitud", "Solicitud erronea", "Ya envio una solicitud de amistad a este vendedor", AlertType.ERROR);
		}
	}

	
	private void  darMeGusta(Producto productoSeleccionado, String nombre, String documento) {
		String codigo= productoSeleccionado.getCodigo();
		if(modelFactoryController.darMeGusta(codigo, nombre, documento)){
			JOptionPane.showMessageDialog(null, "Le ha dado me gusta al producto");
		}else{
			JOptionPane.showMessageDialog(null, "Solo se le puede dar me gusta una vez al producto");
		}
	}
	
	private void escribirComentario(Producto productoSeleccionado, String nombre) throws ComentarioException {
		String codigo= productoSeleccionado.getCodigo();
		modelFactoryController.escribirComentario(codigo,nombre);
	}
	

	private void obtenerNumeroMeGustas(Producto productoSeleccionado, String nombre){
		String codigo= productoSeleccionado.getCodigo();
		String numeroMeGusta= modelFactoryController.obtenerMeGustas(nombre, codigo);
		numeroMeGustas.setText(numeroMeGusta);
	}


	private void mostrarInformacionProducto(Producto productoSeleccionado, String nombre) {
		nombreProducto.setText(productoSeleccionado.getNombre());
		codigo.setText(productoSeleccionado.getCodigo());
		precio.setText(productoSeleccionado.getPrecio());
		categoria.setText(productoSeleccionado.getCategoria());
		fechaHora.setText(productoSeleccionado.getFechaHora());
		Image imagen= new Image("file:/"+productoSeleccionado.getImagen());
		imagenProducto.setImage(imagen);
		
		
	}

	private ObservableList <Producto> getListaProductosData(String nombre){
		listaProductosData.addAll(modelFactoryController.obtenerProductosAmigo(nombre));
		return listaProductosData;
	}

    public void setAplicacion(Aplicacion aplicacion, String documento, String nombre) {
		this.aplicacion = aplicacion;
		this.documento= documento;
		this.nombre= nombre; 
		tableProductosAmigos.getItems().clear();
		tableProductosAmigos.setItems(getListaProductosData(nombre));
		nombreAmigo.setText(nombre);
		
	    
	}
    
    public void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType) {

    	Alert alert = new Alert(alertType);
    	alert.setTitle(titulo);
    	alert.setHeaderText(header);
    	alert.setContentText(contenido);
    	alert.showAndWait();
	}



}
