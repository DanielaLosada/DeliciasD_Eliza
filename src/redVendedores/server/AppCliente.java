package redVendedores.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import redVendedores.model.Red;

public class AppCliente {
	String host;
    int puerto;
    Socket socketComunicacion;

    DataOutputStream flujoSalida;
    ObjectInputStream flujoEntradaObjeto;
    
    Red red;

    public AppCliente(String host, int puerto) {
        this.puerto = puerto;
        this.host = host;
    }



    public AppCliente() {
	}

	public void iniciarCliente() {
        try
        {
            crearConexion();
            flujoEntradaObjeto = new ObjectInputStream(socketComunicacion.getInputStream());
            //recibirObjeto();
//            flujoEntradaObjeto.close();
//            socketComunicacion.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }//Fin de la conexi�n
    }


    public void crearConexion()throws IOException {
        socketComunicacion = new Socket(host, puerto);
        System.out.println ("conectado");
    }


    public Red recibirObjeto()throws IOException, ClassNotFoundException{
//    	flujoSalida = new DataOutputStream(socketComunicacion.getOutputStream());
//    	flujoSalida.writeUTF(mensaje);
    	red = (Red) flujoEntradaObjeto.readObject();
    	System.out.println("Se recibio la red");
    	return red;
   }

	

}
