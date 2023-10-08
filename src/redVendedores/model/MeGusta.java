package redVendedores.model;

import java.io.Serializable;

public class MeGusta implements Serializable {
	private static final long serialVersionUID = 1L;
	private String documento;

	public MeGusta(String documento) {
		super();
		this.documento = documento;
	} 
	
	public MeGusta(){
		super();
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		MeGusta other = (MeGusta) obj;
		if (documento == null) {
			if (other.documento != null)
				return false;
		} else if (!documento.equals(other.documento))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MeGusta [documento=" + documento + "]";
	}
	
	
	
	

}
