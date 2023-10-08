package redVendedores.model;

import java.io.Serializable;

public class Administrador implements Serializable {
	
	/*
	 * Atributos de la clase Administrador
	 */
	private static final long serialVersionUID = 1L;
	private String contrasenia;
	private String documento;
	
	/*
	 * Constructores de la clase Administrador
	 */

	public Administrador( String contrasenia, String documento) {
		super();

		this.contrasenia = contrasenia;
		this.documento = documento;
	}

	public Administrador(){
		super();
	}
	
	//-----------------------------------
	
	/*
	 * Getters & Setters de la clase Administrador
	 */
	

	public String getContrasenia() {
		return contrasenia;
	}


	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}


	public String getDocumento() {
		return documento;
	}


	public void setDocumento(String documento) {
		this.documento = documento;
	}
	
	/*
	 * toString de la clase Administrador
	 */

	@Override
	public String toString() {
		return "Administrador [ contrasenia=" + contrasenia
				+ ", documento=" + documento + "]";
	}
	
	/*
	 * Metodo equals de la clase Administrador
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
			if (other.getContrasenia() != null)
				return false;
		} else if (!contrasenia.equals(other.getContrasenia()))
			return false;
		if (documento == null) {
			if (other.getDocumento() != null)
				return false;
		} else if (!documento.equals(other.getDocumento()))
			return false;
		return true;
	}
	
	//-------------------------------------------
	
	
	/*
	 * Metodo que verifica si el documento (usuario) y la contrasenia son iguales a las almacenadas
	 */

	public boolean verificarDocumento(String documento2, String contrasenia2) {
		if(getDocumento().equals(documento2) && getContrasenia().equals(contrasenia2)){
			return true;
		}
		return false;
	}





}
