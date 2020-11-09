package com.audio.youtube.DescargarMP3Youtube;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.sapher.youtubedl.YoutubeDL;
import com.sapher.youtubedl.YoutubeDLException;
import com.sapher.youtubedl.YoutubeDLRequest;
import com.sapher.youtubedl.YoutubeDLResponse;

/*Para que la aplicación funcione es necesario tener instalado youtube-dl
1-Instalar python
2- Descargar ffmpeg y pegar contenido en :
C:\Users\Usuario\AppData\Local\Programs\Python\Python39\Scripts
*/

public class Youtube {

	private JFrame frmDescargarMP3;
	static JTextField descarga;
	static JTextField enlace;
    File archivo;
    String ruta;
    BufferedReader br;
    YoutubeDLResponse response;
    static YoutubeDLRequest request;
    static File nombreNuevo;
    
    private final ButtonGroup buttonGroup = new ButtonGroup();
    
   
    
    
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Youtube window = new Youtube();
					window.frmDescargarMP3.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Youtube() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDescargarMP3 = new JFrame();
		frmDescargarMP3.setTitle("Descargar canciones youtube");
		frmDescargarMP3.setBounds(100, 100, 629, 322);
		frmDescargarMP3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDescargarMP3.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ruta de descarga:");
		lblNewLabel.setBounds(74, 64, 131, 14);
		frmDescargarMP3.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enlace:");
		lblNewLabel_1.setBounds(74, 113, 131, 14);
		frmDescargarMP3.getContentPane().add(lblNewLabel_1);
		
		descarga = new JTextField();
		descarga.setBounds(215, 61, 189, 20);
		frmDescargarMP3.getContentPane().add(descarga);
		descarga.setColumns(10);
		
		enlace = new JTextField();
		enlace.setBounds(215, 110, 189, 20);
		frmDescargarMP3.getContentPane().add(enlace);
		enlace.setColumns(10);
		
		final JRadioButton reproduccion = new JRadioButton("Lista de reproducción");
		buttonGroup.add(reproduccion);
		reproduccion.setBounds(124, 172, 162, 23);
		frmDescargarMP3.getContentPane().add(reproduccion);
		
		final JRadioButton cancion = new JRadioButton("Canción");
		buttonGroup.add(cancion);
		cancion.setBounds(347, 172, 109, 23);
		frmDescargarMP3.getContentPane().add(cancion);
		
		
		JButton btnNewButton = new JButton("Examinar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		        fileChooser.showOpenDialog(fileChooser);
		        try {
		           ruta = fileChooser.getSelectedFile().getAbsolutePath();
		           descarga.setText(ruta.toString());
		          

		           


		        } catch (NullPointerException e) {
		            System.out.println("No se ha seleccionado ningún fichero");
		        } catch (Exception e) {
		            System.out.println(e.getMessage());
		        } 
		        }
				
			});
		btnNewButton.setBounds(414, 60, 89, 23);
		frmDescargarMP3.getContentPane().add(btnNewButton);
		
		JButton btnNewButton2 = new JButton("Descargar");
		btnNewButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					//Html.saveHTML(enlace.getText(), "titulo");
					//archivo=new File(descarga.getText()+"/"+Html.tituloCancion(enlace.getText())+".mp3");
					if(descarga.getText().length()==0) {
						JOptionPane.showMessageDialog(null, "Seleccione un directorio para descargar canción/es.","Directorio", JOptionPane.WARNING_MESSAGE);
					}else if(enlace.getText().length()==0){
						JOptionPane.showMessageDialog(null, "Introduzca un enlace de youtube para descargar canción/es.","Enlace", JOptionPane.WARNING_MESSAGE);
					}
					//System.out.println(creada);
					if(reproduccion.isSelected()==true){
						int c=0;
						File fic;
						File html;
						String titulo="";
						List<String>ids=new ArrayList<String>();
						List<String> titulos=new ArrayList<String>();
						try {	
							request=new YoutubeDLRequest(enlace.getText(),descarga.getText().trim());
							request.setOption("ignore-errors");
							request.setOption("extract-audio");
							request.setOption("audio-format mp3");
							request.setOption("output", "%(id)s.%(ext)s");
							do {
								ids.add(request.getUrl().split("&list=")[0].substring(request.getUrl().split("&list=")[0].length()-11));
								html=new File(Html.saveHTML(request.getUrl().split("&list=")[0],descarga.getText()+"/titulo.html"));
								if(html.exists()==false) {
									html=new File(Html.saveHTML("https://www.youtube.com/watch?v=_"+ids.get(c),descarga.getText()+"/titulo.html"));
									System.out.println("Fichero creado");
								}else {
									Path path=FileSystems.getDefault().getPath(Youtube.descarga.getText()+"/titulo.html");
									System.out.println(path);
									try {
										Files.delete(path);
										System.out.println("Fichero borrado");
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								titulo=Html.tituloCancion("https://www.youtube.com/watch?v=_"+ids.get(c));
								titulos.add(titulo);
								System.out.println(titulo);
								YoutubeDL.execute(request);
								c++;
							}while(titulo!=null);
							
							
							for(c=0;c<ids.size();c++) {
								nombreNuevo=new File(descarga.getText()+"/"+titulos.get(c)+".mp3");
								System.out.println(nombreNuevo.getName());
								fic=new File(descarga.getText()+"/"+ids.get(c)+".mp3");
								System.out.println(fic.getName());
								fic.renameTo(nombreNuevo);
							}

						} catch (NullPointerException npe) {
								npe.getStackTrace();
							// TODO Auto-generated catch block
							
						}catch (YoutubeDLException e) {
							//JOptionPane.showMessageDialog(null, e.getMessage(),"Causa", JOptionPane.ERROR_MESSAGE);
							
						}
								
						

					}else if(cancion.isSelected()==true){
						File html;
						String titulo="";
						request=new YoutubeDLRequest(enlace.getText().trim(), descarga.getText().trim());
						request.setOption("ignore-errors");
						request.setOption("extract-audio");
						request.setOption("audio-format mp3");
						request.setOption("output", "%(id)s.%(ext)s");
							//System.out.println("Descargando MP3");
						System.out.println(request.getUrl());
						try {
							html=new File(Html.saveHTML(request.getUrl(),descarga.getText()+"/titulo.html"));
							titulo=Html.tituloCancion(request.getUrl());
							System.out.println(titulo);
							YoutubeDL.execute(request);
							nombreNuevo=new File(descarga.getText()+"/"+titulo+".mp3");
							File archivo2=new File(descarga.getText()+"/"+enlace.getText().substring(enlace.getText().length()-11)+".mp3");
							System.out.println(nombreNuevo+" "+archivo2);
							archivo2.renameTo(nombreNuevo);
							System.out.println(html.getAbsolutePath());
							html=new File(Html.saveHTML(request.getUrl(), "titulo.html"));
							html.delete();
							JOptionPane.showMessageDialog(null, "Canción descargada con éxito.");
						} catch (YoutubeDLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
								
					}else {
						JOptionPane.showMessageDialog(null, "Seleccione la opción lista de reproducción o canción.","Enlace", JOptionPane.WARNING_MESSAGE);
					
				
					}
					
					JButton btnNewButton_1 = new JButton("Borrar");
					btnNewButton_1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							enlace.setText("");
						}
					});
					btnNewButton_1.setBounds(414, 109, 89, 23);
					frmDescargarMP3.getContentPane().add(btnNewButton_1);
			
			
		}
			});
		btnNewButton2.setBounds(246, 238, 109, 23);
		frmDescargarMP3.getContentPane().add(btnNewButton2);
		
		
		
		JButton btnNewButton_3 = new JButton("Limpiar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(enlace.getText().length()>0)
					enlace.setText("");
			}
		});
		btnNewButton_3.setBounds(414, 109, 89, 23);
		frmDescargarMP3.getContentPane().add(btnNewButton_3);
		

}
}

