package zizzaggrafico;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ListenersZigzag extends  JButton implements ActionListener {

	private int x;
	private int y;
	private int counter = 0;
	private Component frame;
	
	public ListenersZigzag(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int longitudCamino2 = Ventana.camino.size();

		if (longitudCamino2 == 0) {
			if (x == 0 && y == 0) {
				Ventana.anadirACamino(x, y);
				Ventana.anadirACaminoAux(x, y);

				Ventana.getBotones()[x/2][y/2].setBackground(Color.green);
				Ventana.elementosRecorridos++;			
				return;
			} else {
				JOptionPane.showMessageDialog(frame, "No estás empezando en la casilla superior izquierda");
				return;
			}
		} 
		
		if(!Ventana.estaEnCamino(x,y)) {
					if(Ventana.esPosibleSalto(x, y)) {
						if (Ventana.esSaltoValido(Ventana.camino.get(longitudCamino2-1).i ,Ventana.camino.get(longitudCamino2-1).j ,x ,y )) {

							Ventana.anadirACamino(x, y);
							Ventana.anadirACaminoAux(x, y);
														
							Ventana.getBotones()[x/2][y/2].setBackground(Color.green);
							
							int longitudCamino = Ventana.camino.size();
							Ventana.elementosRecorridos++;
							
							int posicionI = (Ventana.camino.get(longitudCamino-1).i - Ventana.camino.get(longitudCamino-2).i) / 2 + Ventana.camino.get(longitudCamino-2).i;  
							int posicionJ = (Ventana.camino.get(longitudCamino-1).j - Ventana.camino.get(longitudCamino-2).j) / 2 + Ventana.camino.get(longitudCamino-2).j; 

							if (Ventana.haySimbolo(posicionI, posicionJ)) {
								Ventana.camino.remove(longitudCamino- 1);
								Ventana.caminoAux.remove(Ventana.caminoAux.size()-1);
								Ventana.getBotones()[x/2][y/2].setBackground(null);
								Ventana.elementosRecorridos--;
								JOptionPane.showMessageDialog(frame, "Ya hay un simbolo entre las dos casillas");
								return;
							}
							
							if (longitudCamino > 1) {
								Ventana.simbolos[posicionI][posicionJ].setText(Ventana.tipoSimbolo(Ventana.camino.get(longitudCamino-1).i, Ventana.camino.get(longitudCamino-1).j));
							}
							
							if (Ventana.elementosRecorridos == Ventana.contarElementos() && x == (Ventana.filas-1)*2 && y == (Ventana.columnas-1)*2) {
								JOptionPane.showMessageDialog(frame, "Enhorabuena, has completado el puzzle. Puedes intentar buscar mas soluciones");
							} else if (Ventana.elementosRecorridos == Ventana.contarElementos()) {
								JOptionPane.showMessageDialog(frame, "No has acabado en la esquina inferior derecha, la solucion no es válida");
							}
							
							Ventana.limpiarCaminoAux();
							return;
						} else {
							JOptionPane.showMessageDialog(frame, "El salto no es válido");
							return;
						}
					} else {
						JOptionPane.showMessageDialog(frame, "El salto no es posible, ya que está demasiado lejos");
						return;
					}
		} else {
			JOptionPane.showMessageDialog(frame, "Ya has pasado por esa celda");
			return;
		}
	}
}