package redVendedores.controllers;

import java.io.IOException;
import java.util.ArrayList;
import redVendedores.exceptions.ComentarioException;
import redVendedores.exceptions.ProductoException;
import redVendedores.exceptions.VendedorException;
import redVendedores.model.Comentario;
import redVendedores.model.Producto;
import redVendedores.model.Red;
import redVendedores.model.Vendedor;
import redVendedores.persistencia.Persistencia;
import redVendedores.server.AppCliente;


public class ModelFactoryController {
	
	Red red;
	
	AppCliente cliente;


	//------------------------------  Singleton ------------------------------------------------
	// Clase estatica oculta. Tan solo se instanciara el singleton una vez
	private static class SingletonHolder {
		// El constructor de Singleton puede ser llamado desde aqu� al ser protected
		private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
	}

	// M�todo para obtener la instancia de nuestra clase
	public static ModelFactoryController getInstance() {
		return SingletonHolder.eINSTANCE;
	}

	public ModelFactoryController() {
		//1. inicializar datos y luego guardarlo en archivos
				//iniciarSalvarDatosPrueba();

				//2. Cargar los datos de los archivos
				//cargarDatosDesdeArchivos();
		
				//3. Guardar y Cargar el recurso serializable binario
				//guardarResourceBinario();
				//cargarResourceBinario();

				//4. Guardar y Cargar el recurso serializable XML

				//cargarResourceXML();
				
				cliente= new AppCliente("localhost",2026);
		    	cliente.iniciarCliente();
		    	try {
					red = cliente.recibirObjeto();
					guardarResourceXML();
					guardarResourceBinario();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	
				//Siempre se debe verificar si la raiz del recurso es null
				if(red == null)
				{
					System.out.println("es null la red");
					//iniciarSalvarDatosPrueba();
					//crearCopias();
//					guardarResourceSerializable();
					guardarResourceXML();
					guardarResourceBinario();

				}
	}
	
	
	private void iniciarSalvarDatosPrueba() {
		System.out.println("SalvarDatos");
		//inicializarDatos();
			try {
				red= cliente.recibirObjeto();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		try {

			Persistencia.guardarVendedores(getRed().getListaVendedores());
			Persistencia.guardarAdministrador(getRed().getAdministrador());
			Persistencia.guardarProductos(getRed().getListaVendedores());
			
			Persistencia.guardarRecursoBancoBinario(red);
			Persistencia.guardarRecursoBancoXML(red);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Se inicializaron los datos");
	}
	



	public void cargarResourceBinario() {

		red = Persistencia.cargarRecursoBancoBinario();
	}


	public void guardarResourceBinario() {

	    Persistencia.guardarRecursoBancoBinario(red);
	}


	public void cargarResourceXML() {

		red = Persistencia.cargarRecursoBancoXML();
	}


	public void guardarResourceXML() {

	    Persistencia.guardarRecursoBancoXML(red);
	}
	
	public void crearCopias(){
		try {
			Persistencia.crearCopias();
			System.out.println("Se guardaron copias");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Red getRed() {
		return red;
	}

	public void setRed(Red red) {
		this.red= red;
	}


	public Vendedor crearVendedor(String nombre, String apellido, String cedula, String direccion, String contrasenia) throws VendedorException {


		Vendedor vendedor = null;

		try {
			vendedor = getRed().crearVendedor(nombre, apellido, cedula, direccion, contrasenia);
			Persistencia.guardarVendedores(getRed().getListaVendedores());
			Persistencia.guardaRegistroLog("Se ha creado un nuevo vendedor en la red "+cedula, 1, "crear vendedor");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Persistencia.guardaRegistroLog("No se ha podido crear el vendedor", 2, "crear vendedor");
		}

		return vendedor;

	}

	public void actualizarVendedor(String documento, String nombre2, String apellido, String documento2,
			String direccion, String contrasenia) {

		 getRed().actualizarVendedor(documento, nombre2, apellido, documento2, direccion, contrasenia);
		 Persistencia.guardaRegistroLog("Se ha actualizado un vendedor "+documento, 1, "actualizar vendedor");
	}
	
	public Boolean eliminarVendedor(String cedula) throws VendedorException {

		boolean flagVendedorExiste = false;

		flagVendedorExiste = getRed().eliminarVendedor(cedula);
		if(flagVendedorExiste){
			Persistencia.guardaRegistroLog("Se ha eliminado un vendedor "+cedula, 1, "eliminar vendedor");
		}else{
			Persistencia.guardaRegistroLog("No se ha podido eliminar el vendedor"+cedula, 2, "eliminar vendedor");
		}
		return flagVendedorExiste;
	}

	

	public boolean verificarDocumento(String documento, String contrasenia) {
		if(red.verificarDocumento(documento, contrasenia)){
			Persistencia.guardaRegistroLog("Inicio de sesion del usuario: "+documento, 1, "inicio Sesion");
		}else{
			Persistencia.guardaRegistroLog("Error de sesion del usuario", 2, "inicio Sesion");
		}
		return red.verificarDocumento(documento,contrasenia);
	}

	public boolean verificarDocumento(String documento) {

		return red.existeVendedor(documento);
	}

	public boolean verificarAdmin(String documento, String contrasenia){
		if(red.verificarDocumento(documento, contrasenia)){
			Persistencia.guardaRegistroLog("Inicio de sesion del usuario: "+documento, 1, "inicio Sesion");
		}else{
			Persistencia.guardaRegistroLog("Error de sesion del usuario", 2, "inicio Sesion");
		}
		return red.verificarAdmin(documento, contrasenia);
	}

	public boolean verificarDocumentoAdmin(String documento) {
		return red.existeAdministrador(documento);
	}

	public void actualizarContrasenia(String nuevaContrasenia, String identificacion) {
		red.actualizarContrasenia(nuevaContrasenia, identificacion);
	}

	public void actualizarContraseniaAdmin(String nuevaContrasenia, String identificacion) {
		red.actualizarContraseniaAdmin(nuevaContrasenia, identificacion);

	}


	public ArrayList<Vendedor> obtenerVendedores() {
		return red.getListaVendedores();

	}

	public ArrayList<String> obtenerVendedoresNombre(String documento) {
		return red.obtenerNombresVendedores(documento);

	}

	public String obtenerNombre(String documento) {
		return red.obtenerNombreVendedor(documento);
	}

	public ArrayList<Producto> obtenerProductos(String documento) {
		return red.obtenerProductos(documento);
	}

	public Producto crearProducto(String nombre, String codigo, String precio, String estado, String categoria,
			String fechaAux, String documento) throws ProductoException {
		Persistencia.guardaRegistroLog("el vendedor"+documento+"ha creado un producto", 1, "crear producto");
		return red.crearProducto(nombre, codigo, precio, estado, categoria, fechaAux, documento);
	
		
	}

	public void actualizarProducto(String codigo, String nombre, String codigo2, String precio, String estado,
			String categoria, String fechaAux, String documento) {
		Persistencia.guardaRegistroLog("el vendedor"+documento+"ha actualizado un producto", 1, "actualizar producto");
		red.actualizarProducto(codigo, nombre, codigo2, precio, estado, categoria, fechaAux, documento);
		

	}

	public boolean eliminarProducto(String codigo, String documento) {
		Persistencia.guardaRegistroLog("el vendedor "+documento+" ha eliminado un producto", 1, "eliminar producto");
		if( red.eliminarProducto(codigo, documento) ) {
			Persistencia.guardarRecursoBancoXML(red);
			Persistencia.guardarRecursoBancoBinario(red);
			return true;
		}
		return false;
	}

	public ArrayList<Vendedor> obtenerAmigos(String documento) {
		return red.obtenerAmigos(documento);
	}

	public boolean obtenerInformacion(String nombre) {
		return red.obtenerInformacion(nombre);
	}

	public ArrayList<Producto> obtenerProductosAmigo(String nombre) {
		return red.obtenerProductosAmigos(nombre);
	}

	public boolean darMeGusta(String codigo, String nombre, String documento) {
		Persistencia.guardaRegistroLog("el vendedor"+documento+"le ha dado un me gusta a "+nombre, 1, "dar me gusta");
		return red.darMeGusta(codigo, nombre, documento);

	}

	public String obtenerMeGustas(String nombre, String codigo) {
		return red.obtenerNumeroMeGustas(nombre, codigo);
	}
	
	public String obtenerMeGustas2(String codigo, String documento) {
		return red.obtenerNumeroMeGustas2(codigo, documento);
	}

	public ArrayList<Producto> obtenerProductosTop() {
		return red.obtenerProductosTop();
	}

	public void escribirComentario(String codigo, String nombre) throws ComentarioException {
		red.escribirComentario(codigo, nombre);
		
	}

	public ArrayList<Comentario> obtenerComentarios(String codigo, String documento) {
		return red.obtenerComentarios(codigo, documento);
	}

	public boolean agregarAmigo(String nombre, String documento) {
		Persistencia.guardaRegistroLog("el vendedor"+documento+"le ha enviado una solicitud a "+nombre, 1, "agregar amigo");
		return red.agregarAmigo(nombre, documento);
	}

	public ArrayList<Vendedor> obtenerSolicitudes(String documento) {
		return red.obtenerSolicitudes(documento);
	}

	public boolean aceptarSolicitud(String nombreSolicitud, String documento) {
		Persistencia.guardaRegistroLog("el vendedor"+documento+"ahora es amigo de "+nombreSolicitud, 1, "aceptar solicitud");
		return red.aceptarSolicitud(nombreSolicitud, documento);
		
	}

	public boolean eliminarSolicitud(String documentoAmigoS, String documento) {
		Persistencia.guardaRegistroLog("el vendedor"+documento+"ha eliminado la solitud de amistad de "+documentoAmigoS, 1, "eliminar solicitud");
		return red.eliminarSolicitud(documentoAmigoS, documento);
		
	}
	

	

	

}
