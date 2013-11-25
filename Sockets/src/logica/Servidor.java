package logica;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Servidor implements Runnable{
	private ServerSocket serverSocket;
	private ArrayList<Conexion> listConexion;
	private Socket conexionEntrante;
	private String direcionIp;
	private int puerto;
	private Concierto concierto;
	private boolean estado;
	private String estadisticas;


	public Servidor() {
		
		puerto = 4450;
		listConexion = new ArrayList<Conexion>();
		Thread hilo = new Thread(this);
		
		hilo.start();
		
	}

	public void run() {
		while (!estado) {
			
			System.out.println("Esperando Conexiones... en servidor");
			
			try {
				
				conexionEntrante = serverSocket.accept();
				
				listConexion.add(new Conexion(conexionEntrante));
//				System.out.println("conexiones : "+ listConexion.size());
				
//				JOptionPane.showMessageDialog(null, "Nueva conexion aceptada");
				
			} catch (IOException e) {
				
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
			
		}		
	}
	
	public void iniciarServidor() throws IOException{
		if (serverSocket==null) {
			serverSocket  = new ServerSocket(puerto);
		}
	}
	public void cerrarConexion(){
		for (int i = 0; i < listConexion.size(); i++) {
			listConexion.get(i).cerrarConexion();
		}
		estado = false;
	}
	public void estServidor(){
		
	}
	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	public ArrayList<Conexion> getListConexion() {
		return listConexion;
	}

	public void setListConexion(ArrayList<Conexion> listConexion) {
		this.listConexion = listConexion;
	}

	public Socket getConexionEntrante() {
		return conexionEntrante;
	}

	public void setConexionEntrante(Socket conexionEntrante) {
		this.conexionEntrante = conexionEntrante;
	}

	public String getDirecionIp() {
		return direcionIp;
	}

	public void setDirecionIp(String direcionIp) {
		this.direcionIp = direcionIp;
	}

	public int getPuerto() {
		return puerto;
	}

	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public String getEstadisticas() {
		return estadisticas;
	}

	public void setEstadisticas(String estadisticas) {
		this.estadisticas = estadisticas;
	}
	
}
