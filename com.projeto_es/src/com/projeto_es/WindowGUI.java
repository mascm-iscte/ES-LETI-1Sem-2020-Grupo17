package com.projeto_es;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WindowGUI {

	protected static final Component parent = null;
	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowGUI window = new WindowGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WindowGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		
		table = new JTable();
		table.setBounds(116, 48, 661, 334);
		frame.getContentPane().add(table);
		
		JButton btnNewButton = new JButton("Procurar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser chooser = new JFileChooser();     
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");     
				chooser.setFileFilter(filter);     
				int returnVal = chooser.showOpenDialog(parent);     
				if(returnVal == JFileChooser.APPROVE_OPTION) {        
					System.out.println("You chose to open this file: " +chooser.getSelectedFile().getName());
					System.out.println("Your directory is: " +chooser.getCurrentDirectory());
					}
				
			}
		});
		btnNewButton.setBounds(659, 409, 89, 23);
		frame.getContentPane().add(btnNewButton);
	}
}
