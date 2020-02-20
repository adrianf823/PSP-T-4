package Actividad_4_1;
import java.io.IOException;

import org.apache.commons.net.ftp.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		FTPClient cliente=new FTPClient();
		String servFTP="localhost";
		System.out.println("Nos conectamos a: "+servFTP);
		String usuario="usuario";
		String clave="usuario";
		
		String directorio="/Descargas/";
		
		try {
			cliente.connect(servFTP);
			cliente.enterLocalPassiveMode();
			
			boolean login=cliente.login(usuario, clave);
			
			if(login)
				System.out.println("Login correcto");
			else {
				System.out.println("Login incorrecto");
				cliente.disconnect();
				System.exit(1);
			}
			System.out.println("Directorio actual: "+cliente.printWorkingDirectory());
			FTPFile[] files=cliente.listFiles();
			System.out.println("Ficheros en el directorio actual: "+files.length);
			
			String tipos[]= {"Fichero","Directorio","Enlace simb."};
			
			for (int i = 0; i < files.length; i++) {
				System.out.println("\t"+files[i].getName()+" => "+tipos[files[i].getType()]);
			}
			
			
			cliente.changeWorkingDirectory(directorio);
			System.out.println("Directorio actual: "+cliente.printWorkingDirectory());
			files=cliente.listFiles();
			System.out.println("Ficheros en el directorio actual: "+files.length);
			
			for (int i = 0; i < files.length; i++) {
				System.out.println("\t"+files[i].getName()+" => "+tipos[files[i].getType()]);
			}
			
			boolean logout=cliente.logout();
			
			if(logout)
				System.out.println("Logout del servidor FTP");
			else
				System.out.println("Error al hacer logout");
			cliente.disconnect();
			System.out.println("Desconectado");
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
