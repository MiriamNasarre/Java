package com.audio.youtube.DescargarMP3Youtube;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class Html {
	static URL url;
	static HttpURLConnection conn;
	static BufferedReader rd;
	static BufferedWriter bw;
	static int c=0;
	static String fin;
	static File archivo;
	static boolean creada=false;
	
	static String saveHTML(String html,String nombre) {
		StringBuilder result = new StringBuilder();
		try {
			url=new URL(Youtube.enlace.getText());
			conn=(HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			archivo=new File(nombre); 
			bw=new BufferedWriter(new FileWriter(archivo,false));
			String line;
			while ((line = rd.readLine()) != null) {
				bw.write(line+"\n");
				//System.out.println(line);
				line=rd.readLine();
			}
			rd.close();
			bw.close();
		} catch (Exception e) {
			result = new StringBuilder();
		}
		return result.toString();
	}

	public static String tituloCancion (String url){
		//saveHTML(url,Youtube.descarga.getText()+"/titulo.html");
		String buscar="<meta name=\"title\"";
		String buscar2="{\"videoId\" : ";
		String fin="\">";
		String titulo = "";
		String linea;
		
		try {
			BufferedReader  br = new BufferedReader(new FileReader(archivo));
			while((linea=br.readLine())!=null) {
				//System.out.println("Buscando titulo");
				if(linea.contains(buscar) | linea.contains(buscar2)) {
					titulo=linea.substring(linea.indexOf(buscar)+28,linea.indexOf(fin)+1);
					if(titulo.contains("'")| titulo.contains(".")| titulo.contains("|")| titulo.contains("?")| titulo.contains("!")| titulo.contains("¡")
							|titulo.contains("&#39;")|titulo.contains("\"")|titulo.contains("&quot;")|titulo.contains("/")){
						titulo=titulo.replace("'","");
						titulo=titulo.replace(".", "");
						titulo=titulo.replace("|", "");
						titulo=titulo.replace("?", "");
						titulo=titulo.replace("!", "");
						titulo=titulo.replace("¡", "");
						titulo=titulo.replace("&#39;", "");
						titulo=titulo.replace("\"", "");
						titulo=titulo.replace("&quot;", "");
						titulo=titulo.replace("/", "");
					}else{
						System.out.println(linea+"\n");
						linea=br.readLine();
					}
					//System.out.println("Titulo: "+titulo);
				}
				
				
				
			}
			br.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		return titulo;
			
	}	
	
	}



