package com.projeto_es;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.graphbuilder.struc.LinkedList;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WindowGUI {

	protected static final Component parent = null;
	private JFrame frmExcelSearch;
	Vector headers = new Vector();
	Vector data = new Vector();
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	JTable table = new JTable();
	String file_path;
	String file_name;
	ExcelMethods method;
	DefaultTableModel model;
	Workbook book = null;
	Sheet sheet = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowGUI window = new WindowGUI();
					window.frmExcelSearch.setVisible(true);
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
		frmExcelSearch = new JFrame();
		frmExcelSearch.setSize(1200, 800);
		frmExcelSearch.getContentPane().setBounds(new Rectangle(1000, 1000, 1200, 800));
		frmExcelSearch.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		frmExcelSearch.setForeground(Color.BLUE);
		frmExcelSearch.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		frmExcelSearch.setTitle("Excel Search");
		
		
		
		
		
		
		
		JButton btnNewButton = new JButton("Abrir");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser chooser = new JFileChooser();     
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Spreadsheet", "xlsx"); 
				chooser.setFileFilter(filter);
				//Workbook book;
				//Sheet sheet;
				int returnVal = chooser.showOpenDialog(parent);     
				if(returnVal == JFileChooser.APPROVE_OPTION) {  
					try {
						file_path = chooser.getCurrentDirectory().toString();
						file_name = chooser.getSelectedFile().getName();
						method = new ExcelMethods(book, sheet);
						// OS validation
						String os = System.getProperty("os.name").toLowerCase();
						if(os.indexOf("win")>=0)
							book = method.getWorkbook(file_path+"\\"+file_name);
						if(os.indexOf("mac")>=0)
							book = method.getWorkbook(file_path+"//"+file_name);
						if(os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0 || os.indexOf("aix") > 0)
							book = method.getWorkbook(file_path+"//"+file_name);
							
						method.setWB(book);
						method.setSH(book.getSheet("long-method"));
						for (int i = 0; i < method.getCols(); i++) {
							String headercol = method.getCellContentStr(0, i);
							headers.add(headercol);
						}
						data.clear();
						for (int i = 0; i < method.getRows(); i++) {
						Vector d = new Vector();
							for (int j = 0; j < method.getCols(); j++) {
								String auxx = method.getCellContentStr(i, j);
								d.add(auxx);
							}	
							d.add("\n");
							data.add(d);
						
						}

						}
					
						
						catch (Exception e1) {
						e1.printStackTrace();
						}
					
					model = new DefaultTableModel(data,headers);
					table.setModel(model);
					table.setAutoCreateRowSorter(true);
					model = new DefaultTableModel(data, headers);
					table.setModel(model);
					System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
					System.out.println(("File directory: " + chooser.getCurrentDirectory()));
					}
			}
		});
		
		btnNewButton_1 = new JButton("Save");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				boolean changesDetected = false;
			    CreationHelper createhelper = book.getCreationHelper();
			    sheet = book.getSheet("long-method");
			    Row row = null;
			    Cell cell = null;
			    
			    changesDetected = changed();
			    
			    if(changesDetected) {
			    	Object[] options = {"Yes", "No"};
			    	
					int n = JOptionPane.showOptionDialog(frmExcelSearch,
						"Changes found. Update workbook sheet?", "Save",
								JOptionPane.YES_NO_CANCEL_OPTION,
								JOptionPane.QUESTION_MESSAGE,
								null,
								options, options[1]);
				if(n == 0) {
					for (int i=0;i<model.getRowCount();i++) {
				        row = sheet.getRow(i);
				        for (int j=0;j<model.getColumnCount();j++) {
				        	
				        	cell = row.getCell(j);
				            cell.setCellValue((String) model.getValueAt(i, j));
				        }
				        
				    }
				    System.out.println("File saved");
				    
				   FileOutputStream out = new FileOutputStream("D:\\workbook.xls");
				   book.write(out);
				   out.close();
				   JOptionPane.showMessageDialog(frmExcelSearch,
						    "Changes saved",
						    "Warning",
						    JOptionPane.WARNING_MESSAGE);
				} 
				}
				if(!changesDetected) {
					JOptionPane.showMessageDialog(frmExcelSearch,
						    "No changes found",
						    "Warning",
						    JOptionPane.WARNING_MESSAGE);
				}
			    
			    
			    
			} catch (IOException ex) {
			    
			}
			}

			private boolean changed() {
				 for (int i=0;i<model.getRowCount();i++) {
				        for (int j=0;j<model.getColumnCount();j++) {
				        	if(!table.getValueAt(i, j).equals(method.getCellContentStr(i, j))) { 
								return true;
							}
				        }
				        
				    }
				return false;
			}});
			
	
			
		    
			   
		
		btnNewButton_2 = new JButton("Thresholds");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		

		
		btnNewButton_3 = new JButton("Procurar");
		
		JScrollPane scrollPane = new JScrollPane(table);
		GroupLayout groupLayout = new GroupLayout(frmExcelSearch.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNewButton_2)
					.addGap(18)
					.addComponent(btnNewButton_1)
					.addPreferredGap(ComponentPlacement.RELATED, 792, Short.MAX_VALUE)
					.addComponent(btnNewButton_3)
					.addGap(22))
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1184, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 709, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_2)
						.addComponent(btnNewButton_3))
					.addContainerGap())
		);
		frmExcelSearch.getContentPane().setLayout(groupLayout);
	}}
		
	
		
