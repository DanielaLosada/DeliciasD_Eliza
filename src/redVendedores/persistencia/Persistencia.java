package redVendedores.persistencia;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import redVendedores.model.Administrador;
import redVendedores.model.Producto;
import redVendedores.model.Red;
import redVendedores.model.Vendedor;

public class Persistencia {
	public static final String RUTA_ARCHIVO_EMPLEADOS = "src/redVendedores/persistencia/archivos/archivoVendedores.txt";
	public static final String RUTA_ARCHIVO_ADMIN = "src/redVendedores/persistencia/archivos/archivoAdministrador.txt";
	public static final String RUTA_ARCHIVO_PRODUCTOS = "src/redVendedores/persistencia/archivos/archivoProductos.txt";
	public static final String RUTA_ARCHIVO_MODELO_RED_BINARIO = "src/redVendedores/persistencia/model.dat";
	public static final String RUTA_ARCHIVO_MODELO_RED_XML = "src/redVendedores/persistencia/model.xml";
	public static final String RUTA_ARCHIVO_RED_LOG= "src/redVendedores/persistencia/log/redVendedores_Log.txt";
	public static final String RUTA_CARPETA_RESPALDO= "src/redVendedores/persistencia/respaldo/";


	public static void cargarDatosArchivos(Red red) throws FileNotFoundException, IOException {
	
//		//cargar archivos empleados
//		ArrayList<Vendedor> empleadosCargados = cargarEmpleados();
//		
//		if(empleadosCargados.size() > 0)
//			red.getListaVendedores().addAll(empleadosCargados);
		
		//cargar archivos empleados
		ArrayList<Vendedor> empleadosCargados = cargarEmpleados();
		ArrayList<Producto> productosCargados = cargarProductos();
		if(empleadosCargados.size() > 0)
			for (Vendedor vendedor : empleadosCargados) {
				vendedor.getListaProductos().addAll(productosCargados);
			}
			red.getListaVendedores().addAll(empleadosCargados);
			
		Administrador adminCargado= cargarAdministrador();
		red.setAdministrador(adminCargado);
		
	}
	
	public static void crearCopias() throws IOException{
        try {
            ArchivoUtil.moveFile(RUTA_ARCHIVO_EMPLEADOS, RUTA_CARPETA_RESPALDO);
            ArchivoUtil.moveFile(RUTA_ARCHIVO_ADMIN, RUTA_CARPETA_RESPALDO);
            ArchivoUtil.moveFile(RUTA_ARCHIVO_PRODUCTOS, RUTA_CARPETA_RESPALDO);
            ArchivoUtil.moveFile(RUTA_ARCHIVO_MODELO_RED_BINARIO, RUTA_CARPETA_RESPALDO);
            ArchivoUtil.moveFile(RUTA_ARCHIVO_MODELO_RED_XML, RUTA_CARPETA_RESPALDO);
            System.out.println("File moved successfully.");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
	}
	
	
//	----------------------LOADS------------------------
	
	/**
	 * 
	 * @param tipoPersona
	 * @param ruta
	 * @return un Arraylist de personas con los datos obtenidos del archivo de texto indicado
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	
	private static ArrayList<Vendedor> cargarEmpleados() throws IOException {
		
		ArrayList<Vendedor> vendedores =new ArrayList<Vendedor>();
		
		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_EMPLEADOS);
		String linea="";
		
		for (int i = 0; i < contenido.size(); i++)
		{
			linea = contenido.get(i);
			Vendedor vendedor = new Vendedor();
			vendedor.setNombre(linea.split("@@")[0]);
			vendedor.setApellidos(linea.split("@@")[1]);
			vendedor.setDireccion(linea.split("@@")[2]);
			vendedor.setDocumento(linea.split("@@")[3]);
			vendedores.add(vendedor);
		}
		return vendedores;
	}
	
	private static ArrayList<Producto> cargarProductos() throws IOException{
		ArrayList<Producto> productos =new ArrayList<Producto>();
		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_PRODUCTOS);
		String linea="";
		for(int i= 0; i<contenido.size(); i++){
			linea= contenido.get(i); 
			Producto producto = new Producto(); 
			producto.setNombre(linea.split("@@")[1]);
			producto.setCodigo(linea.split("@@")[2]);
			producto.setPrecio(linea.split("@@")[3]);
			producto.setCategoria(linea.split("@@")[4]);
			producto.setEstado(linea.split("@@")[5]);
			productos.add(producto);
		}
		return productos;
	}
	
	private static Administrador cargarAdministrador() throws IOException{ 
		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_ADMIN);
		Administrador admin = new Administrador();
		String linea="";
		for(int i= 0; i<contenido.size(); i++){
			linea= contenido.get(i); 
			admin.setDocumento(linea.split("@@")[0]);
			admin.setContrasenia(linea.split("@@")[1]);
		}
		return admin;
		
	}
	
	public static void guardaRegistroLog(String mensajeLog, int nivel, String accion)
	{
		
		ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_RED_LOG);
	}


	
	
	
//	----------------------SAVES------------------------
	
	/**
	 * Guarda en un archivo de texto todos la informaci�n de las personas almacenadas en el ArrayList
	 * @param objetos
	 * @param ruta
	 * @throws IOException
	 */
	
	public static void guardarVendedores(ArrayList<Vendedor> listaVendedores) throws IOException {
		
		// TODO Auto-generated method stub
		String contenido = "";
		
		for(Vendedor vendedor:listaVendedores) 
		{
			contenido+= vendedor.getNombre()+"@@"+vendedor.getApellidos()+"@@"+vendedor.getDocumento()+"@@"+vendedor.getDireccion()+"\n";
		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_EMPLEADOS, contenido, false);
	}
	
	public static void guardarAdministrador (Administrador administrador) throws IOException{
		String contenido= "";
		contenido+= administrador.getDocumento()+"@@"+administrador.getContrasenia()+"@";
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_ADMIN, contenido, false);
	}
	
	public static void guardarProductos(ArrayList<Vendedor>listaVendedores) throws IOException{
		String contenido= "";
		for (Vendedor vendedor : listaVendedores) {
			ArrayList<Producto> productos = vendedor.getListaProductos();
			for (Producto producto : productos) {
				contenido+= vendedor.getNombre()+"@@"+producto.getNombre()+"@@"+producto.getCodigo()+"@@"+
						producto.getPrecio()+"@@"+producto.getCategoria()+"@@"+producto.getEstado()+"@@"+
						producto.getImagen()+"@@"+"\n";
			}
		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_PRODUCTOS, contenido, false);
	}

	
	
	//------------------------------------SERIALIZACI�N  y XML
	
	
	public static Red cargarRecursoBancoBinario() {
		
		Red red = null;
		
		try {
			red = (Red)ArchivoUtil.cargarRecursoSerializado(RUTA_ARCHIVO_MODELO_RED_BINARIO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return red;
	}
	
	public static void guardarRecursoBancoBinario(Red red) {
		
		try {
			ArchivoUtil.salvarRecursoSerializado(RUTA_ARCHIVO_MODELO_RED_BINARIO, red);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static Red cargarRecursoBancoXML() {
		
		Red red = null;
		
		try {
			red = (Red)ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_RED_XML);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return red;
	}

	
	
	public static void guardarRecursoBancoXML(Red red) {
		
		try {
			ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_RED_XML, red);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
