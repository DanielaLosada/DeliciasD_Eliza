package redVendedores.model;


import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import redVendedores.exceptions.MeGustaException;
import redVendedores.exceptions.ProductoException;

public class Vendedor implements Serializable {
	
	/*
	 * Atributos de la clase Vendedor 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String apellidos;
	private String documento;
	private String direccion;
	private String contrasenia;
	public ArrayList<Producto>listaProductos = new ArrayList<>();
	private ArrayList<Vendedor> listaAmigos= new ArrayList<>();
	private ArrayList<Vendedor> listaSolicitudesP= new ArrayList<>();
	
	/*
	 * Constructores de la clase Vendedor 
	 */
	
	public Vendedor(String nombre, String apellidos, String documento, String direccion, String contrasenia) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.documento = documento;
		this.direccion = direccion;
		this.contrasenia = contrasenia;
	}

	public Vendedor(){
		super();
	}
	
	//------------------------------------
	
	/*
	 * Getters & Setters de la clase Vendedor 
	 */
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public ArrayList<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(ArrayList<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}
	
	public ArrayList<Vendedor> getListaAmigos() {
		return listaAmigos;
	}

	public void setListaAmigos(ArrayList<Vendedor> listaAmigos) {
		this.listaAmigos = listaAmigos;
	}

	public ArrayList<Vendedor> getListaSolicitudesP() {
		return listaSolicitudesP;
	}

	public void setListaSolicitudesP(ArrayList<Vendedor> listaSolicitudesP) {
		this.listaSolicitudesP = listaSolicitudesP;
	}

	
	/*
	 * toString de la clase Vendedor
	 */

	@Override
	public String toString() {
		return "Vendedor [nombre=" + nombre + ", apellidos=" + apellidos + ", documento=" + documento + ", direccion="
				+ direccion + ", contrasenia=" + contrasenia + "]";
	}
	
	/*
	 * Metodo equals de la clase Vendedor
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contrasenia == null) ? 0 : contrasenia.hashCode());
		result = prime * result + ((documento == null) ? 0 : documento.hashCode());
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
		Vendedor other = (Vendedor) obj;
		if (contrasenia == null) {
			if (other.contrasenia != null)
				return false;
		} else if (!contrasenia.equals(other.contrasenia))
			return false;
		if (documento == null) {
			if (other.documento != null)
				return false;
		} else if (!documento.equals(other.documento))
			return false;
		return true;
	}
	
	//----------------------------------------
	
	/*
	 * Metodo que verifica si el documento (usuario) y la contrasenia ingresadas sean iguales a las almacenadas
	 */


	public boolean verificarDocumento(String documento, String contrasenia) {
		if(getDocumento().equals(documento) && getContrasenia().equals(contrasenia)){
			return true;
		}
		return false;
	}
	
	/*
	 * Metodo que verifica si el documento que entra por parametro es igual al almacenado
	 */

	public boolean verificarDocumento(String documento2) {
		if(getDocumento().equals(documento)){
			return true;
		}
		return false;
	}
	
	/*
	 * Metodo que obtiene la lista de productos
	 */

	public ArrayList<Producto> obtenerProductos() {
		return getListaProductos();
	}
	
	/*
	 * Metodo que permite al vendedor crear un producto
	 */

	public Producto crearProducto(String nombre2, String codigo, String precio, String estado, String categoria,
			String fechaHora, String documento) throws ProductoException {

		Producto producto = new Producto();
		producto.setNombre(nombre2);
		producto.setCodigo(codigo);
		producto.setPrecio(precio);
		producto.setEstado(estado);
		producto.setCategoria(categoria);


		if(verificarExisteProducto(codigo) == true){
			throw new ProductoException("Producto ya existe");
		}
		listaProductos.add(producto);
		return producto;
	}
	
	/*
	 * Metodo que verifica si ya existe el producto de la lista de productos del vendedor 
	 */

	public boolean verificarExisteProducto(String codigo) {
		for (Producto producto : listaProductos) {
			if(producto.getCodigo().equals(codigo)){
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Metodo que permite al vendedor actualizar un producto
	 */

	public void actualizarProducto(String codigo, String nombre2, String codigo2, String precio, String estado, String categoria,
			String fechaAux) {
		for (Producto producto : listaProductos) {
			if(producto.getCodigo().equals(codigo)){
				producto.setNombre(nombre2);
				producto.setCodigo(codigo2);
				producto.setPrecio(precio);
				producto.setEstado(estado);
				producto.setCategoria(categoria);
			}
		}
	}
	
	/*
	 * Metodo que permite al vendedor eliminar un producto
	 */

	public boolean eliminarProducto(String codigo) {
		for (Producto producto : listaProductos) {
			if(producto.getCodigo().equals(codigo)){
				listaProductos.remove(producto);
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Procedimiento que permite al vendedor dar Me Gusta a un producto
	 */
	
	
	public boolean darMeGusta(String codigo, String documento) throws MeGustaException {
		for (Producto producto : listaProductos) {
			if(producto.getCodigo().equals(codigo)){
				boolean verificado= producto.verificarExisteMeGusta(documento);
				if(verificado){
					MeGusta meGusta= new MeGusta();
					meGusta.setDocumento(documento);
					producto.getListaMeGustas().add(meGusta);
					return true;
				}
			}
		}
		return false;
	}

	
	/*
	 * Metodo que permite obtener el numero Me Gusta de un producto
	 */
	
	
	
	public String obtenerMeGustas(String codigo) {
		String meGusta= "";
		for (Producto producto : listaProductos) {
			if(producto.getCodigo().equals(codigo)){
				meGusta= Integer.toString(producto.getListaMeGustas().size());
			}
		}
		return meGusta;
	}
	
	public String obtenerMeGustas2(String codigo) {
		String meGusta= "";
		for (Producto producto : listaProductos) {
			if(producto.getCodigo().equals(codigo)){
				meGusta= Integer.toString(producto.getListaMeGustas().size());
			}
		}
		return meGusta;
	}

	public ArrayList<Producto> obtenerProductosTop() {
		ArrayList<Producto> productosTop = new ArrayList<>();
		productosTop.addAll(listaProductos);
		Producto aux= null;
	    for(int i = 0;i < productosTop.size()-1;i++){
	        for(int j = 0;j < productosTop.size()-i-1;j++){
	            if(productosTop.get(j+1).getListaMeGustas().size() < productosTop.get(j).getListaMeGustas().size()){    
	                aux = productosTop.get(j+1);
	                productosTop.set(j+1,productosTop.get(j));
	                productosTop.set(j,aux);
	            }
	        }
	    }
	    return productosTop;
	}
	

	public void escribirComentario(String codigo) {
		for (Producto producto : listaProductos) {
			if(producto.getCodigo().equals(codigo)){
				Comentario comentario = new Comentario(); 
				String mensaje= JOptionPane.showInputDialog(null, "Escriba el comentario que quiere dejar al producto", "Comentarios", JOptionPane.QUESTION_MESSAGE);
				if(mensaje==null){
					JOptionPane.showMessageDialog(null, "Debe escribir un comentario");
				}else{
					comentario.setMensaje(mensaje);
					producto.getListaComentarios().add(comentario);
					JOptionPane.showMessageDialog(null,producto.mostrarMensajes(), "Comentarios del producto", JOptionPane.PLAIN_MESSAGE);
				}
				
			}
		}
	}

	public ArrayList<Comentario> obtenerComentarios(String codigo) {
		for (Producto producto : listaProductos) {
			if(producto.getCodigo().equals(codigo)){
				return producto.getListaComentarios();
			}
		}
		return null;
		
	}

	public boolean agregarAmigo(Vendedor vendedor, Vendedor vendedor2) {
			if(!listaAmigos.contains(vendedor2) && !listaSolicitudesP.contains(vendedor2)){
				getListaSolicitudesP().add(vendedor2);
				return true;
			}
		return false;
	}

	public boolean aceptarSolicitud(Vendedor vendedor, Vendedor vendedor2) {
		if(vendedor2.getListaSolicitudesP().contains(vendedor)){
			vendedor2.getListaSolicitudesP().remove(vendedor);
			vendedor.getListaSolicitudesP().remove(vendedor2);
			vendedor2.getListaAmigos().add(vendedor);
			vendedor.getListaAmigos().add(vendedor2);
			return true;
		}
		return false;
		
	}

	public boolean eliminarSolicitud(Vendedor vendedor, Vendedor vendedor2) {
		if(vendedor2.getListaSolicitudesP().contains(vendedor)){
			vendedor2.getListaSolicitudesP().remove(vendedor);
			vendedor.getListaSolicitudesP().remove(vendedor2);
			return true;
		}
		return false;
	}



	
	
	/*
	public String mostrarComentarios(String codigo){
		for (Producto producto : listaProductos) {
			if(producto.getCodigo().equals(codigo)){
				producto.getListaComentarios();
				
				
			}
		}
		*/
		
		
	


	



}
