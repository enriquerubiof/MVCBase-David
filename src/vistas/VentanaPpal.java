package vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import excepciones.BBDDException;
import excepciones.CantidadDebeSerPositivaException;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPpal extends JFrame {

	private JPanel contentPane;
	private Controlador controlador;

		/**
	 * Create the frame.
	 */
	public VentanaPpal() {
		setTitle("Gestión de Biblioteca");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][]", "[][][][]"));
		
		JButton btnNuevoLibro = new JButton("Nuevo Libro");
		btnNuevoLibro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					controlador.mostrarInsertarLibro();
				} catch (CantidadDebeSerPositivaException | BBDDException e1) {
					// TODO Bloque catch generado automáticamente
					e1.printStackTrace();
				}
			}
		});
		btnNuevoLibro.setFont(new Font("Verdana", Font.PLAIN, 18));
		contentPane.add(btnNuevoLibro, "cell 1 1,growx");
		
		JButton btnMostrarLibros = new JButton("Mostrar Libros");
		btnMostrarLibros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					controlador.mostrarLibros();
				} catch (CantidadDebeSerPositivaException | BBDDException e1) {
					JOptionPane.showMessageDialog(null, 
							e1.getMessage(),
							"Error al recuperar los datos",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnMostrarLibros.setFont(new Font("Verdana", Font.PLAIN, 18));
		contentPane.add(btnMostrarLibros, "cell 3 1,growx");
		
		JButton btnNuevaEditorial = new JButton("Nueva Editorial");
		btnNuevaEditorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.mostrarInsertarEditorial();
			}
		});
		btnNuevaEditorial.setFont(new Font("Verdana", Font.PLAIN, 18));
		contentPane.add(btnNuevaEditorial, "cell 1 3");
		
		JButton btnMostrarEditorial = new JButton("Mostrar Editorial");
		btnMostrarEditorial.setFont(new Font("Verdana", Font.PLAIN, 18));
		btnMostrarEditorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					controlador.mostrarEditoriales();
				} catch (BBDDException e1) {
					JOptionPane.showMessageDialog(null, 
							e1.getMessage(),
							"Error al recuperar los datos",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		contentPane.add(btnMostrarEditorial, "cell 3 3");
	}

	public void setControlador(Controlador controlador) {
		this.controlador=controlador;
	}

}
