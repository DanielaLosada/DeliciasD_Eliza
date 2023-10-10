package redVendedores.model;

import java.io.Serializable;
import java.util.ArrayList;

import redVendedores.exceptions.ComentarioException;
import redVendedores.exceptions.MeGustaException;
import redVendedores.exceptions.ProductoException;
import redVendedores.exceptions.VendedorException;


public class Red implements Serializable{


	/*
	 * Atributos de la clase Red
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private ArrayList<Vendedor> listaVendedores;
	private Administrador administrador;

	/*
	 * Constructores de la clase Red
	 */

	public Red(String nombre) {
		super();
		this.nombre = nombre;
		listaVendedores = new ArrayList<Vendedor>();
		this.administrador = new Administrador();
	}

	public Red(){
		super();
		
	}



	/*
	 * Getters & setters de la clase Red
	 */

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}



	public ArrayList<Vendedor> getListaVendedores() {
		return listaVendedores;
	}

	public void setListaVendedores(ArrayList<Vendedor> listaVendedores) {
		this.listaVendedores = listaVendedores;
	}

	/*
	 * metodo equals de la clase Red
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Red other = (Red) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	/*
	 *toString de la clase Red
	 */

	@Override
	public String toString() {
		return "Biblioteca [nombre=" + nombre + "]";
	}

	//----------------------------------------------------

	/*
	 * Metodo que llama a la clase Vendedor para verificar si el documento (usuario) y la contrasenia son correctas
	 */

	public boolean verificarDocumento(String documento, String contrasenia) {
		for (Vendedor vendedor : listaVendedores) {
			if(vendedor.verificarDocumento(documento,contrasenia)){
				return true;
			}
		}
		return false;
	}

	/*
	 * Metodo que verifica si el documento (Usuario) y contrasenia ingresados por el administrador son correctos
	 */

	public boolean verificarAdmin(String documento, String contrasenia){
		if(administrador.getDocumento().equals(documento)&& administrador.getContrasenia().equals(contrasenia)){
			return true;
		}
		return false;
	}

	/*
	 * Metodo que permite crear un vendedor dentro de la red
	 */

	public Vendedor crearVendedor(String nombre2, String apellidos, String documento,String direccion, String contrasenia) throws VendedorException {
		Vendedor vendedor = new Vendedor();
		vendedor.setNombre(nombre2);
		vendedor.setApellidos(apellidos);
		vendedor.setDocumento(documento);
		vendedor.setDireccion(direccion);
		vendedor.setContrasenia(contrasenia);

		if(existeVendedor(documento) == true){
			throw new VendedorException("Vendedor ya existe");
		}
		listaVendedores.add(vendedor);
		return vendedor;
	}

	/*
	 * Metodo que verifica si ya existe un vendedor en la red
	 */

	public boolean existeVendedor(String documento) {
		for (Vendedor vendedor : listaVendedores) {
			if(vendedor.getDocumento().equals(documento)){
				return true;
			}
		}
		return false;
	}

	/*
	 * Metodo que verifica si ya existe un administrador dentro de la red
	 */

	public boolean existeAdministrador (String documento) {

			if(administrador.getDocumento().equals(documento)){
				return true;
			}

		return false;
	}

	/*
	 * Metodo que llama a la clase Vendedor para actualizar la contrasenia
	 */

	public void actualizarContrasenia( String identificacion, String nuevaContrasenia ) {
		for (Vendedor vendedor : listaVendedores) {
			if(vendedor.getDocumento().equals(identificacion)){
				vendedor.setContrasenia(nuevaContrasenia);
			}
		}
	}

	/*
	 * Metodo que permite actualizar la contrasenia del administrador
	 */

	public void actualizarContraseniaAdmin(String identificacion, String nuevaContrasenia) {
		if(administrador.getDocumento().equals(identificacion)){
			administrador.setContrasenia(nuevaContrasenia);
			}
		}

	/*
	 * Metodo que permite actualizar un vendedor de la red
	 */

	public void actualizarVendedor(String documento, String nombre2, String apellido, String documento2,
			String direccion, String contrasenia) {
		for (Vendedor vendedor : listaVendedores) {
			if(vendedor.getDocumento().equals(documento)){
				vendedor.setNombre(nombre2);
				vendedor.setApellidos(apellido);
				vendedor.setDocumento(documento2);
				vendedor.setDireccion(direccion);
				vendedor.setContrasenia(contrasenia);
			}
		}
	}

	/*
	 * Metodo que permite eliminar un vendedor de la red
	 */

	public boolean eliminarVendedor(String documento) {
		if(existeVendedor(documento)){
			for (Vendedor vendedor : listaVendedores) {
				if(vendedor.getDocumento().equals(documento)){
					listaVendedores.remove(vendedor);
					return true;
				}
			}
		}else{
			return false;
		}
		return false;
	}

	/*
	 * Metodo obtiene el nombre de un vendedor segun su numero de identificacion (documento)
	 */

	public String obtenerNombreVendedor(String documento) {
		String cadenaAux= "";
		for (Vendedor vendedor : listaVendedores) {
			if(vendedor.getDocumento().equals(documento)){
				cadenaAux= vendedor.getNombre()+" "+vendedor.getApellidos();
			}
		}
		return cadenaAux;
	}

	/*
	 * Metodo que retorna una lista de productos de un vendedor
	 */

	public ArrayList<Producto> obtenerProductos(String documento) {
		ArrayList<Producto> listaProducto = new ArrayList<>();
		for (Vendedor vendedor : listaVendedores) {
			if(vendedor.getDocumento().equals(documento)){
				listaProducto.addAll(vendedor.getListaProductos());
			}
		}
		return listaProducto;
	}

	/*
	 * Metodo que llama la clase Vendedor para verificar si existe un producto
	 */
	public boolean existeProducto(String documento, String codigo) {
		for (Vendedor vendedor : listaVendedores) {
			if(vendedor.getDocumento().equals(documento)){
				vendedor.verificarExisteProducto(codigo);
				return true;
			}
		}
		return false;
	}

	/*
	 * Metodo que llama a la clase Vendedor para poder crear un producto
	 */

	public Producto crearProducto(String nombre2, String codigo, String precio, String estado, String categoria,
			String fechaHora, String documento) throws ProductoException {
		for (Vendedor vendedor : listaVendedores) {
			if(vendedor.getDocumento().equals(documento)){
				return vendedor.crearProducto(nombre2, codigo, precio, estado, categoria, fechaHora, documento);
			}
			}
		return null;

	}

	/*
	 * Metodo que llama a la clase Vendedor para poder actualizar un producto
	 */

	public void actualizarProducto(String codigo, String nombre2, String codigo2, String precio, String estado,
			String categoria, String fechaHora, String documento) {
		for (Vendedor vendedor : listaVendedores) {
			if(vendedor.getDocumento().equals(documento)){
				vendedor.actualizarProducto(codigo, nombre2, codigo2, precio, estado, categoria, fechaHora);
			}
		}
	}

	/*
	 * Metodo que llama a la clase Vendedor para poder eliminar un producto
	 */

	public boolean eliminarProducto(String codigo, String documento) {
		for (Vendedor vendedor : listaVendedores) {
			if(vendedor.getDocumento().equals(documento)){
				return vendedor.eliminarProducto(codigo);
			}
		}
		return false;

	}

	/*
	 * Metodo que retorna una lista con los nombres de los vendedores de la red
	 */

	public ArrayList<String> obtenerNombresVendedores(String documento) {
		ArrayList<String> nombres = new ArrayList<>();
		for (Vendedor vendedor : listaVendedores) {
			if(vendedor.getDocumento().compareTo(documento)!=0){
				nombres.add(vendedor.getNombre()+ " " + vendedor.getApellidos());
			}
		}
		return nombres;
	}

	/*
	 * Metodo que verifica si el nombre ingresado es igual al almacenado
	 */

	public boolean obtenerInformacion(String nombre2) {
		for (Vendedor vendedor : listaVendedores) {
			String nombreVendedor= vendedor.getNombre()+" "+vendedor.getApellidos();
			if(nombreVendedor.equals(nombre2)){
				return true;
			}
		}
		return false;
	}

	/*
	 * Metodo que retorna una lista de productos de los vendedores amigos
	 */

	public ArrayList<Producto> obtenerProductosAmigos(String nombre2) {
		ArrayList<Producto> listaProducto = new ArrayList<>();
		for (Vendedor vendedor : listaVendedores) {
			String nombreVendedor= vendedor.getNombre()+" "+vendedor.getApellidos();
			if(nombreVendedor.equals(nombre2)){
				listaProducto.addAll(vendedor.getListaProductos());
			}
		}
		return listaProducto;
	}

	/*
	 * Metodo que llama a la clase Vendedor para poder dar Me Gusta a un producto
	 */

	public boolean darMeGusta(String codigo, String nombre, String documento){
		for (Vendedor vendedor : listaVendedores) {
			String nombreVendedor= vendedor.getNombre()+" "+vendedor.getApellidos();
			if(nombreVendedor.equals(nombre)){
				try {
					return vendedor.darMeGusta(codigo,documento);
				} catch (MeGustaException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	/*
	 * Metodo que llama a la clase Vendedor para saber el numero de Me Gustas de un producto
	 */
	
	
	public String obtenerNumeroMeGustas(String nombre, String codigo) {
		for (Vendedor vendedor : listaVendedores) {
			String nombreVendedor= vendedor.getNombre()+" "+vendedor.getApellidos();
			if(nombreVendedor.equals(nombre)){
				return vendedor.obtenerMeGustas(codigo);
			}
		}
		return null;

	}

	/*
	 * Metodo que retorna una lista con los nombres de los vendedores
	 */
	public ArrayList<Vendedor> obtenerAmigos(String documento) {
			for (Vendedor vendedor : listaVendedores) {
				if(vendedor.getDocumento().equals(documento)){
					return vendedor.getListaAmigos();
				}
			}
			return null;
		}

	
	public String obtenerNumeroMeGustas2(String codigo, String documento) {
		for (Vendedor vendedor : listaVendedores) {
			if(vendedor.getDocumento().equals(documento)){
				return vendedor.obtenerMeGustas2(codigo);
			}
		}
		return null;
	}

	public ArrayList<Producto> obtenerProductosTop() {
		ArrayList<Producto> productosTop = new ArrayList<>();
		for (int i = 0; i < listaVendedores.size(); i++) {
			Vendedor vendedor= listaVendedores.get(i);
			productosTop.addAll(vendedor.obtenerProductosTop());
			}
			return productosTop; 
		}

	public void escribirComentario(String codigo, String nombre2) throws ComentarioException{
		for (Vendedor vendedor : listaVendedores) {
			String nombreVendedor= vendedor.getNombre()+" "+vendedor.getApellidos();
			if(nombreVendedor.equals(nombre2)){
				 vendedor.escribirComentario(codigo);
			}
		}
		
	}

	public ArrayList<Comentario> obtenerComentarios(String codigo, String documento) {
		for (Vendedor vendedor : listaVendedores) {
			if(vendedor.getDocumento().equals(documento)){
				return vendedor.obtenerComentarios(codigo);
			}
		}
		return null;
		
	}

	public boolean agregarAmigo(String nombre, String documento) {
		for(Vendedor vendedor: listaVendedores){
			String nombreVendedor= vendedor.getNombre()+" "+vendedor.getApellidos();
			for (Vendedor vendedor2 : listaVendedores) {
				if(vendedor2.getDocumento().equals(documento) && nombreVendedor.equals(nombre)){
				 return vendedor.agregarAmigo(vendedor, vendedor2);
				}
			}
		}	
		return false;
	}

	public ArrayList<Vendedor> obtenerSolicitudes(String documento) {
		ArrayList<Vendedor> listaSolicitudes = new ArrayList<>();
		for (Vendedor vendedor : listaVendedores) {
			if(vendedor.getDocumento().equals(documento)){
				listaSolicitudes.addAll(vendedor.getListaSolicitudesP());
			}
		}
		return listaSolicitudes;
		
	}

	public boolean aceptarSolicitud(String nombreS, String documento) {
		for(Vendedor vendedor: listaVendedores){
			String nombreVendedor= vendedor.getNombre()+" "+vendedor.getApellidos();
			for (Vendedor vendedor2 : listaVendedores) {
				if(vendedor2.getDocumento().equals(documento) && nombreVendedor.equals(nombreS)){
				 return vendedor.aceptarSolicitud(vendedor, vendedor2);
				}
			}
		}	
		return false;
		}


	public boolean eliminarSolicitud(String documentoS, String documento) {
		for(Vendedor vendedor: listaVendedores){
			String nombreVendedor= vendedor.getNombre()+" "+vendedor.getApellidos();
			for (Vendedor vendedor2 : listaVendedores) {
				if(vendedor2.getDocumento().equals(documento) && nombreVendedor.equals(documentoS)){
				 return vendedor.eliminarSolicitud(vendedor, vendedor2);
				}
			}
		}	
		return false;	
	}

	public boolean verificarCorreo(String correo) {
		// TODO Auto-generated method stub
		return false;
	}
	
	












}
