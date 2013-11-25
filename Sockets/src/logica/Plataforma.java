package logica;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Plataforma {
	
	private ArrayList<Servidor> listServer;
	private ServerSocket serverSocket;
	private ArrayList<Conexion> listConexion;
	private Socket conexionEntrante;
	private String direcionIp;
	private int puerto;
	private Concierto concierto;
	private boolean estado;
	private String estadisticas;

}
