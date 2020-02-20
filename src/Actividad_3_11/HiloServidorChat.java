package Actividad_3_11;
import java.net.*;
import java.io.*;

public class HiloServidorChat extends Thread{

	DataInputStream fentrada;
	Socket socket=null;
	ComunHilos comun;
	
	public HiloServidorChat(Socket s, ComunHilos comun) {
		this.socket=s;
		this.comun=comun;
		try {
			fentrada=new DataInputStream(socket.getInputStream());
		}catch(IOException e){
			System.out.println("ERROR DE E/S");
			e.printStackTrace();
		}
	}
	
	public void run() {
		System.out.println("NUMERO DE CONEXIONES ACTUALES: "+comun.getACTUALES());
		
		String texto=comun.getMensajes();
		EnviarMensajesaTodos(texto);
		
		while(true) {
			String cadena="";
			try {
				cadena=fentrada.readUTF();
				if(cadena.trim().equals("*")) {
					comun.setACTUALES(comun.getACTUALES()-1);
					System.out.println("NUMERO DE CONEXIONES ACTUALES: "+comun.getACTUALES());
					break;
				}
				comun.setMensajes(comun.getMensajes()+cadena+"\n");
				EnviarMensajesaTodos(comun.getMensajes());
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				break;
			}
		}
		
		try {
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void EnviarMensajesaTodos(String texto) {
		for (int i = 0; i < comun.getCONEXIONES(); i++) {
			Socket s1=comun.getElementoTabla(i);
			if(!s1.isClosed()) {
				try {
					DataOutputStream fsalida2=new DataOutputStream(s1.getOutputStream());
					fsalida2.writeUTF(texto);
				} catch (IOException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
	}
	
}
