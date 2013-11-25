package logica;

import java.awt.HeadlessException;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;



public class Conexion implements Runnable{

	private Socket socketConex;
	private DataInputStream dataInputStream;//canal de entrada 
	private DataOutputStream dataOutputStream;//canal de salida
	private boolean estado;
	private int opcion = Integer.MAX_VALUE;
	private String mensaje;


	public Conexion(Socket socketNew) {

		this.socketConex = socketNew;

		try {
			dataInputStream = new DataInputStream(socketConex.getInputStream());

			JOptionPane.showMessageDialog(null, "Creado canal de entrada");

		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			dataOutputStream = new DataOutputStream(socketConex.getOutputStream());
			
			JOptionPane.showMessageDialog(null, "Creado canal de salida");

		} catch (IOException e) {
			e.printStackTrace();
		}
		iniciarConexion(1);
		
		Thread hilo = new Thread(this);
		hilo.start();
	}

	public  void run() {
		while (!estado) {

			try {
				
				opcion = dataInputStream.readInt();

			} catch (IOException e) {
				e.printStackTrace();
			} 
			switch (opcion) {
			case 1:
				try {
					
					JOptionPane.showMessageDialog(null, dataInputStream.readUTF());
					
				} catch (HeadlessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 0:
				enviarMensaje(mensaje);
				break;

			case 3:

				break;

			default:

				break;
			}
			System.out.println("Esperando Conexiones...en conexion");

		}	
	}

	public void iniciarConexion (int n){
		try {
			dataOutputStream.writeInt(n);//envio de opcion o bandera
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		try {
			
			dataOutputStream.writeUTF("se digito la opcion: asdfasfa ");//envio del mensaje con esa opcion
			
		} catch (IOException e) {
			
			
			e.printStackTrace();
		}	
	}

	public void enviarMensaje(String mensaje){
		try {
			dataOutputStream.writeUTF(mensaje);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void cerrarConexion(){
		try {
			dataInputStream.close();
			JOptionPane.showMessageDialog(null, "Cerrado canal de entrada");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			dataOutputStream.close();
			JOptionPane.showMessageDialog(null, "Cerrado canal de salida");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			socketConex.close();
			JOptionPane.showMessageDialog(null, "Cerrado el cliente");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Socket getSocketConex() {
		return socketConex;
	}

	public void setSocketConex(Socket socketConex) {
		this.socketConex = socketConex;
	}

	public DataInputStream getDataInputStream() {
		return dataInputStream;
	}

	public void setDataInputStream(DataInputStream dataInputStream) {
		this.dataInputStream = dataInputStream;
	}

	public DataOutputStream getDataOutputStream() {
		return dataOutputStream;
	}

	public void setDataOutputStream(DataOutputStream dataOutputStream) {
		this.dataOutputStream = dataOutputStream;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public int getOpcion() {
		return opcion;
	}

	public void setOpcion(int opcion) {
		this.opcion = opcion;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
}
