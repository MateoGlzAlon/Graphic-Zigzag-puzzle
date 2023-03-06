package zizzaggrafico;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class Ventana {

	private static JFrame frame;
	private JTextField textField_NumColumnas;
	private JTextField textField_numFilas;
	private JTextField textField_FileName;
	private JTextField textField_SaveFile;
	
	public  static JButton[][] botones;
	private JTextField[][] camposTexto;
	public static JLabel[][] simbolos;
	
		
	public static JPanel panelPuzzle;
	GridBagConstraints gbc_panelPuzzle;
	GridLayout gl_panelPuzzle;
	
	public static int filas;
	public static int columnas;
	private static int filasAmpliadas = filas*2 -1;
	private static int columnasAmpliadas = columnas*2-1;
	
	private static int max;
	private static int min;
	
	private static String[][] tablaAmpliada = new String[filas][columnas];
	
	public static ArrayList<Casilla> camino;
	public static ArrayList<Casilla> caminoAux;

	
	public static int elementosRecorridos;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					Ventana window = new Ventana();
					window.frame.setVisible(true);
				} catch (Exception e) {
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ventana() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBackground(Color.BLACK);
		frame.setBounds(100, 100, 938, 579);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panelBotones = new JPanel();
		panelBotones.setBounds(546, 11, 372, 525);
		frame.getContentPane().add(panelBotones);
		GridBagLayout gbl_panelBotones = new GridBagLayout();
		gbl_panelBotones.columnWidths = new int[]{0, 0, 0};
		gbl_panelBotones.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0};
		gbl_panelBotones.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panelBotones.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
		panelBotones.setLayout(gbl_panelBotones);
		
		JLabel lblNumFilas = new JLabel("FILAS");
		GridBagConstraints gbc_lblNumFilas = new GridBagConstraints();
		gbc_lblNumFilas.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumFilas.gridx = 0;
		gbc_lblNumFilas.gridy = 0;
		panelBotones.add(lblNumFilas, gbc_lblNumFilas);
		
		textField_numFilas = new JTextField();
		Font font1 = new Font("SansSerif", Font.BOLD, 20);		 
		textField_numFilas.setFont(font1);
		textField_numFilas.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_textField_numFilas = new GridBagConstraints();
		gbc_textField_numFilas.insets = new Insets(0, 0, 5, 0);
		gbc_textField_numFilas.fill = GridBagConstraints.BOTH;
		gbc_textField_numFilas.gridx = 1;
		gbc_textField_numFilas.gridy = 0;
		panelBotones.add(textField_numFilas, gbc_textField_numFilas);
		textField_numFilas.setColumns(10);
		
		JLabel lblNumColumnas = new JLabel("COLUMNAS");
		GridBagConstraints gbc_lblNumColumnas = new GridBagConstraints();
		gbc_lblNumColumnas.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumColumnas.gridx = 0;
		gbc_lblNumColumnas.gridy = 1;
		panelBotones.add(lblNumColumnas, gbc_lblNumColumnas);
		
		textField_NumColumnas = new JTextField();
		textField_NumColumnas.setFont(font1);
		textField_NumColumnas.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_textField_NumColumnas = new GridBagConstraints();
		gbc_textField_NumColumnas.insets = new Insets(0, 0, 5, 0);
		gbc_textField_NumColumnas.fill = GridBagConstraints.BOTH;
		gbc_textField_NumColumnas.gridx = 1;
		gbc_textField_NumColumnas.gridy = 1;
		panelBotones.add(textField_NumColumnas, gbc_textField_NumColumnas);
		textField_NumColumnas.setColumns(10);
		
		JButton btnFijarFilasYColumnas = new JButton("FIJAR FILAS Y COLUMNAS");
		btnFijarFilasYColumnas.setFont(new Font("Calibri", Font.PLAIN, 15));
		btnFijarFilasYColumnas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearCeldas();
			}
		});
		GridBagConstraints gbc_btnFijarFilasYColumnas = new GridBagConstraints();
		gbc_btnFijarFilasYColumnas.fill = GridBagConstraints.BOTH;
		gbc_btnFijarFilasYColumnas.insets = new Insets(0, 0, 5, 5);
		gbc_btnFijarFilasYColumnas.gridx = 0;
		gbc_btnFijarFilasYColumnas.gridy = 2;
		panelBotones.add(btnFijarFilasYColumnas, gbc_btnFijarFilasYColumnas);
		
		JButton btnFijarValores = new JButton("FIJAR VALORES");
		btnFijarValores.setFont(new Font("Calibri", Font.PLAIN, 15));
		GridBagConstraints gbc_btnFijarValores = new GridBagConstraints();
		gbc_btnFijarValores.fill = GridBagConstraints.BOTH;
		gbc_btnFijarValores.insets = new Insets(0, 0, 5, 0);
		gbc_btnFijarValores.gridx = 1;
		gbc_btnFijarValores.gridy = 2;
		btnFijarValores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				introducirEnMatriz();
				if (comprobaciones()) cambiarCeldasPorBotones();
			}
		});
		panelBotones.add(btnFijarValores, gbc_btnFijarValores);
		
		JButton btnMostrarSoluciones = new JButton("MOSTRAR SOLUCIONES");
		btnMostrarSoluciones.setFont(new Font("Calibri", Font.PLAIN, 15));
		GridBagConstraints gbc_btnMostrarSoluciones = new GridBagConstraints();
		gbc_btnMostrarSoluciones.fill = GridBagConstraints.BOTH;
		gbc_btnMostrarSoluciones.insets = new Insets(0, 0, 5, 5);
		gbc_btnMostrarSoluciones.gridx = 0;
		gbc_btnMostrarSoluciones.gridy = 3;
		
		btnMostrarSoluciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				introducirEnMatriz();
				comprobaciones();				
				JOptionPane.showMessageDialog(frame,mostrarSoluciones());
			}
		});
		panelBotones.add(btnMostrarSoluciones, gbc_btnMostrarSoluciones);
		
		JButton btnInstrucciones = new JButton("INSTRUCCIONES");
		btnInstrucciones.setFont(new Font("Calibri", Font.PLAIN, 15));
		btnInstrucciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame,"El objetivo del juego es recorrer todos los elementos del puzle con estas condiciones:\r\n"
						+ "- Se debe empezar en la casilla superior izquierda y acabar en la inferior derecha\r\n"
						+ "- Solo se puede saltar a la siguiente casilla si es el siguiente numero o si se pasa del máximo al mínimo \r\n"
						+ "- No se deben cruzar los símbolos\r\n"
						+ "Puede haber 0 soluciones, 1 solución o múltiples soluciones\r\n"
						+ "\r\n"
						+ "INSTRUCCIONES\r\n"
						+ "\n- Para crear un nuevo puzzle\r\n"
						+ "1. Escribe el numero de filas y columnas y pulsa el botón FIJAR FILAS Y COLUMNAS\r\n"
						+ "2. Rellena las celdas con tus números y pulsa el botón FIJAR VALORES\r\n"
						+ "3. Si quieres ver todas las soluciones, pulsa el botón MOSTRAR SOLUCIONES\r\n"
						+ "4. Para unir las casillas, tan solo debes pulsar tu camino en el orden que quieras, si la casilla es válida, se pondrá en verde, si no lo es, saldrá un mensaje de error.\r\n"
						+ "5. Para guardar el puzle en un fichero, tan solo escribe el nombre de tu fichero en el campo de texto al lado del botón GUARDAR EN FICHERO (ej: f1.txt) y pulsa el botón \r\n"
						+ "\n- Para recuperar un fichero:\r\n"
						+ "1. Para recuperar el puzle de un fichero, tan solo escribe el nombre de tu fichero en el campo de texto al lado del botón RECUPERAR DE FICHERO (ej: f1.txt) y pulsa el botón\r\n"
						+ "2.Pulsa en FIJAR VALORES y todo funciona como lo anterior\r\n"
						+ "\r\n"
						+ "El botón rehacer y deshacer solo modifican el recorrido, en caso de querer cambiar los valores, debes hacerlo como si fuese un puzle nuevo o cargando un fichero\r\n"
						+ "");

			}
		});
		
		GridBagConstraints gbc_btnInstrucciones = new GridBagConstraints();
		gbc_btnInstrucciones.insets = new Insets(0, 0, 5, 0);
		gbc_btnInstrucciones.fill = GridBagConstraints.BOTH;
		gbc_btnInstrucciones.gridx = 1;
		gbc_btnInstrucciones.gridy = 3;
		panelBotones.add(btnInstrucciones, gbc_btnInstrucciones);
		
		JButton btnGuardarFichero = new JButton("GUARDAR EN FICHERO");
		btnGuardarFichero.setFont(new Font("Calibri", Font.PLAIN, 15));
		btnGuardarFichero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					guardarEnArchivo(mostrarMatrizEnTexto());
			}
		});
		GridBagConstraints gbc_btnGuardarFichero = new GridBagConstraints();
		gbc_btnGuardarFichero.fill = GridBagConstraints.BOTH;
		gbc_btnGuardarFichero.insets = new Insets(0, 0, 5, 5);
		gbc_btnGuardarFichero.gridx = 0;
		gbc_btnGuardarFichero.gridy = 4;
		panelBotones.add(btnGuardarFichero, gbc_btnGuardarFichero);
		
		textField_FileName = new JTextField();
		textField_FileName.setFont(new Font("Calibri", Font.PLAIN, 15));
		textField_FileName.setHorizontalAlignment(SwingConstants.CENTER);
		textField_FileName.setText("<nombrefichero.txt>");
		GridBagConstraints gbc_textField_FileName = new GridBagConstraints();
		gbc_textField_FileName.insets = new Insets(0, 0, 5, 0);
		gbc_textField_FileName.fill = GridBagConstraints.BOTH;
		gbc_textField_FileName.gridx = 1;
		gbc_textField_FileName.gridy = 4;
		panelBotones.add(textField_FileName, gbc_textField_FileName);
		textField_FileName.setColumns(10);
		
		JButton btnRecuperarFichero = new JButton("RECUPERAR DE FICHERO");
		btnRecuperarFichero.setFont(new Font("Calibri", Font.PLAIN, 15));
		btnRecuperarFichero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				try {
					recuperarDeArchivo(textField_SaveFile.getText());
				} catch (IOException e1) {
					
				}
			}
		});
		GridBagConstraints gbc_btnRecuperarFichero = new GridBagConstraints();
		gbc_btnRecuperarFichero.insets = new Insets(0, 0, 5, 5);
		gbc_btnRecuperarFichero.fill = GridBagConstraints.BOTH;
		gbc_btnRecuperarFichero.gridx = 0;
		gbc_btnRecuperarFichero.gridy = 5;
		panelBotones.add(btnRecuperarFichero, gbc_btnRecuperarFichero);
		
		textField_SaveFile = new JTextField();
		textField_SaveFile.setFont(new Font("Calibri", Font.PLAIN, 15));
		textField_SaveFile.setText("<nombrefichero.txt>");
		textField_SaveFile.setHorizontalAlignment(SwingConstants.CENTER);
		textField_SaveFile.setColumns(10);
		GridBagConstraints gbc_textField_SaveFile = new GridBagConstraints();
		gbc_textField_SaveFile.insets = new Insets(0, 0, 5, 0);
		gbc_textField_SaveFile.fill = GridBagConstraints.BOTH;
		gbc_textField_SaveFile.gridx = 1;
		gbc_textField_SaveFile.gridy = 5;
		panelBotones.add(textField_SaveFile, gbc_textField_SaveFile);
		
		JButton btnRehacer = new JButton("REHACER");
		btnRehacer.setFont(new Font("Calibri", Font.PLAIN, 15));
		btnRehacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rehacer();
			}
		});
		GridBagConstraints gbc_btnRehacer = new GridBagConstraints();
		gbc_btnRehacer.fill = GridBagConstraints.BOTH;
		gbc_btnRehacer.insets = new Insets(0, 0, 5, 5);
		gbc_btnRehacer.gridx = 0;
		gbc_btnRehacer.gridy = 6;
		panelBotones.add(btnRehacer, gbc_btnRehacer);
		
		JButton btnDeshacer = new JButton("DESHACER");
		btnDeshacer.setFont(new Font("Calibri", Font.PLAIN, 15));
		GridBagConstraints gbc_btnDeshacer = new GridBagConstraints();
		gbc_btnDeshacer.insets = new Insets(0, 0, 5, 0);
		gbc_btnDeshacer.fill = GridBagConstraints.BOTH;
		gbc_btnDeshacer.gridx = 1;
		gbc_btnDeshacer.gridy = 6;
		btnDeshacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deshacer();
			}		
		});
		panelBotones.add(btnDeshacer, gbc_btnDeshacer);
				
		panelPuzzle = new JPanel();
		panelPuzzle.setBounds(10, 11, 525, 525);
		frame.getContentPane().add(panelPuzzle);
		gl_panelPuzzle = new GridLayout(19,19,0, 0);
		panelPuzzle.setLayout(gl_panelPuzzle);
	
	}

	//Crea los campos de texto para poder meter los elementos
	public void crearCeldas() {
				
		Ventana.filas = Integer.valueOf(textField_numFilas.getText());
		Ventana.columnas = Integer.valueOf(textField_NumColumnas.getText());
				
		if (filas > 10 || filas < 1) {
			JOptionPane.showMessageDialog(frame, "El numero de filas debe estar entre 1 y 10");
			return;
		}
		
		if (columnas > 10 || columnas < 1) {
			JOptionPane.showMessageDialog(frame, "El numero de columnas debe estar entre 1 y 10");
			return;
		}
		
		panelPuzzle.removeAll();
		panelPuzzle.revalidate();
		panelPuzzle.repaint();
		
		camino = new ArrayList<Casilla>();
		
		panelPuzzle.removeAll();

		camposTexto = new JTextField[10][10];
		crearTextField();
		
		filasAmpliadas = Integer.valueOf(textField_numFilas.getText())*2 - 1;
		columnasAmpliadas = Integer.valueOf(textField_NumColumnas.getText())*2 - 1;
				
		gl_panelPuzzle.setColumns(columnasAmpliadas);
		gl_panelPuzzle.setRows(filasAmpliadas);

		tablaAmpliada = new String[filasAmpliadas][columnasAmpliadas];
				
		for(int i = 0; i < filasAmpliadas; i++) {
			for(int j = 0; j < columnasAmpliadas; j++) {				
				//j/2 + "/" + i/2
				
				
				if (j%2 == 0 && i % 2 == 0) {
					camposTexto[i/2][j/2].setText("");
					camposTexto[i/2][j/2].setHorizontalAlignment(SwingConstants.CENTER);
					Font font1 = new Font("SansSerif", Font.BOLD, 20);		 
					camposTexto[i/2][j/2].setFont(font1);
					
					panelPuzzle.add(camposTexto[i/2][j/2]);
					
				} else {
					JLabel label = new JLabel(" ");
					Font font1 = new Font("SansSerif", Font.BOLD, 20);	
					label.setFont(font1);
					label.setHorizontalAlignment(SwingConstants.CENTER);
					panelPuzzle.add(label);
				}
			}
		}
		panelPuzzle.revalidate();
		panelPuzzle.repaint();
	}
	
	
	//Cambia los campos de texto por botones con el mismo contenido
	public void cambiarCeldasPorBotones() {
				
		botones = new JButton[10][10];
		crearBotones();
		
		simbolos = new JLabel[filasAmpliadas][columnasAmpliadas];
		crearLabels();
		
		max = 0;
		min = 0;
		
		camino = new ArrayList<Casilla>();
		caminoAux = new ArrayList<Casilla>();
		
		elementosRecorridos = 0;
		
		panelPuzzle.removeAll();
		panelPuzzle.revalidate();
		
		for(int i = 0; i < filasAmpliadas; i++) {
			for(int j = 0; j < columnasAmpliadas; j++) {
				
				if (j%2 == 0 && i % 2 == 0) {
					
					panelPuzzle.remove(camposTexto[i/2][j/2]);
					Font font1 = new Font("SansSerif", Font.BOLD, 100/filas+5);		 
					
					botones[i/2][j/2].setMargin(new Insets(0,0,0,0));

					botones[i/2][j/2].setText(camposTexto[i/2][j/2].getText());
					botones[i/2][j/2].setFont(font1);
					botones[i/2][j/2].addActionListener(new ListenersZigzag(i, j));

					botones[i/2][j/2].setHorizontalAlignment(SwingConstants.CENTER);
					
					panelPuzzle.add(botones[i/2][j/2]);
					
				} else {
					
						JLabel label = new JLabel(" ");
						label.setHorizontalAlignment(SwingConstants.CENTER);
						simbolos[i][j].setVerticalAlignment(SwingConstants.CENTER);
						panelPuzzle.add(simbolos[i][j]);
					
				}
			}
		}
				
		panelPuzzle.revalidate();
		panelPuzzle.repaint();
	}
	
	//Devuelve botones
	public static JButton[][] getBotones(){
		return botones;
	}
	
	//Devuelve camposTexto
	public JTextField[][] getCamposTexto(){
		return camposTexto;
	}
	
	//Mete en la tablaAmpliada todos los elementos
	public void introducirEnMatriz() {
		for (int i = 0; i < filasAmpliadas; i+=2) {
			for (int j = 0; j < columnasAmpliadas; j+=2) {								
				tablaAmpliada[i][j] = camposTexto[i/2][j/2].getText();		
			}
		}
		
		for (int i = 0; i < filasAmpliadas ; i++) {
			for (int j = 0; j < columnasAmpliadas ; j++){
				if (tablaAmpliada[i][j] == null) tablaAmpliada[i][j] = " ";
			}
		}
		
		panelPuzzle.revalidate();
		panelPuzzle.repaint();
	}
	
	//Devuelve un String con todas las posibles soluciones
	public String mostrarSoluciones() {
		
		ZigZag aux = new ZigZag(filas,columnas,tablaAmpliada);
		return aux.solucionar();
		
	}
	
	//Todas las comprobaciones de entrada juntas
	public boolean comprobaciones() {
		
		try {
			//Si un elemento es menor de 1 o mayor de 10, la entrada es incorrecta
			if(elementoIncorrecto(tablaAmpliada)) {
				JOptionPane.showMessageDialog(frame, "Entrada malformada");
				return false;
			}
			
			//Si todos los elementos son iguales y no es un solo elemento(1x1), no tiene soluciones
			if(todosSonMismoElemento(tablaAmpliada) && ( filas != 1 && columnas != 1)) {
				JOptionPane.showMessageDialog(frame, "Numero de soluciones: 0");
				return false;
			}
			
		}catch (Exception e) {
			JOptionPane.showMessageDialog(frame, "Entrada malformada");
			return false;
		}
		return true;
	}
	
	//Comprueba que todos los elementos estén entre 1 y 10
	public static boolean elementoIncorrecto(String[][] tablaAmpliada) {
		
		for (int i = 0; i < filasAmpliadas ; i+=2) {
			for (int j = 0; j < columnasAmpliadas; j+=2) {
				if (Integer.valueOf(tablaAmpliada[i][j]) < 1 || Integer.valueOf(tablaAmpliada[i][j]) > 9){
					return true;
				}
			}
		}
		return false;
	}
	
	//Comprueba si todos los elementos son iguales
	public static boolean todosSonMismoElemento(String[][] tablaAmpliada) {
		
		for(int i = 0; i <filasAmpliadas; i+=2) {
			for(int j = 0; j < columnasAmpliadas; j+=2) {
				if(Integer.valueOf(tablaAmpliada[i][j]) != Integer.valueOf(tablaAmpliada[0][0])){
					return false;
				}
			}	
		}
			
		return true;
	}
	
	//Metodo que guarda un puzle en un archivo
	public void guardarEnArchivo(String texto){
		
		try (PrintWriter out = new PrintWriter(textField_FileName.getText())){
			out.print(texto);
			out.close();
			JOptionPane.showMessageDialog(frame, "Fichero guardado con exito con el nombre: " + textField_FileName.getText());
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(frame, "El nombre del archivo es incorrecto, quita los simbolos extraños o añade .txt al final");
		}
	}
	
	//Pasa la matriz en una string para guardarla (metodo auxiliar a guardarEnArchivo)
	public String mostrarMatrizEnTexto() {
		
		String aux = "";
		
		for(int i = 0; i < filasAmpliadas; i++) {
			for(int j = 0; j < columnasAmpliadas;j++) {
				if (j%2 == 0 && i % 2 == 0) {
					tablaAmpliada[i][j] = camposTexto[i/2][j/2].getText();
				}				
			}
		}
		
		for (int i = 0; i < filasAmpliadas; i+=2) {
			for (int j = 0; j < columnasAmpliadas; j+=2) {
				aux += tablaAmpliada[i][j] + " ";
			}
			aux += "\r\n";
		}
		
		return aux;
	}
	
	//Metodo que recupera un puzle previamente guardado en un fichero
	public void recuperarDeArchivo(String texto) throws IOException {
		
		 File archivo = null;
	     FileReader fr = null;
	     BufferedReader br = null;
	     String linea = "";

	      try {
	         // Apertura del fichero y creacion de BufferedReader para poder
	         // hacer una lectura comoda (disponer del metodo readLine()).
	         archivo = new File (texto);
	         fr = new FileReader (archivo);
	         br = new BufferedReader(fr);

	         // Lectura del fichero
	         for( int i = 0; i < 10; i++) {
	        	 String aux2 = br.readLine();
	        	 if (aux2 != null && aux2 != "\n") linea += aux2 + "\n";
	         }
	      }
	      catch(Exception e){
			JOptionPane.showMessageDialog(frame, "El nombre del archivo es incorrecto");
	      }finally{
	         try{                    
	            if( null != fr ){   
	               fr.close();     
	            }                  
	         }catch (Exception e2){ 

	         }
	      }
	    
		String[] line1 = linea.split("\n");
		String[] aux = line1[0].split(" ");
		
		Ventana.filas = line1.length;
		Ventana.columnas = aux.length;
		
		textField_NumColumnas.setText(columnas + "");
		textField_numFilas.setText(filas + "");
	
		filasAmpliadas = filas*2 -1;
		columnasAmpliadas = columnas*2 -1;
		
		tablaAmpliada = new String[filasAmpliadas][columnasAmpliadas];
		
		for(int i = 0; i < filasAmpliadas; i+= 2) {
			for(int j = 0; j < columnasAmpliadas; j+=2) {
				
				String[] line2 = line1[i/2].split(" "); 
				tablaAmpliada[i][j] = line2[j/2];
			}
		}
								
		deStringACeldas();		
	}

	//Pasa de un string a rellenar los campos de texto(método auxiliar a recuperarDeArchivo())
	public void deStringACeldas() {
		
		panelPuzzle.removeAll();
		panelPuzzle.revalidate();
		panelPuzzle.repaint();
		
		panelPuzzle.removeAll();

		camposTexto = new JTextField[10][10];
		crearTextField();
		
		filasAmpliadas = filas*2 - 1;
		columnasAmpliadas = columnas*2 - 1;
		
		gl_panelPuzzle.setColumns(columnasAmpliadas);
		gl_panelPuzzle.setRows(filasAmpliadas);
		
		for(int i = 0; i < filasAmpliadas; i++) {
			for(int j = 0; j < columnasAmpliadas; j++) {				
				
				if (j%2 == 0 && i % 2 == 0) {
					camposTexto[i/2][j/2].setText(tablaAmpliada[i][j]);
					camposTexto[i/2][j/2].setHorizontalAlignment(SwingConstants.CENTER);
					Font font1 = new Font("SansSerif", Font.BOLD, 20);		 
					camposTexto[i/2][j/2].setFont(font1);
					panelPuzzle.add(camposTexto[i/2][j/2]);
					
				} else {
					JLabel label = new JLabel(" ");
					Font font1 = new Font("SansSerif", Font.BOLD, 20);	
					label.setFont(font1);
					label.setHorizontalAlignment(SwingConstants.CENTER);
					panelPuzzle.add(label);
				}
			}
		}
	
		panelPuzzle.revalidate();
		panelPuzzle.repaint();
	}

	//Añade una casilla al camino
	public static void anadirACamino(int i, int j) {
		camino.add(new Casilla(i, j));
	}
	
	//Añade una casilla al caminoAux
	public static void anadirACaminoAux(int i, int j) {
		caminoAux.add(new Casilla(i, j));
	}
	
	//Comprueba que el boton que se clica no esté en el camino
	public static boolean estaEnCamino(int x, int y) {
		for (int i = 0 ; i < camino.size();i++) {
			if (camino.get(i).i == x && camino.get(i).j == y) return true;
		}
		return false;
	}
	
	//Comprueba que la celda no este lejos
	public static boolean esPosibleSalto( int i, int j) {
		
		if (camino.size() > 0) {
			
			// Arriba izquierda
			if ( i == camino.get(camino.size()-1).i - 2 && j == camino.get(camino.size()-1).j - 2) {
				return true;
			}
			// Arriba
			if ( i == camino.get(camino.size()-1).i - 2 && j  == camino.get(camino.size()-1).j) {
				return true;
			}		
			// Arriba derecha
			if ( i == camino.get(camino.size()-1).i - 2 && j == camino.get(camino.size()-1).j + 2) {
				return true;
			}
			// Izquierda
			if ( i == camino.get(camino.size()-1).i && j == camino.get(camino.size()-1).j - 2 ) {
				return true;
			}
			// Derecha
			if ( i == camino.get(camino.size()-1).i && j == camino.get(camino.size()-1).j + 2 ) {
				return true;
			}
			// Abajo izquierda
			if ( i == camino.get(camino.size()-1).i + 2 && j == camino.get(camino.size()-1).j - 2) {
				return true;
			}
			// Abajo
			if ( i == camino.get(camino.size()-1).i + 2 && j == camino.get(camino.size()-1).j) {
				return true;
			}
			// Abajo derecha
			if ( i == camino.get(camino.size()-1).i + 2 && j == camino.get(camino.size()-1).j + 2) {
				return true;
			}
			
		} else {
			return true;
		}

		return false;
		
	}
	
	//Comprueba que se pueda saltar a esa celda
	public static boolean esSaltoValido(int filaprev, int columnaprev, int fila, int columna) {
		
		max = buscarMax(tablaAmpliada);
		min = buscarMin(tablaAmpliada);
		
		if (siguienteNumero(Integer.valueOf(tablaAmpliada[filaprev][columnaprev]),Integer.valueOf(tablaAmpliada[fila][columna])) || deMaxAMin(Integer.valueOf(tablaAmpliada[filaprev][columnaprev]),Integer.valueOf(tablaAmpliada[fila][columna]))) {
			return true;
		}
		
		return false;

	}
	
	//Metodo que comprueba que no se cruzen los simbolos
	public static boolean haySimbolo(int fila, int columna) {
		
		if(simbolos[fila][columna].getText() == " ") {
			return false;
		} else {
			return true;
		}
	}
	
	//Busca el elemento máximo
	public static int buscarMax(String tabla[][]) {
		int max = 0;
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				if (Integer.valueOf(botones[i][j].getText()) > max ) max = Integer.valueOf(botones[i][j].getText());
			}
		}
		return max;
	}
	
	//Busca el elemento minimo
	public static int buscarMin(String tabla[][]) {
		int min = 10;
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				if (Integer.valueOf(botones[i][j].getText()) < min ) min = Integer.valueOf(botones[i][j].getText());
			}
		}
		return min;
	}
	
	//Comprueba si se puede realizar el salto de casilla (si pasa de maximo a minimo)
	public static boolean deMaxAMin(int numeroprev, int numero) {
		max = buscarMax(tablaAmpliada);
		min = buscarMin(tablaAmpliada);

		if(numeroprev == max && numero == min) {
			return true;
		} else {
			return false;
		}
			
	}
	
	//Comprueba si se puede realizar el salto de casilla (si el numero al que salta es el anterior + 1)
	public static boolean siguienteNumero(int numeroprev, int numero) {
		
		if (numero == numeroprev + 1) {
			return true;
		} else {
			return false;
		}
		
	}
	
	//Devuelva el tipo de simbolo que va en funcion de la direccion del salto de una celda a otra
	public static String tipoSimbolo( int i, int j) {
		
		String aux = ""; 
		
		if (camino.size() > 1) {
			
			// Arriba izquierda
			if ( i == camino.get(camino.size()-2).i - 2 && j == camino.get(camino.size()-2).j - 2) {
				aux = "↖";
			}
			// Arriba
			if ( i == camino.get(camino.size()-2).i - 2 && j  == camino.get(camino.size()-2).j) {
				aux =  "↑";
			}		
			// Arriba derecha
			if ( i == camino.get(camino.size()-2).i - 2 && j == camino.get(camino.size()-2).j + 2) {
				aux =  "↗";
			}
			// Izquierda
			if ( i == camino.get(camino.size()-2).i && j == camino.get(camino.size()-2).j - 2 ) {
				aux =  "←";
			}
			// Derecha
			if ( i == camino.get(camino.size()-2).i && j == camino.get(camino.size()-2).j + 2 ) {
				aux =  "→";
			}
			// Abajo izquierda
			if ( i == camino.get(camino.size()-2).i + 2 && j == camino.get(camino.size()-2).j - 2) {
				aux =  "↙";
			}
			// Abajo
			if ( i == camino.get(camino.size()-2).i + 2 && j == camino.get(camino.size()-2).j) {
				aux =  "↓";
			}
			// Abajo derecha
			if ( i == camino.get(camino.size()-2).i + 2 && j == camino.get(camino.size()-2).j + 2) {
				aux =  "↘";
			}
			
		} 
		return aux;
	}
	
	//Cuenta el numero de elementos a recorrer
	public static int contarElementos() {
		return filas*columnas;
	}
	
	//Metodo que retrocede una vez el camino
	public static void deshacer() {
		
		if(camino.size() > 1) {
			
			botones[(camino.get(camino.size()-1).i)/2][(camino.get(camino.size()-1).j)/2].setBackground(null);
			
			int posicionI = (camino.get(camino.size()-1).i - camino.get(camino.size()-2).i) / 2 + camino.get(camino.size()-2).i;  
			int posicionJ = (camino.get(camino.size()-1).j - camino.get(camino.size()-2).j) / 2 + camino.get(camino.size()-2).j;  
						
			simbolos[posicionI][posicionJ].setText(" ");
			camino.remove(camino.size()-1);
			
			elementosRecorridos--;
			
		} else if (camino.size() == 1){
			camino.clear();
			botones[0][0].setBackground(null);
			
			elementosRecorridos--;
			
		} else {	
			JOptionPane.showMessageDialog(frame, "No puedes deshacer más veces, ya que ya has borrado todo el recorrido" );
		}
		
	}
	
	//Metodo que rehace el camino
	public void rehacer() {
		
		try {
		    
		    if(camino.size() == 0){
		        
		        botones[(caminoAux.get(camino.size()).i)/2][(caminoAux.get(camino.size()).j)/2].setBackground(Color.green);
                camino.add(caminoAux.get(camino.size()));
                elementosRecorridos++;

		        
		    }else if(camino.size() < contarElementos()) {
							
				botones[(caminoAux.get(camino.size()).i)/2][(caminoAux.get(camino.size()).j)/2].setBackground(Color.green);
				
				camino.add(caminoAux.get(camino.size()));
				
				int posicionI = (caminoAux.get(camino.size()-1).i - caminoAux.get(camino.size()-2).i) / 2 + caminoAux.get(camino.size()-2).i;  
				int posicionJ = (caminoAux.get(camino.size()-1).j - caminoAux.get(camino.size()-2).j) / 2 + caminoAux.get(camino.size()-2).j;  
				
				simbolos[posicionI][posicionJ].setText(tipoSimbolo(caminoAux.get(camino.size()-1).i, caminoAux.get(camino.size()-1).j));
				
				elementosRecorridos++;
			}
		}catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(frame, "No puedes rehacer mas veces" );			
		}
	}
	
	
	//Metodo auxiliar al rehacer, si se pulsa una nueva celda, no se puede rehacer
	public static void limpiarCaminoAux() {
		caminoAux.clear();
		for (int i = 0; i < camino.size(); i++) {
			caminoAux.add(camino.get(i));
		}
	}
	
	
	//Crea los botones donde estan los numeros y los guarda en un array
	public static void crearBotones() {
		
		for (int i = 0; i < 10;i++) {
			for (int j = 0; j < 10; j++) {
				botones[i][j] = new JButton();
			}
		}	
	}
	
	//Crea los JTextFields donde coloco los numeros y los guarda en un array
	public void crearTextField() {
		
		for (int i = 0; i < 10;i++) {
			for (int j = 0; j < 10; j++) {
				camposTexto[i][j] = new JTextField();
			}
		}	
		
	}
	
	//Crea las JLabels donde coloco los simbolos y los guarda en un array
	public void crearLabels() {	
		for (int i = 0; i < filasAmpliadas; i++) {
			for (int j = 0; j < columnasAmpliadas; j++) {
				JLabel label = new JLabel(" ");
				int tamano =  Math.min(300/filas,300/columnas);
				Font font1 = new Font("SansSerif", Font.BOLD, tamano);		 
				label.setHorizontalAlignment(SwingConstants.CENTER);
				label.setFont(font1);
				simbolos[i][j] = label;
			}
		}
	}
	
	
	//METODOS AUXILIARES PARA AYUDARME MIENTRAS DESARROLLABA LA APLICACION
	
	public void mostrarBotones() {
		String aux = "";
		
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				aux += "boton [" + i + "][" + j + "] = " +   botones[i][j].getText() + "\n";
			}
		}
		JOptionPane.showMessageDialog(frame, aux);

	}
	
	public static void mostrarTabla2D(String[][] tabla) {
		for (int i = 0; i < filasAmpliadas; i++) {
			for (int j = 0; j < columnasAmpliadas; j++) {
				if (tabla[i][j] == null) {
					System.out.print("o ");
				} else {
					System.out.print(tabla[i][j] + " ");
				}
			}
			System.out.print("\n");
		}
	}
	
	public static String imprimirCamino() {
		String aux = "";
		for (int i = 0; i< camino.size(); i++) {
			if (camino.get(i) != null) aux+= i+1 + "º " + camino.get(i).toString() + " | ";
		}
		return aux;
	}
	
	public static String imprimirCaminoAux() {
		String aux = "";
		for (int i = 0; i< caminoAux.size(); i++) {
			if (caminoAux.get(i) != null) aux+= i+1 + "º " + caminoAux.get(i).toString() + " | ";
		}
		return aux;
	}
}