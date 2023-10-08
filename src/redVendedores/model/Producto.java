package redVendedores.model;


import java.io.Serializable;
import java.util.ArrayList;

public class Producto implements Serializable{
	
	/*
	 * Atributos de la clase Producto
	 */
	private static final long serialVersionUID = 1L;
	private String nombre; 
	private String codigo; 
	private String precio; 
	private String estado; 
	private String categoria;
	private String fechaHora;
	private String imagen; 
	public ArrayList<MeGusta>listaMeGustas = new ArrayList<>();
	public ArrayList<Comentario>listaComentarios = new ArrayList<>();
	
	
	//private int meGusta; 
	//private String comentario;
	
	/*
	 * Constructores de la clase Producto
	 */

	public Producto(String nombre, String codigo, String precio, String estado, String categoria, String fechaHora,
			String imagen) {
		super();
		this.nombre = nombre;
		this.codigo = codigo;
		this.precio = precio;
		this.estado = estado;
		this.categoria = categoria;
		this.fechaHora = fechaHora;
		this.imagen = imagen;
	}

	public Producto(){
		super();
	}
	
	//------------------------------------------
	
	/*
	 * Getters & Setters de la clase Producto
	 */

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public String getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}
	
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	
	
	/*
	
	public int getMeGusta() {
		return meGusta;
	}

	public void setMeGusta(int meGusta) {
		this.meGusta = meGusta;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	*/
	
	
	/*
	 * Metodo equals de la clase Producto
	 */

	public ArrayList<MeGusta> getListaMeGustas() {
		return listaMeGustas;
	}

	public void setListaMeGustas(ArrayList<MeGusta> listaMeGustas) {
		this.listaMeGustas = listaMeGustas;
	}
	
	public ArrayList<Comentario> getListaComentarios() {
		return listaComentarios;
	}

	public void setListaComentarios(ArrayList<Comentario> listaComentarios) {
		this.listaComentarios = listaComentarios;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Producto other = (Producto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	
	/*
	 * toString de la clase Producto
	 */
	
	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + ", codigo=" + codigo + ", precio=" + precio + ", estado=" + estado
				+ ", categoria=" + categoria + ", fechaHora=" + fechaHora + ", imagen=" + imagen + "]";
	}

	public boolean verificarExisteMeGusta(String documento) {
		for (MeGusta meGusta : listaMeGustas) {
			if(meGusta.getDocumento().equals(documento)){
				return false;
			}
		}
		return true;
	}
	
	public String mostrarMensajes() {
		String mensajeComentarios="";
		mensajeComentarios= listaComentarios.toString();
		return mensajeComentarios;
	}
	
	/*

	public void verificarExisteMeGusta(String documento) {
		for (MeGusta meGusta : listaMeGustas) {
			Vendedor vendedor= meGusta.getVendedor();
			if(vendedor.getDocumento().equals(documento)){
				MeGusta meGustas= new MeGusta();
				meGustas.setVendedor(vendedor);
				listaMeGustas.add(meGustas);
			}
		}
	}
	*/
	

	
	
	

}
