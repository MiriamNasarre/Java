package nombreFicheros;

import java.io.File;
import java.io.IOException;


public class CambiarNombre {
		public static void main(String[] args) {
			String ruta="E:\\mis pelis\\Fire Emblem Modelos";		
			File[] listaficheros=new File(ruta).listFiles();
			ProcessBuilder pb;
			for(int i=0;i<listaficheros.length;i++) {
					String [] cmd= {"ren",(char)34+ruta+"\\"+listaficheros[i].getName()+(char)34,(char)34+listaficheros[i].getName().substring(0,listaficheros[i].getName().length()-3)+(char)34};
					pb = new ProcessBuilder("cmd.exe", "/c", cmd[0],cmd[1],cmd[2]);
					try {
						pb.start();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//if(cmd[1].toString().contains("bin") && !cmd[1].toString().contains("gz") && cmd[1].toString().contains("3")  )
					System.out.println(cmd[0]+" "+cmd[1]+" "+cmd[2]);
					if(i==listaficheros.length-1)
						System.out.println("Se han renombrado "+(i+1)+" ficheros en "+ruta+".");
					
					//System.out.println("fethdeflate.exe "+(char)34+"file "+i+".bin.gz"+(char)34);
				}
			
			}

	}

