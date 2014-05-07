package Presentation;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JTree;

import Logic.Client;
import Persistence.Category;

import javax.swing.JButton;

public class UserFrame extends JFrame {
	
	private static final long serialVersionUID = 7724983501905330662L;
	
	private JTextField textField;
	private JTextField textField_1;
	private JComboBox<String> comboBox;
	private JPanel panel;
	private JScrollPane panel_1;
	private JButton btnConectar;
	
	private DefaultMutableTreeNode root;
	private DefaultTreeModel model;
	private JTree tree;
	
	private Client client;
	private JSeparator separator_1;
	private JTextField textField_2;
	
	public UserFrame() {
		getContentPane().setLayout(null);
		setSize(690, 430);
		
		try {
			client=new Client(1125, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		root = new DefaultMutableTreeNode("Categoría");
		model = new DefaultTreeModel(root);
		tree=new JTree(model);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Usuario", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 216, 374);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNombreUsuario = new JLabel("Nombre de usuario");
		lblNombreUsuario.setBounds(43, 22, 163, 14);
		panel.add(lblNombreUsuario);
		
		textField = new JTextField();
		textField.setBounds(14, 40, 192, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(79, 71, 89, 14);
		panel.add(lblContrasea);
		
		textField_1 = new JTextField();
		textField_1.setBounds(14, 91, 192, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setBounds(14, 173, 56, 14);
		panel.add(lblCategora);
		
		comboBox = new JComboBox<>(Category.getCategorys());
		comboBox.setBounds(76, 170, 130, 20);
		panel.add(comboBox);
		
		btnConectar = new JButton("Conectar");
		btnConectar.setBounds(59, 201, 89, 23);
		btnConectar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				client.getUser().setPath(textField_2.getText());
				client.getUser().setUserName(textField.getText());
				client.getUser().setCategory(comboBox.getSelectedItem().toString());
				client.connectTo(client.getIp(), client.getPort());
				client.sendMessage(client.getS(), textField.getText()+" "+textField_1.getText()+" "+comboBox.getSelectedItem());
			}
		});
		panel.add(btnConectar);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(172, 177, -139, 14);
		panel.add(separator_1);
		
		JLabel lblDirectorio = new JLabel("Directorio");
		lblDirectorio.setBounds(79, 122, 69, 14);
		panel.add(lblDirectorio);
		
		textField_2 = new JTextField();
		textField_2.setBounds(14, 140, 111, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc=new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fc.showOpenDialog(null);
				textField_2.setText(fc.getSelectedFile().getAbsolutePath());
			}
		});
		btnNewButton.setBounds(127, 139, 79, 23);
		panel.add(btnNewButton);
		
		panel_1 = new JScrollPane();
		panel_1.setBorder(new TitledBorder(null, "Archivos", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panel_1.setBounds(236, 11, 428, 374);
		getContentPane().add(panel_1);
	}
	
	public Client getClient() {
		return client;
	}
	
	public DefaultMutableTreeNode getRoot() {
		return root;
	}

	public DefaultTreeModel getModel() {
		return model;
	}

	public JTree getTree() {
		return tree;
	}
	
	public void updateTree(){
		panel_1.setViewportView(tree);
		panel_1.repaint();
		panel_1.updateUI();
	}

	public static void main(String[] args) {
		UserFrame uf=new UserFrame();
		uf.getClient().setIp(JOptionPane.showInputDialog(null, "Ingrese la IP del servidor"));
		uf.setVisible(true);
	}
}
