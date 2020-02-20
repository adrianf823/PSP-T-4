package Actividad_3_11;
import java.net.Socket;

public class ComunHilos {
	
	int CONEXIONES;
	int ACTUALES;
	int MAXIMO;
	Socket tabla[]=new Socket[MAXIMO];
	String mensajes;
	
	public ComunHilos(int maximo, int actuales, int conexiones, Socket[] tabla) {
		// TODO Auto-generated constructor stub
		MAXIMO=maximo;
		ACTUALES=actuales;
		CONEXIONES=conexiones;
		this.tabla=tabla;
		mensajes="";
	}
	
	public ComunHilos() {
		// TODO Auto-generated constructor stub
		super();
	}

	public int getCONEXIONES() {
		return CONEXIONES;
	}

	public synchronized void setCONEXIONES(int cONEXIONES) {
		CONEXIONES = cONEXIONES;
	}

	public int getACTUALES() {
		return ACTUALES;
	}

	public synchronized void setACTUALES(int aCTUALES) {
		ACTUALES = aCTUALES;
	}

	public int getMAXIMO() {
		return MAXIMO;
	}

	public synchronized void setMAXIMO(int mAXIMO) {
		MAXIMO = mAXIMO;
	}

	public Socket getElementoTabla(int i) {
		return tabla[i];
	}

	public synchronized void addTabla(Socket s, int i) {
		tabla[i]=s;
	}

	public String getMensajes() {
		return mensajes;
	}

	public synchronized void setMensajes(String mensajes) {
		this.mensajes = mensajes;
	}
	
	
}
