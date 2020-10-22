import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Formatter;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.autentia.utils.ACharsetDetector;

public class convierteLibro {

	private JFrame frmCambiarFormatoDe;
	private JTextField entrada;
	private JTextField salida;
    private String ruta;
    private String nombre;
    private int pos;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    
    
    
    
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					convierteLibro window = new convierteLibro();
					window.frmCambiarFormatoDe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public convierteLibro() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCambiarFormatoDe = new JFrame();
		frmCambiarFormatoDe.setTitle("Cambiar formato de fichero");
		frmCambiarFormatoDe.setBounds(100, 100, 629, 322);
		frmCambiarFormatoDe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCambiarFormatoDe.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Fichero de entrada:");
		lblNewLabel.setBounds(74, 64, 131, 14);
		frmCambiarFormatoDe.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Fichero de salida:");
		lblNewLabel_1.setBounds(74, 113, 131, 14);
		frmCambiarFormatoDe.getContentPane().add(lblNewLabel_1);
		
		entrada = new JTextField();
		entrada.setBounds(215, 61, 189, 20);
		frmCambiarFormatoDe.getContentPane().add(entrada);
		entrada.setColumns(10);
		
		salida = new JTextField();
		salida.setEditable(false);
		salida.setBounds(215, 110, 189, 20);
		frmCambiarFormatoDe.getContentPane().add(salida);
		salida.setColumns(10);
		
		JRadioButton epub = new JRadioButton("epub");
		buttonGroup.add(epub);
		epub.setBounds(74, 172, 109, 23);
		frmCambiarFormatoDe.getContentPane().add(epub);
		
		JRadioButton mobi = new JRadioButton("mobi");
		buttonGroup.add(mobi);
		mobi.setBounds(184, 172, 109, 23);
		frmCambiarFormatoDe.getContentPane().add(mobi);
		
		JRadioButton fb2 = new JRadioButton("fb2");
		buttonGroup.add(fb2);
		fb2.setBounds(294, 172, 109, 23);
		frmCambiarFormatoDe.getContentPane().add(fb2);
		
		JRadioButton pdf = new JRadioButton("pdf");
		buttonGroup.add(pdf);
		pdf.setBounds(404, 172, 109, 23);
		frmCambiarFormatoDe.getContentPane().add(pdf);
		
		
		JButton btnNewButton = new JButton("Examinar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
		        fileChooser.showOpenDialog(fileChooser);
		        try {
		           ruta = fileChooser.getSelectedFile().getAbsolutePath();
		           entrada.setText(ruta);
		           pos=ruta.indexOf(".");
		           nombre=ruta.substring(0,pos-1);

		           


		        } catch (NullPointerException e) {
		            System.out.println("No se ha seleccionado ningún fichero");
		        } catch (Exception e) {
		            System.out.println(e.getMessage());
		        } 
		        }
				
			});
		btnNewButton.setBounds(414, 60, 89, 23);
		frmCambiarFormatoDe.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Convertir");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(entrada.getText().length()==0) {
					System.out.println("Seleccione un archivo.");
				}else{
				
				
				if(epub.isSelected()==true){
					salida.setText(nombre+"."+epub.getText());	
				}else if(mobi.isSelected()==true){
					salida.setText(nombre+"."+mobi.getText());
				}else if(pdf.isSelected()==true){
					salida.setText(nombre+"."+pdf.getText());
				}else if(fb2.isSelected()==true){
					salida.setText(nombre+"."+fb2.getText());
				}
				
				try
				{
		             // Se abre el fichero original para lectura
					InputStreamReader isr=new InputStreamReader(new FileInputStream(ruta));
					ACharsetDetector codificacion = new ACharsetDetector(ruta);
					codificacion.detect();
					if(codificacion.isDetectedCharset()==true)
						System.out.println(codificacion.getDetectedCharset());
					
					// Se abre el fichero donde se har� la copia
					OutputStreamWriter osw=new OutputStreamWriter(new FileOutputStream(salida.getText(),true),"ISO-8859-1");
					
					// Bucle para leer de un fichero y escribir en el otro.
					char[] car = new char[1024];
					String linea=car.toString();
					int leidos = isr.read();
					while (leidos > 0)
					{
							osw.write(car,0,leidos);
							leidos=isr.read();
							
							
					}

					// Cierre de los ficheros
					isr.close();
					osw.close();
					System.out.println("fichero terminado");
					
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
				
				
			}
			});
		btnNewButton_2.setBounds(265, 227, 89, 23);
		frmCambiarFormatoDe.getContentPane().add(btnNewButton_2);
		
		
		
		
		
		
	}
}
