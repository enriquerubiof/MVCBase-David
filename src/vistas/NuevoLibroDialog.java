package vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import excepciones.BBDDException;
import excepciones.CantidadDebeSerPositivaException;
import modelo.Editorial;
import modelo.Libro;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class NuevoLibroDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtIsbn;
	private JTextField txtTitulo;
	private JComboBox comboEditorial;
	private JTextField txtPrecio;
	private JTextField txtPrecioCD;
	private Controlador controlador;
	private JSpinner spinnerAnio;
	private JSpinner spinnerPaginas;
	private JSpinner spinnerCantidad;
	private JButton okButton;



	/**
	 * Create the dialog.
	 */
	public NuevoLibroDialog() {
		setBounds(100, 100, 464, 327);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][][grow][grow][][grow][]", "[][][][][][][][][]"));
		{
			JLabel lblNewLabel = new JLabel("Introduce los datos del libro:");
			lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
			contentPanel.add(lblNewLabel, "cell 1 1 5 1,growx");
		}
		{
			JLabel lblNewLabel_1 = new JLabel("ISBN:");
			lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 12));
			contentPanel.add(lblNewLabel_1, "cell 1 3,alignx trailing");
		}
		{
			txtIsbn = new JTextField();
			contentPanel.add(txtIsbn, "cell 2 3 5 1,growx");
			txtIsbn.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Título:");
			lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 12));
			contentPanel.add(lblNewLabel_1, "cell 1 4,alignx trailing");
		}
		{
			txtTitulo = new JTextField();
			contentPanel.add(txtTitulo, "cell 2 4 5 1,growx");
			txtTitulo.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Cód Editorial:");
			lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 12));
			contentPanel.add(lblNewLabel_1, "cell 1 5,alignx trailing");
		}
		{
			comboEditorial = new JComboBox();
			contentPanel.add(comboEditorial, "cell 2 5 5 1,growx");
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Año:");
			lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 12));
			contentPanel.add(lblNewLabel_1, "cell 1 6,alignx right");
		}
		{
			spinnerAnio = new JSpinner();
			spinnerAnio.setModel(new SpinnerNumberModel(Integer.valueOf(2023), Integer.valueOf(1900), null, Integer.valueOf(1)));
			contentPanel.add(spinnerAnio, "cell 2 6,growx");
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Núm Páginas:");
			lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 12));
			contentPanel.add(lblNewLabel_1, "cell 3 6 2 1");
		}
		{
			spinnerPaginas = new JSpinner();
			spinnerPaginas.setModel(new SpinnerNumberModel(200, 1, 50000, 1));
			contentPanel.add(spinnerPaginas, "cell 5 6,growx");
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Precio: ");
			lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 12));
			contentPanel.add(lblNewLabel_1, "cell 1 7,alignx trailing");
		}
		{
			txtPrecio = new JTextField();
			contentPanel.add(txtPrecio, "cell 2 7,growx");
			txtPrecio.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("PrecioCD: ");
			lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 12));
			contentPanel.add(lblNewLabel_1, "cell 4 7,alignx trailing");
		}
		{
			txtPrecioCD = new JTextField();
			txtPrecioCD.setColumns(10);
			contentPanel.add(txtPrecioCD, "cell 5 7,growx");
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Cantidad:");
			lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 12));
			contentPanel.add(lblNewLabel_1, "cell 1 8,alignx right");
		}
		{
			spinnerCantidad = new JSpinner();
			spinnerCantidad.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
			contentPanel.add(spinnerCantidad, "cell 2 8,growx");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Insertar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Libro l =validarDatos();
						
						if (l!=null) {
							try {
								if (okButton.getText().equals("Insertar")) {
									controlador.insertaLibro(l);
								} else {
									controlador.editarLibro(l);
								}
							} catch (BBDDException | CantidadDebeSerPositivaException e1) {
								JOptionPane.showConfirmDialog(contentPanel, 
										e1.getMessage(),
										"Error insertando los datos",JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	protected Libro validarDatos() {
		Libro l = null;
		
		try {
			String isbn = txtIsbn.getText();
			String titulo = txtTitulo.getText();
			Editorial ed = (Editorial) comboEditorial.getSelectedItem();
			int codEditorial = ed.getCodEditorial();
			int anio = (int) spinnerAnio.getValue();
			int numPags = (int) spinnerPaginas.getValue();
			double precio = Double.parseDouble(txtPrecio.getText());
			int cantidad = (int) spinnerCantidad.getValue();
			double precioCD = Double.parseDouble(txtPrecioCD.getText());
			if (isbn==null || isbn.isBlank()) {
				JOptionPane.showConfirmDialog(contentPanel, 
						"Debe introducir el ISBN del libro",
						"error en los datos",JOptionPane.ERROR_MESSAGE);
				return l;
			}
			
			l = new Libro(isbn, titulo, codEditorial, anio, numPags, precio, cantidad, precioCD);
		} catch (NumberFormatException e) {
			JOptionPane.showConfirmDialog(contentPanel, 
					"DEbe introducir un número válido en código Editorial, precio y precio CD",
					"error en los datos",JOptionPane.ERROR_MESSAGE);
			return l;
		} catch (CantidadDebeSerPositivaException e) {
		}
		
		
		
		return l;
	}

	public void setControlador(Controlador controlador) {
		this.controlador=controlador;
		
	}
	
	public void limpiar( ) {
		this.setTitle("Insertar Libro");
		this.okButton.setText("Insertar");
		this.txtIsbn.setEnabled(true);
		this.txtIsbn.setText("");
		if (comboEditorial.getModel().getSize()>0) this.comboEditorial.setSelectedIndex(0);
		this.txtTitulo.setText("");
		this.txtPrecio.setText("");
		this.txtPrecioCD.setText("");
		this.spinnerAnio.setValue(LocalDate.now().getYear());
		this.spinnerCantidad.setValue(1);
		this.spinnerPaginas.setValue(200);
	}

	public void setLibro(Libro l) {
		this.setTitle("Editar Libro");
		this.okButton.setText("Editar");
		this.txtIsbn.setEnabled(false);
		this.txtIsbn.setText(l.getIsbn());
		
		Editorial ed = new Editorial();
		ed.setCodEditorial(l.getCodEditorial());
		this.comboEditorial.setSelectedItem(ed);
		
		//this.comboEditorial.setText(""+l.getCodEditorial());
		this.txtTitulo.setText(l.getTitulo());
		this.txtPrecio.setText(""+l.getPrecio());
		this.txtPrecioCD.setText(""+l.getPrecioCD());
		this.spinnerAnio.setValue(l.getAnio());
		this.spinnerCantidad.setValue(l.getCantidad());
		this.spinnerPaginas.setValue(l.getNumPags());
		
	}

	public void setListaEditoriales(ArrayList<Editorial> listaEditoriales) {
		for (Editorial ed : listaEditoriales) {
			comboEditorial.addItem(ed);
		}
		
	}

}
