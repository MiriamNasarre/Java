package nombreFicheros;

import java.io.File;


public class CambiarNombre {
		public static void main(String[] args) {
			String ruta="C:\\Users\\Usuario\\Desktop\\hactool\\CompressedFiles";		
			File[] listaficheros=new File(ruta).listFiles();
			for(int i=0;i<listaficheros.length;i++) {
					String [] cmd= {"ren",(char)34+ruta+"\\"+listaficheros[i].getName()+(char)34,(char)34+listaficheros[i].getName().substring(0,listaficheros[i].getName().length()-3)+(char)34}; 
					System.out.println(cmd[0]+" "+cmd[1]+" "+cmd[2]);
					if(i==listaficheros.length)
						System.out.println("Se han renombrado "+(i+1)+" ficheros en "+ruta+".");
					
					
				}
			
			}

	}

