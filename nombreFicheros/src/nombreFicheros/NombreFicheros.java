package nombreFicheros;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class NombreFicheros {
	
	static BufferedWriter bw;
	static Writer escribe;
	static int pos=0; 
	public static void main(String[] args) {
		String ruta="C:/users/usuario/desktop/hactool/ficheros";
		try {
			escribe=new FileWriter(ruta+"/ficheros2.txt");
			bw=new BufferedWriter(escribe);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		File[] listaficheros=new File(ruta).listFiles();
		int fin=0;
		int cfic=0;
		while(fin==0) {
			for(int i=0;i<listaficheros.length;i++) {
				
				
				if(listaficheros[i].toString().contains("dat")) {
					cfic++;
					System.out.println("ffsnt.exe "+listaficheros[i].getName());
					try {
						escribe.write(listaficheros[i].getName());
						escribe.write("\r\n");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(i==listaficheros.length-1) {
					fin=1;
					System.out.println("Hay "+cfic+" ficheros.");
				}

			
		}
		System.out.println("Fichero creado con éxito");
		
		
		}

	}

}
