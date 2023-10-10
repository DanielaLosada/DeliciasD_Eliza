package redVendedores.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import redVendedores.exceptions.ComentarioException;
import redVendedores.exceptions.ProductoException;
import redVendedores.exceptions.VendedorException;
import redVendedores.model.Administrador;
import redVendedores.model.Comentario;
import redVendedores.model.Producto;
import redVendedores.model.Red;
import redVendedores.model.Vendedor;
import redVendedores.persistencia.ArchivoUtil;
import redVendedores.persistencia.Persistencia;


public class ModelFactoryController {
	
	Red red;
	


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

				try {
					guardarResourceXML();
					guardarResourceBinario();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
				// Siempre se debe verificar si la raiz del recurso es null
				if (red == null) {
					System.out.println("es null la red");
					iniciarSalvarDatosPrueba();
					// crearCopias();
					// guardarResourceSerializable();
					guardarResourceXML();
					guardarResourceBinario();
		
				}
	}
	
	
    private void iniciarSalvarDatosPrueba() {
		inicializarDatos();
		try {

			Persistencia.guardarVendedores(getRed().getListaVendedores());
			Persistencia.guardarAdministrador(getRed().getAdministrador());
			Persistencia.guardarProductos(getRed().getListaVendedores());
			
			Persistencia.guardarRecursoBancoBinario(red);
			Persistencia.guardarRecursoBancoXML(red);

			//Persistencia.cargarDatosArchivos(getBanco());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Se inicializaron los datos");
	}
    
    private void inicializarDatos() {

		red= new Red("Shoppify");
		
		
		ArrayList<Vendedor> listaVendedores= new ArrayList<>();
		
		Vendedor vendedor = new Vendedor();
		vendedor.setNombre("Juan Esteban");
		vendedor.setApellidos("Velez");
		vendedor.setDocumento("12345");
		vendedor.setDireccion("jskjdaf");
		vendedor.setContrasenia("0000");

		ArrayList<Producto>listaProductos= new ArrayList<>();

		Producto producto= new Producto();
		producto.setNombre("Nike Air force 1");
		producto.setCodigo("88888");
		producto.setPrecio("$150");
		producto.setCategoria("Calzado");
		producto.setEstado("Publicado");
		producto.setFechaHora(ArchivoUtil.cargarFechaSistema());
		URL ruta= getClass().getResource("/imagenes/Nike.jpg");
		producto.setImagen(ruta.getPath().substring(1));

		Producto producto1= new Producto();
		producto1.setNombre("Vans");
		producto1.setCodigo("5656");
		producto1.setPrecio("$220");
		producto1.setCategoria("Calzado");
		producto1.setEstado("Publicado");
		producto1.setFechaHora(ArchivoUtil.cargarFechaSistema());
		URL ruta2= getClass().getResource("/imagenes/Vans.jpg");
		producto1.setImagen(ruta2.getPath().substring(1));


		Producto producto2= new Producto();
		producto2.setNombre("Adidas Forum low");
		producto2.setCodigo("7979");
		producto2.setPrecio("$280");
		producto2.setCategoria("Calzado");
		producto2.setEstado("Publicado");
		producto2.setFechaHora(ArchivoUtil.cargarFechaSistema());
		URL ruta3= getClass().getResource("/imagenes/e4b262a7f0528b734751a42b34a69d9c.jpg");
		producto2.setImagen(ruta3.getPath().substring(1));


		Producto producto3= new Producto();
		producto3.setNombre("Puma");
		producto3.setCodigo("4656");
		producto3.setPrecio("$200");
		producto3.setCategoria("Calzado");
		producto3.setEstado("Publicado");
		producto3.setFechaHora(ArchivoUtil.cargarFechaSistema());
		URL ruta4= getClass().getResource("/imagenes/p.jpg");
		producto3.setImagen(ruta4.getPath().substring(1));



		Producto producto4= new Producto();
		producto4.setNombre("New balance");
		producto4.setCodigo("7777");
		producto4.setPrecio("$160");
		producto4.setCategoria("Calzado");
		producto4.setEstado("Publicado");
		producto4.setFechaHora(ArchivoUtil.cargarFechaSistema());
		URL ruta5= getClass().getResource("/imagenes/n.jpg");
		producto4.setImagen(ruta5.getPath().substring(1));

		Producto producto5= new Producto();
		producto5.setNombre("Gucci");
		producto5.setCodigo("2321");
		producto5.setPrecio("$500");
		producto5.setCategoria("Calzado");
		producto5.setEstado("Publicado");
		producto5.setFechaHora(ArchivoUtil.cargarFechaSistema());
		URL ruta6= getClass().getResource("/imagenes/gucci.jpeg");
		producto5.setImagen(ruta6.getPath().substring(1));


		listaProductos.add(producto);
		listaProductos.add(producto1);
		listaProductos.add(producto2);
		listaProductos.add(producto3);
		listaProductos.add(producto4);
		listaProductos.add(producto5);

		vendedor.setListaProductos(listaProductos);
		listaVendedores.add(vendedor);

		Vendedor vendedor2 = new Vendedor();
		vendedor2.setNombre("Angelica Maria");
		vendedor2.setApellidos("Popayan");
		vendedor2.setDocumento("3335554");
		vendedor2.setDireccion("Cra 34 b/ Caldas");
		vendedor2.setContrasenia("abcd");

		ArrayList<Producto>listaProductos2= new ArrayList<>();

		Producto productoA= new Producto();
		productoA.setNombre("Lavadora");
		productoA.setCodigo("8976");
		productoA.setPrecio("$500");
		productoA.setCategoria("Electrodomesticos");
		productoA.setEstado("Publicada");
		productoA.setFechaHora(ArchivoUtil.cargarFechaSistema());
		URL rutaA= getClass().getResource("/imagenes/lavadoras.jpg");
		productoA.setImagen(rutaA.getPath().substring(1));

		Producto productoB= new Producto();
		productoB.setNombre("Microondas");
		productoB.setCodigo("4563");
		productoB.setPrecio("$100");
		productoB.setCategoria("Electrodomesticos");
		productoB.setEstado("Publicada");
		productoB.setFechaHora(ArchivoUtil.cargarFechaSistema());
		URL rutaB= getClass().getResource("/imagenes/microondas.png");
		productoB.setImagen(rutaB.getPath().substring(1));

		Producto productoC= new Producto();
		productoC.setNombre("Nevera");
		productoC.setCodigo("5678");
		productoC.setPrecio("$800");
		productoC.setCategoria("Electrodomesticos");
		productoC.setEstado("Publicada");
		productoC.setFechaHora(ArchivoUtil.cargarFechaSistema());
		URL rutaC= getClass().getResource("/imagenes/nevera.jpg");
		productoC.setImagen(rutaC.getPath().substring(1));


		Producto productoD= new Producto();
		productoD.setNombre("Estufa");
		productoD.setCodigo("1290");
		productoD.setPrecio("$600");
		productoD.setCategoria("Electrodomesticos");
		productoD.setEstado("Publicada");
		productoD.setFechaHora(ArchivoUtil.cargarFechaSistema());
		URL rutaD= getClass().getResource("/imagenes/estufa.jpg");
		productoD.setImagen(rutaD.getPath().substring(1));

		Producto productoE= new Producto();
		productoE.setNombre("Televisor 50'' ");
		productoE.setCodigo("8655");
		productoE.setPrecio("$200");
		productoE.setCategoria("Electrodomesticos");
		productoE.setEstado("Publicada");
		productoE.setFechaHora(ArchivoUtil.cargarFechaSistema());
		URL rutaE= getClass().getResource("/imagenes/tv.jpg");
		productoE.setImagen(rutaE.getPath().substring(1));

		Producto productoF= new Producto();
		productoF.setNombre("Sanduchera");
		productoF.setCodigo("3246");
		productoF.setPrecio("$200");
		productoF.setCategoria("Electrodomesticos");
		productoF.setEstado("Publicada");
		productoF.setFechaHora(ArchivoUtil.cargarFechaSistema());
		URL rutaF= getClass().getResource("/imagenes/sanduchera.jpg");
		productoF.setImagen(rutaF.getPath().substring(1));



		listaProductos2.add(productoA);
		listaProductos2.add(productoB);
		listaProductos2.add(productoC);
		listaProductos2.add(productoD);
		listaProductos2.add(productoE);
		listaProductos2.add(productoF);

		vendedor2.setListaProductos(listaProductos2);
		listaVendedores.add(vendedor2);

		Vendedor vendedor3 = new Vendedor();
		vendedor3.setNombre("Luisa Fernanda");
		vendedor3.setApellidos("Cadavid");
		vendedor3.setDocumento("987654");
		vendedor3.setDireccion("Apto 1345 Edif Palma e Hoyo");
		vendedor3.setContrasenia("luchita");

		ArrayList<Producto>listaProductos3= new ArrayList<>();

		Producto productito1= new Producto();
		productito1.setNombre("Candongas");
		productito1.setCodigo("6541");
		productito1.setPrecio("$600");
		productito1.setCategoria("Joyas");
		productito1.setEstado("Publicado");
		productito1.setFechaHora(ArchivoUtil.cargarFechaSistema());
		URL ruta11= getClass().getResource("/imagenes/candongas.jpg");
		productito1.setImagen(ruta11.getPath().substring(1));

		Producto productito2= new Producto();
		productito2.setNombre("Anillo");
		productito2.setCodigo("7809");
		productito2.setPrecio("$600");
		productito2.setCategoria("Accesorios");
		productito2.setEstado("Publicado");
		productito1.setFechaHora(ArchivoUtil.cargarFechaSistema());
		URL ruta22= getClass().getResource("/imagenes/anilloHuella.jpg");
		productito2.setImagen(ruta22.getPath().substring(1));

		Producto productito3= new Producto();
		productito3.setNombre("Pulsera");
		productito3.setCodigo("9087");
		productito3.setPrecio("$500");
		productito3.setCategoria("Accesorios");
		productito3.setEstado("Publicado");
		productito3.setFechaHora(ArchivoUtil.cargarFechaSistema());
		URL ruta33= getClass().getResource("/imagenes/pulsera.jpg");
		productito3.setImagen(ruta33.getPath().substring(1));


		Producto productito4= new Producto();
		productito4.setNombre("Tobillera");
		productito4.setCodigo("1256");
		productito4.setPrecio("$300");
		productito4.setCategoria("Accesorios");
		productito4.setEstado("Publicado");
		productito4.setFechaHora(ArchivoUtil.cargarFechaSistema());
		URL ruta44= getClass().getResource("/imagenes/tobillera.jpg");
		productito4.setImagen(ruta44.getPath().substring(1));

		Producto productito5= new Producto();
		productito5.setNombre("Collar");
		productito5.setCodigo("1236");
		productito5.setPrecio("$600");
		productito5.setCategoria("Accesorios");
		productito5.setEstado("Publicada");
		productito5.setFechaHora(ArchivoUtil.cargarFechaSistema());
		URL ruta55= getClass().getResource("/imagenes/collar.jpg");
		productito5.setImagen(ruta55.getPath().substring(1));

		Producto productito6= new Producto();
		productito6.setNombre("Dije");
		productito6.setCodigo("9087");
		productito6.setPrecio("$250");
		productito6.setCategoria("Accesorios");
		productito6.setEstado("Publicada");
		productito6.setFechaHora(ArchivoUtil.cargarFechaSistema());
		URL ruta66= getClass().getResource("/imagenes/dije.jpg");
		productito6.setImagen(ruta66.getPath().substring(1));



		listaProductos3.add(productito1);
		listaProductos3.add(productito2);
		listaProductos3.add(productito3);
		listaProductos3.add(productito4);
		listaProductos3.add(productito5);
		listaProductos3.add(productito6);

		vendedor3.setListaProductos(listaProductos3);

		listaVendedores.add(vendedor3);

		Vendedor vendedor4 = new Vendedor();
		vendedor4.setNombre("Pepito Pepe");
		vendedor4.setApellidos("Perez");
		vendedor4.setDocumento("654321");
		vendedor4.setDireccion("Cra 24 #89");
		vendedor4.setContrasenia("dospordos");

		ArrayList<Producto>listaProductos4= new ArrayList<>();

		Producto productitoA= new Producto();
		productitoA.setNombre("Camiseta hombre");
		productitoA.setCodigo("3256");
		productitoA.setPrecio("$600");
		productitoA.setCategoria("Ropa");
		productitoA.setEstado("Publicado");
		productitoA.setFechaHora(ArchivoUtil.cargarFechaSistema());
		URL rutaAA= getClass().getResource("/imagenes/camiseta.jpg");
		productitoA.setImagen(rutaAA.getPath().substring(1));

		Producto productitoB= new Producto();
		productitoB.setNombre("Blusa mujer");
		productitoB.setCodigo("2345");
		productitoB.setPrecio("$500");
		productitoB.setCategoria("Ropa");
		productitoB.setEstado("Publicada");
		productitoB.setFechaHora(ArchivoUtil.cargarFechaSistema());
		URL rutaBB= getClass().getResource("/imagenes/bbb.jpg");
		productitoB.setImagen(rutaBB.getPath().substring(1));

		Producto productitoC= new Producto();
		productitoC.setNombre("Jean rotos unisex");
		productitoC.setCodigo("1289");
		productitoC.setPrecio("$190");
		productitoC.setCategoria("Ropa");
		productitoC.setEstado("Publicado");
		productitoC.setFechaHora(ArchivoUtil.cargarFechaSistema());
		URL rutaCC= getClass().getResource("/imagenes/jeans.jpg");
		productitoC.setImagen(rutaCC.getPath().substring(1));


		Producto productitoD= new Producto();
		productitoD.setNombre("Falda cuero");
		productitoD.setCodigo("9865");
		productitoD.setPrecio("$178");
		productitoD.setCategoria("Ropa");
		productitoD.setEstado("Publicado");
		productitoD.setFechaHora(ArchivoUtil.cargarFechaSistema());
		URL rutaDD= getClass().getResource("/imagenes/falda.jpg");
		productitoD.setImagen(rutaDD.getPath().substring(1));

		Producto productitoE= new Producto();
		productitoE.setNombre("Chaqueta jean hombre");
		productitoE.setCodigo("7879");
		productitoE.setPrecio("$250");
		productitoE.setCategoria("Ropa");
		productitoE.setEstado("Publicado");
		productitoE.setFechaHora(ArchivoUtil.cargarFechaSistema());
		URL rutaEE= getClass().getResource("/imagenes/chaqueta.jpg");
		productitoE.setImagen(rutaEE.getPath().substring(1));

		Producto productitoF= new Producto();
		productitoF.setNombre("Sudadera");
		productitoF.setCodigo("4578");
		productitoF.setPrecio("$110");
		productitoF.setCategoria("Ropa");
		productitoF.setEstado("Publicada");
		productitoD.setFechaHora(ArchivoUtil.cargarFechaSistema());
		URL rutaFF= getClass().getResource("/imagenes/sudadera.jpg");
		productitoF.setImagen(rutaFF.getPath().substring(1));



		listaProductos4.add(productitoA);
		listaProductos4.add(productitoB);
		listaProductos4.add(productitoC);
		listaProductos4.add(productitoD);
		listaProductos4.add(productitoE);
		listaProductos4.add(productitoF);

		vendedor4.setListaProductos(listaProductos4);
		listaVendedores.add(vendedor4);

		Vendedor vendedor5 = new Vendedor();
		vendedor5.setNombre("Edgar Alonso");
		vendedor5.setApellidos("Gajo");
		vendedor5.setDocumento("775545");
		vendedor5.setDireccion("Finca Los Rios");
		vendedor5.setContrasenia("juasjuas");

		ArrayList<Producto>listaProductos5= new ArrayList<>();

		Producto producto1A= new Producto();
		producto1A.setNombre("Martillo");
		producto1A.setCodigo("3422");
		producto1A.setPrecio("$380");
		producto1A.setCategoria("Herramientas");
		producto1A.setEstado("Publicado");
		producto1A.setFechaHora(ArchivoUtil.cargarFechaSistema());
		URL ruta1A= getClass().getResource("/imagenes/martillo.jpg");
		producto1A.setImagen(ruta1A.getPath().substring(1));

		Producto producto2B= new Producto();
		producto2B.setNombre("Destornillador");
		producto2B.setCodigo("1289");
		producto2B.setPrecio("$450");
		producto2B.setCategoria("Herramientas");
		producto2B.setEstado("Publicada");
		producto2B.setFechaHora(ArchivoUtil.cargarFechaSistema());
		URL ruta2B= getClass().getResource("/imagenes/destornillador.jpg");
		producto2B.setImagen(ruta2B.getPath().substring(1));

		Producto producto3C= new Producto();
		producto3C.setNombre("Alicate");
		producto3C.setCodigo("2342");
		producto3C.setPrecio("$290");
		producto3C.setCategoria("Herramientas");
		producto3C.setEstado("Publicada");
		producto3C.setFechaHora(ArchivoUtil.cargarFechaSistema());
		URL ruta3C= getClass().getResource("/imagenes/alicate.jpg");
		producto3C.setImagen(ruta3C.getPath().substring(1));


		Producto producto4D= new Producto();
		producto4D.setNombre("Serrucho");
		producto4D.setCodigo("1092");
		producto4D.setPrecio("$100");
		producto4D.setCategoria("Herramientas");
		producto4D.setEstado("Publicada");
		producto4D.setFechaHora(ArchivoUtil.cargarFechaSistema());
		URL ruta4D= getClass().getResource("/imagenes/serrucho.jpg");
		producto4D.setImagen(ruta4D.getPath().substring(1));

		Producto producto5E= new Producto();
		producto5E.setNombre("Metro");
		producto5E.setCodigo("1278");
		producto5E.setPrecio("$790");
		producto5E.setCategoria("Herramientas");
		producto5E.setEstado("Publicada");
		producto5E.setFechaHora(ArchivoUtil.cargarFechaSistema());
		URL ruta5E= getClass().getResource("/imagenes/metro.jpg");
		producto5E.setImagen(ruta5E.getPath().substring(1));

		Producto producto6F= new Producto();
		producto6F.setNombre("Llave inglesa");
		producto6F.setCodigo("2312");
		producto6F.setPrecio("$360");
		producto6F.setCategoria("Herramientas");
		producto6F.setEstado("Publicada");
		producto6F.setFechaHora(ArchivoUtil.cargarFechaSistema());
		URL ruta6F= getClass().getResource("/imagenes/llave.jpg");
		producto6F.setImagen(ruta6F.getPath().substring(1));



		listaProductos5.add(producto1A);
		listaProductos5.add(producto2B);
		listaProductos5.add(producto3C);
		listaProductos5.add(producto4D);
		listaProductos5.add(producto5E);
		listaProductos5.add(producto6F);

		vendedor5.setListaProductos(listaProductos5);
		listaVendedores.add(vendedor5);


		 Administrador administrador= new Administrador();
		 administrador.setDocumento("10923870251");
		 administrador.setContrasenia("DAX");
		 
		 red.setListaVendedores(listaVendedores);
		 red.setAdministrador(administrador);
		
		System.out.println("Red inicializada "+red );
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

	public boolean verificarCorreo(String correo) {
		return red.verificarCorreo(correo);
	}
	

	

	

}
