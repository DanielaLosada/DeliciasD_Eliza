package redVendedores.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import org.controlsfx.control.textfield.TextFields;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import redVendedores.aplication.Aplicacion;
import redVendedores.exceptions.ProductoException;
import redVendedores.model.Comentario;
import redVendedores.model.MyListener;
import redVendedores.model.Producto;
import redVendedores.model.Vendedor;

public class ReedController {


	 @FXML
	    private TableColumn<Vendedor, String> columnNombre;

	    @FXML
	    private TableView<Vendedor> tableContactos;

	    Vendedor contactoSeleccionado;

	    ObservableList<Vendedor> listaAmigosData = FXCollections.observableArrayList();

	    ObservableList<Producto> listaProductosData = FXCollections.observableArrayList();

	    Producto productoSeleccionado;
	    
	    ObservableList<Producto> listaProductosTopData = FXCollections.observableArrayList();
	    
	    Producto productoSeleccionado2;


	    private Aplicacion aplicacion;

	    @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private Label cerrarSesion;

	    @FXML
	    private ImageView iconEliminarProducto;

	    @FXML
	    private Label actualizarProducto;


	    @FXML
	    private Label nombreVendedor;

	    @FXML
	    private Label crearProducto;

	    @FXML
	    private Label eliminarProducto;

	    @FXML
	    private ImageView iconActualizarProducto;

	    @FXML
	    private ImageView notificaciones;

	    @FXML
	    private ImageView iconCrearProducto;

	    @FXML
	    private ImageView usuario;


	    @FXML
	    private ImageView iconCerrarS;

	    @FXML
	    private Label fechaHora;

	    @FXML
	    private TextField txtCategoria;

	    @FXML
	    private TableColumn<Producto, String> columnNombreProductos;
	    
	    @FXML
	    private TableView<Producto> tableProductos;
	    
	    @FXML
	    private TableColumn<Vendedor, String> columnVendedor;
	    
	    @FXML
	    private TableColumn<Producto, String> columnProducto;
	    

	    @FXML
	    private TextField txtCodigo;

	    @FXML
	    private TextField txtPrecio;

	    @FXML
	    private TextField txtEstado;
	    
	    @FXML
	    private Label txtFecha;

	    @FXML
	    private ImageView imagenProducto;

	    @FXML
	    private TextField txtNombreProducto;

	    @FXML
	    private ImageView iconComentarios;

	    @FXML
	    private ImageView iconMeGusta;

	    private String documento;

	    @FXML
	    private TextField txtBuscarVendedor;
	    
	    @FXML
	    private ScrollPane scroll;
	    
	    @FXML
	    private GridPane grid;
	    
	    @FXML
	    private TableColumn<Vendedor, String> columnSolicitudes;
	    
	    @FXML
	    private TableView<Vendedor> tableSolicitudes;
	    
	    ObservableList<Vendedor> listaSolicitudesData = FXCollections.observableArrayList();

	    Vendedor solicitudSeleccionada;
	    
	    ModelFactoryController modelFactoryController; 
	    

	    Calendar fechahoy = Calendar.getInstance();

	    String dt = "" + fechahoy.get(Calendar.DAY_OF_MONTH) + "" + fechahoy.get(Calendar.MONTH);
	    
	    private ArrayList<Producto> productos = new ArrayList<>();
	    private MyListener myListener;

//	    private List<Producto> getData() {
//	        List<Producto> productos = new ArrayList<>();
////	        Producto producto;
//	        //productos= getListaProductosData(documento);
////	        productos.addAll(listaProductosData);
////	        producto = new Producto();
////	        producto.setNombre("Zapato");
////	        producto.setPrecio("$200");
////	        URL ruta2= getClass().getResource("/imagenes/Nike.jpg");
////			producto.setImagen(ruta2.getPath().substring(1));
////	        productos.add(producto);
////	        
////	        producto = new Producto();
////	        producto.setNombre("camisa");
////	        producto.setPrecio("$200");
////	        URL ruta3= getClass().getResource("/imagenes/Vans.jpg");
////			producto.setImagen(ruta3.getPath().substring(1));
////	        productos.add(producto);
////	        
////	        producto = new Producto();
////	        producto.setNombre("pantalon");
////	        producto.setPrecio("$200");
////	        URL ruta4= getClass().getResource("/imagenes/collar.jpg");
////			producto.setImagen(ruta4.getPath().substring(1));
////	        productos.add(producto);
//
//	  
//	        return productos;
//	    }

	    private void setChosenProducto(Producto producto) {
	        txtNombreProducto.setText(producto.getNombre());
	        txtCodigo.setText(producto.getCodigo());
	        txtPrecio.setText(producto.getPrecio());
	        txtEstado.setText(producto.getEstado());
	        txtCategoria.setText(producto.getCategoria());
	        txtFecha.setText(producto.getFechaHora());
	        Image imagen2= new Image("file:/"+producto.getImagen());
	        imagenProducto.setImage(imagen2);
//	        chosenFruitCard.setStyle("-fx-background-color: #" + fruit.getColor() + ";\n" +
//	                "    -fx-background-radius: 30;");
	    }




	    @FXML
	    void notificacionesEvent(MouseEvent event) {
	    	notificaciones.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->{
	    		JOptionPane.showMessageDialog(null, "Puede que tengas me gustas y comentarios nuevos en tus productos", null, JOptionPane.INFORMATION_MESSAGE);
	    	});

	    }

	    @FXML
	    void usuarioEvent(MouseEvent event) {
	    	usuario.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->{
	    		JOptionPane.showMessageDialog(null, "Se encuentra en linea el vendedor"+nombreVendedor.getText()+"con N� documento"+documento+"",null, JOptionPane.INFORMATION_MESSAGE);
	    	});

	    }

	    @FXML
	    void crearProductoEvent(MouseEvent event) {
	    	crearProducto.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->{
	    		crearProductoAction();
	    		limpiarDatos();
	    	});


	    }

	    @FXML
	    void actualizarProductoEvent(MouseEvent event) {
	    	actualizarProducto.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->{
	    		actualizarProductoAction();
	    		limpiarDatos();
	    	});

	    }

	    @FXML
	    void eliminarProductoEvent(MouseEvent event) {
	    	eliminarProducto.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->{
	    		eliminarProductoAction();
	    	});

	    }

	    @FXML
	    void cerrarSesionEvent(MouseEvent event) {
	    	cerrarSesion.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->{
	    		aplicacion.mostrarVentanaIniciarRed();
	    	});

	    }

	    @FXML
	    void crearProducto2Event(MouseEvent event) {
	    	iconCrearProducto.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->{
	    		crearProductoAction();
	    		limpiarDatos();
	    	});


	    }

	    @FXML
	    void actualizarProducto2Event(MouseEvent event) {
	    	iconActualizarProducto.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->{
	    		actualizarProductoAction();
	    		limpiarDatos();
	    	});


	    }

	    @FXML
	    void eliminarProducto2Event(MouseEvent event) {
	    	iconEliminarProducto.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->{
	    		eliminarProductoAction();
	    	});

	    }

	    @FXML
	    void cerrarSesion2Event(MouseEvent event) {
	    	iconCerrarS.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->{
	    		aplicacion.mostrarVentanaIniciarRed();
	    	});
	    }

	    @FXML
	    void meGustaEvent(MouseEvent event) {
	    	iconMeGusta.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->{
	    		obtenerNumeroMeGustas(productoSeleccionado, documento);
	    	});
	    }

	    @FXML
	    void comentariosEvent(MouseEvent event) {
	    	iconComentarios.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->{
	    		obtenerComentarios(productoSeleccionado, documento);
	    	});

	    }


		@FXML
	    void buscarEvent(KeyEvent event) {
	    	txtBuscarVendedor.addEventHandler(KeyEvent.KEY_RELEASED, keyEvent ->{
               if(event.getCode() == KeyCode.ENTER){
               	obtenerNombreAmigo(documento);
               }
               
	    	});
	    }


	    private void crearProductoAction() {
			String nombre = txtNombreProducto.getText();
			String codigo= txtCodigo.getText();
			String precio= txtPrecio.getText();
			String estado = txtEstado.getText();
			String categoria= txtCategoria.getText();
			String fechaAux = fechaHora.getText();


			if(datosValidos(nombre, codigo, precio, estado, categoria) == true){

				Producto producto = null;
				try {
					producto = modelFactoryController.crearProducto(nombre, codigo, precio, estado, categoria, fechaAux, documento );
					listaProductosData.add(producto);

					mostrarMensaje("Notificaci�n Vendedor", "Producto creado", "El producto se ha creado con �xito", AlertType.INFORMATION);

				} catch (ProductoException e) {
					mostrarMensaje("Notificaci�n Vendedor", "Producto no creado", "El producto con codigo "+codigo+" ya se encuentra creado dentro de su lista de productos", AlertType.ERROR);
				}
			}
			else{
				mostrarMensaje("Notificaci�n Vendedor", "Informacion invalida", "Informacion invalida", AlertType.ERROR);

			}
		}

	    private void actualizarProductoAction() {

	    	String nombre = txtNombreProducto.getText();
			String codigo= txtCodigo.getText();
			String precio= txtPrecio.getText();
			String estado = txtEstado.getText();
			String categoria= txtCategoria.getText();
			String fechaAux= fechaHora.getText();

			if(productoSeleccionado == null){
				mostrarMensaje("Notificaci�n Vendedor", "Seleccion producto", "Debe seleccionar un producto", AlertType.ERROR);
			}else{
				if(datosValidos(nombre,codigo, precio, estado, categoria ) == true){
					modelFactoryController.actualizarProducto(productoSeleccionado.getCodigo(),nombre, codigo, precio, estado, categoria, fechaAux, documento);
					tableProductos.refresh();
				}else{
					mostrarMensaje("Notificaci�n vendedor", "producto no actualizado", "Datos invalidos", AlertType.ERROR);
				}

			}
		}

	    private void eliminarProductoAction() {

			if(productoSeleccionado == null){
				mostrarMensaje("Notificaci�n Vendedor", "Seleccion producto", "Debe seleccionar un producto", AlertType.ERROR);
			}else{
				boolean eliminado = modelFactoryController.eliminarProducto(productoSeleccionado.getCodigo(), documento);
				if(eliminado){
					listaProductosData.remove(productoSeleccionado);
					productoSeleccionado = null;
					limpiarDatos();
					tableProductos.refresh();
					tableProductos.getSelectionModel().clearSelection();
					mostrarMensaje("Notificaci�n Vendedor", "Producto eliminado", "El producto ha sido eliminado de su lista", AlertType.ERROR);
				}else{
					mostrarMensaje("Notificaci�n vendedor", "Producto no eliminado", "El producto No ha sido eliminado", AlertType.ERROR);

				}
			}

		}

	public void setAplicacion(Aplicacion aplicacion, String documento) {
		this.aplicacion = aplicacion;
		tableContactos.getItems().clear();
		tableContactos.setItems(getListaAmigosData(documento));
		this.documento= documento;
		obtenerNombreVendedor(documento);

		tableProductos.getItems().clear();
		tableProductos.setItems(getListaProductosTopData());
//		obtenerFecha();
		obtenerNombreVendedores(documento);
//		getListaProductosData(documento);
		productos.addAll(getListaProductosData(documento));
		System.out.println(productos);
		if (productos.size() > 0) {
	        setChosenProducto(productos.get(0));
	        myListener = new MyListener() {
	            @Override
	            public void onClickListener(Producto producto) {
	                setChosenProducto(producto);
	            }
	        };
	        
	        grid.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
	            Object source = event.getTarget();
	            if (source instanceof Producto) {
	                Producto clickedRectangle = (Producto) source;

	                if (productoSeleccionado != null) {
	                    System.out.println("Lo cogio");// Restaurar el color anterior del objeto previamente seleccionado
	                }

	               // Cambiar el color del objeto seleccionado
	                productoSeleccionado = clickedRectangle;
	            }
	        });

	    }
	    int column = 0;
	    int row = 1;
	    //grid= new GridPane();
	    try {
	        for (int i = 0; i < productos.size(); i++) {
	            FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Aplicacion.class.getResource("../views/Producto.fxml"));

				AnchorPane rootLayout = (AnchorPane)loader.load();

	            ProductoController itemController = loader.getController();
	            itemController.setData(productos.get(i),myListener);

	            if (column == 2) {
	                column = 0;
	                row++;
	            }

	            grid.add(rootLayout, column++, row); //(child,column,row)
	            //set grid width
	            grid.setMinWidth(Region.USE_COMPUTED_SIZE);
	            grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
	            grid.setMaxWidth(Region.USE_PREF_SIZE);

	            //set grid height
	            grid.setMinHeight(Region.USE_COMPUTED_SIZE);
	            grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
	            grid.setMaxHeight(Region.USE_PREF_SIZE);

	            GridPane.setMargin(rootLayout, new Insets(10));
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
//		tableTop10.getItems().clear();
//		tableTop10.setItems(getListaProductosTopData());
//		
		tableSolicitudes.getItems().clear();
		tableSolicitudes.setItems(getListaSolicitudesData(documento));
		

	}

	private ObservableList<Vendedor> getListaAmigosData(String documento){
		listaAmigosData.addAll(modelFactoryController.obtenerAmigos(documento));
		return listaAmigosData;

	}
	
	private ObservableList <Vendedor> getListaSolicitudesData(String documento){
		listaSolicitudesData.addAll(modelFactoryController.obtenerSolicitudes(documento));
		tableSolicitudes.refresh();
		return listaSolicitudesData;

	}

	private ArrayList <Producto> getListaProductosData(String documento){
		
		return modelFactoryController.obtenerProductos(documento);

	}
	
	private ObservableList <Producto> getListaProductosTopData(){
		listaProductosTopData.addAll(modelFactoryController.obtenerProductosTop());
		tableProductos.refresh();
		return listaProductosTopData;

	}


	@FXML
   void initialize() {
		modelFactoryController = ModelFactoryController.getInstance();
//	    productos.addAll(getListaProductosData(documento));
//	    if (productos.size() > 0) {
//	        setChosenProducto(productos.get(0));
//	        myListener = new MyListener() {
//	            @Override
//	            public void onClickListener(Producto producto) {
//	                setChosenProducto(producto);
//	            }
//	        };
//	    }
//	    int column = 0;
//	    int row = 1;
//	    //grid= new GridPane();
//	    try {
//	        for (int i = 0; i < productos.size(); i++) {
//	            FXMLLoader loader = new FXMLLoader();
//				loader.setLocation(Aplicacion.class.getResource("../views/Producto.fxml"));
//
//				AnchorPane rootLayout = (AnchorPane)loader.load();
//
//	            ProductoController itemController = loader.getController();
//	            itemController.setData(productos.get(i),myListener);
//
//	            if (column == 2) {
//	                column = 0;
//	                row++;
//	            }
//
//	            grid.add(rootLayout, column++, row); //(child,column,row)
//	            //set grid width
//	            grid.setMinWidth(Region.USE_COMPUTED_SIZE);
//	            grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
//	            grid.setMaxWidth(Region.USE_PREF_SIZE);
//
//	            //set grid height
//	            grid.setMinHeight(Region.USE_COMPUTED_SIZE);
//	            grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
//	            grid.setMaxHeight(Region.USE_PREF_SIZE);
//
//	            GridPane.setMargin(rootLayout, new Insets(10));
//	        }
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	    }
	    
   	this.columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

   	tableContactos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

   		contactoSeleccionado = newSelection;

   		mostrarInformacionContacto(contactoSeleccionado, documento);

   	});

//   	this.columnNombreProductos.setCellValueFactory(new PropertyValueFactory<>("nombre"));
//
//   	tableProductos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
//
//   		productoSeleccionado = newSelection;
//   		mostrarInformacionProducto(productoSeleccionado);
//
//   	});
   	grid.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
        Object source = event.getTarget();
        if (source instanceof Producto) {
            Producto clickedRectangle = (Producto) source;

            if (productoSeleccionado != null) {
                System.out.println("Lo cogio");// Restaurar el color anterior del objeto previamente seleccionado
            }else{
            	System.out.println("No esta seleccionando");
            }

           // Cambiar el color del objeto seleccionado
            productoSeleccionado = clickedRectangle;
        }
    });
   	this.columnProducto.setCellValueFactory(new PropertyValueFactory<>("nombre"));
   	
   	this.columnSolicitudes.setCellValueFactory(new PropertyValueFactory<>("nombre"));

   	tableSolicitudes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

   		solicitudSeleccionada = newSelection;
   		mostrarOpcionSolicitud(solicitudSeleccionada, documento);

   	});
   	
   	

   }

	
	private void mostrarOpcionSolicitud(Vendedor solicitudSeleccionada, String documento) {
		String nombreSolicitud= solicitudSeleccionada.getNombre()+" "+solicitudSeleccionada.getApellidos();
		String [] botones = { "Confirmar", "Eliminar"};
		int variable = JOptionPane.showOptionDialog (null, "�Desea aceptar la solicitud de este vendedor?", "Solicitudes de amistad", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, botones, botones[0]);
		switch(variable){
			case 0: 
				boolean aceptado= modelFactoryController.aceptarSolicitud(nombreSolicitud, documento);
				if(aceptado){
					listaSolicitudesData.remove(solicitudSeleccionada);
					tableSolicitudes.refresh();
					tableSolicitudes.getSelectionModel().clearSelection();
					listaAmigosData.add(solicitudSeleccionada);
					tableContactos.refresh();
					tableSolicitudes.getSelectionModel().clearSelection();
					mostrarMensaje("Notificaci�n Solicitud", "Solicitud aceptada", "El vendedor ahora se encuentra en su lista de amigos", AlertType.ERROR);
				}else{
					System.out.println("No entro");
				}
				break;
			case 1: 
				boolean eliminado= modelFactoryController.eliminarSolicitud(nombreSolicitud, documento);
				if(eliminado){
					listaSolicitudesData.remove(solicitudSeleccionada);
					solicitudSeleccionada= null;
					tableSolicitudes.refresh();
					tableSolicitudes.getSelectionModel().clearSelection();
					mostrarMensaje("Notificaci�n Solicitud", "Solicitud eliminada", "Se ha eliminado la solicitud correctamente", AlertType.ERROR);
					break; 
				}
		}
	}
	
	private void obtenerNombreVendedores(String documento){
		ArrayList<String> possibleWords= modelFactoryController.obtenerVendedoresNombre(documento);

   	TextFields.bindAutoCompletion(txtBuscarVendedor, possibleWords);

	}

	 
	private void obtenerNumeroMeGustas(Producto productoSeleccionado, String documento){
		String codigo= productoSeleccionado.getCodigo();
		String numeroMeGusta= modelFactoryController.obtenerMeGustas2(codigo, documento);
		JOptionPane.showMessageDialog(null, "El producto tiene: "+numeroMeGusta+" me gustas");
	}
	
	private void obtenerComentarios(Producto productoSeleccionado2, String documento2) {
		String codigo= productoSeleccionado.getCodigo();
		ArrayList<Comentario> numeroMeGusta= modelFactoryController.obtenerComentarios(codigo, documento);
		JOptionPane.showMessageDialog(null, numeroMeGusta, "Comentarios del producto", JOptionPane.PLAIN_MESSAGE);
	}
		
	


	private void mostrarInformacionProducto(Producto productoSeleccionado) {
		txtNombreProducto.setText(productoSeleccionado.getNombre());
		txtCodigo.setText(productoSeleccionado.getCodigo());
		txtPrecio.setText(productoSeleccionado.getPrecio());
		txtCategoria.setText(productoSeleccionado.getCategoria());
		txtEstado.setText(productoSeleccionado.getEstado());
		Image imagen= new Image("file:/"+productoSeleccionado.getImagen());
		imagenProducto.setImage(imagen);

	}

	private void mostrarInformacionContacto(Vendedor contactoSeleccionado, String documeto) {
		String nombre= contactoSeleccionado.getNombre()+" "+contactoSeleccionado.getApellidos();
		aplicacion.mostrarVentanaAmigo(documento, nombre);
	}

	private void obtenerNombreVendedor(String documento){
		String nombre= modelFactoryController.obtenerNombre(documento);
		nombreVendedor.setText(nombre);

	}

//	private void obtenerFecha(){
//		Calendar fechahoy = Calendar.getInstance();
//		String dt = "" + fechahoy.get(Calendar.DAY_OF_MONTH) + "" + fechahoy.get(Calendar.MONTH);
//		fechaHora.setText(dt);
//
//	}
	
	private void obtenerNombreAmigo(String documento){
		String nombre= txtBuscarVendedor.getText(); 
		if(datoValido(nombre)== true){
			if(modelFactoryController.obtenerInformacion(nombre)==true){
				aplicacion.mostrarVentanaAmigo(documento, nombre);
			}else{
				mostrarMensaje("Notificaci�n Busqueda", "datos invalidos", "El nombre que escribi� no esta asociado a ningun vendedor registrado en DAX", AlertType.ERROR);
				
			}
		}
		else{
			mostrarMensaje("Notificaci�n Busqueda", "datos invalidos", "Debe escribir un nombre para poder buscar un vendedor en DAX", AlertType.ERROR);
		}
	}

	private boolean datosValidos(String nombre, String codigo, String precio, String estado,
			String categoria) {
		if(nombre.equals("")){
			return false;
		}
		if(codigo.equals("")){
			return false;
		}
		if(precio.equals("")){
			return false;
		}
		if(estado.equals("")){
			return false;
		}
		if(categoria.equals("")){
			return false;
		}

		return true;

	}
	
	private boolean datoValido(String nombre){
		if(nombre.equals("")){
			return false;
		}
		return true;
		
	}

	private void limpiarDatos() {
		txtNombreProducto.setText("");
		txtCodigo.setText("");
		txtPrecio.setText("");
		txtCategoria.setText("");
		txtEstado.setText("");
		fechaHora.setText("");

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
