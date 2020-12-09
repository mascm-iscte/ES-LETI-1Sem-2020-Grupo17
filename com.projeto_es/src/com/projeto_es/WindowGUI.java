package com.projeto_es;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;


import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileOutputStream;

import java.io.IOException;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;


import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Rectangle;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WindowGUI {

	protected static final Component parent = null;
	private JFrame frmExcelSearch;
	Vector<String> headers = new Vector<String>();
	Vector<Vector<String>> data = new Vector<Vector<String>>();
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
		frmExcelSearch.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmExcelSearch.setSize(1200, 800);
		frmExcelSearch.getContentPane().setBounds(new Rectangle(1000, 1000, 1200, 800));
		frmExcelSearch.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		frmExcelSearch.setForeground(Color.BLUE);
		frmExcelSearch.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		frmExcelSearch.setTitle("Excel Search");
		WindowListener exitListener = new WindowAdapter() {

		    @Override
		    public void windowClosing(WindowEvent e) {
		        int confirm = JOptionPane.showOptionDialog(
		             null, "Are You Sure to Close Application?", 
		             "Exit Confirmation", JOptionPane.YES_NO_OPTION, 
		             JOptionPane.QUESTION_MESSAGE, null, null, null);
		        if (confirm == 0) {
		        	System.exit(0);
		        }
		    }
		};
		frmExcelSearch.addWindowListener(exitListener);
		

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
						Vector<String> d = new Vector<String>();
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
			    //CreationHelper createhelper = book.getCreationHelper();
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
				Object[] options = {"Default", "is_long_method metrics", "is_feature_envy metrics", "Create"};
				int n = JOptionPane.showOptionDialog(frmExcelSearch,
					"Thresholds Options", "Save",
							JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE,
							null,
							options, options[3]);
				if(n==0) {
					// add method here
					JOptionPane.showMessageDialog(frmExcelSearch,
						    "Applied default thresholds",
						    "Warning",
						    JOptionPane.WARNING_MESSAGE);
				} else if(n==1) {
					// add method here
				
					String LOC = JOptionPane.showInputDialog(frmExcelSearch, "Enter new LOC threshold:");
					String CYCLO = JOptionPane.showInputDialog(frmExcelSearch, "Enter new CYCLO threshold:");
					int LOC_metric = Integer.parseInt(LOC);
					int CYCLO_metric = Integer.parseInt(CYCLO);
					System.out.println(Integer.toString(LOC_metric));
					System.out.println(Integer.toString(CYCLO_metric));
					LongMethodThresholds longMethod = new LongMethodThresholds (LOC_metric, CYCLO_metric);
					JTable long_table = new JTable();
					Vector<Vector<String>> loc_values = new Vector<Vector<String>>();
					int loc = 0;
					int cyclo = 0;
					for(int i = 1; i < method.getRows(); i++){
						Vector<String> loc_rows = new Vector<String>();
						for(int j = 0; j < method.getCols(); j++){
							if(j == 4){
								String loc_str = method.getCellContentStr(i, j);
								loc = Integer.parseInt(loc_str);
								loc_rows.add(method.getCellContentStr(i, j));
								j++;
								String cyclo_str = method.getCellContentStr(i, j);
								cyclo = Integer.parseInt(cyclo_str);
								loc_rows.add(method.getCellContentStr(i, j));
								}
							
							else if(j==8) {
								if(longMethod.isLongMethod(loc, cyclo,LOC_metric, CYCLO_metric)) {
									loc_rows.add("TRUE");
								}
							
								else {
									loc_rows.add("FALSE");
								}
							}
							else{
								loc_rows.add(method.getCellContentStr(i, j));
								}
							}loc_values.add(loc_rows);
							}
					
					DefaultTableModel long_model = new DefaultTableModel(loc_values,headers);
					long_table.setModel(long_model);
					long_table.setAutoCreateRowSorter(true);
					JFrame long_frame = new JFrame();
					JScrollPane long_scroll = new JScrollPane(long_table);
					long_frame.setSize(800, 600);
					long_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					long_frame.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					long_frame.setTitle("is_long_method Search");
					long_frame.setVisible(true);
					long_frame.setResizable(true);
					long_frame.add(long_scroll);

					
				
					
					JOptionPane.showMessageDialog(frmExcelSearch,

						    "Applied is_long_method thresholds",
						    "Warning",
						    JOptionPane.WARNING_MESSAGE);
				} else if(n==2) {
					// add method here
					String ATFD = JOptionPane.showInputDialog(frmExcelSearch, "Enter new ATFD threshold:");
					String LAA = JOptionPane.showInputDialog(frmExcelSearch, "Enter new LAA threshold:");
					String NOFA = JOptionPane.showInputDialog(frmExcelSearch, "Enter new NOFA threshold:");
					int ATFD_metric = Integer.parseInt(ATFD);
					double LAA_metric = Double.parseDouble(LAA);
					int NOFA_metric = Integer.parseInt(NOFA);
					System.out.println(Integer.toString(ATFD_metric));
					System.out.println(Double.toString(LAA_metric));
					System.out.println(Integer.toString(NOFA_metric));
					FeatureEnvyThresholds featureEnvy = new FeatureEnvyThresholds (ATFD_metric, LAA_metric, NOFA_metric);
					JTable long_table = new JTable();
					Vector<Vector<String>> fe_values = new Vector<Vector<String>>();
					int atfd = 0;
					double laa = 0;
					int nofa = 0;
					for(int i = 1; i < method.getRows(); i++){
						Vector<String> fe_rows = new Vector<String>();
						for(int j = 0; j < method.getCols(); j++){
							if(j == 6){
								String atfd_str = method.getCellContentStr(i, j);
								atfd = Integer.parseInt(atfd_str);
								fe_rows.add(method.getCellContentStr(i, j));
								j++;
								String laa_str = method.getCellContentStr(i, j);
								laa = Double.parseDouble(laa_str);
								fe_rows.add(method.getCellContentStr(i, j));
								}
							
							else if(j==11) {
								if(featureEnvy.isFeatureEnvy(atfd, laa, nofa, ATFD_metric, LAA_metric, NOFA_metric)) {
									fe_rows.add("TRUE");
								}
							
								else {
									fe_rows.add("FALSE");
								}
							}
							else{
								fe_rows.add(method.getCellContentStr(i, j));
								}
							}fe_values.add(fe_rows);
							}
					
					DefaultTableModel long_model = new DefaultTableModel(fe_values,headers);
					long_table.setModel(long_model);
					long_table.setAutoCreateRowSorter(true);
					JFrame long_frame = new JFrame();
					JScrollPane long_scroll = new JScrollPane(long_table);
					long_frame.setSize(800, 600);
					long_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					long_frame.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					long_frame.setTitle("is_feature_envy Search");
					long_frame.setVisible(true);
					long_frame.setResizable(true);
					long_frame.add(long_scroll);

					
				
					
					JOptionPane.showMessageDialog(frmExcelSearch,

						    "Applied is_long_method thresholds",
						    "Warning",
						    JOptionPane.WARNING_MESSAGE);
				}else if(n==3){
					// add method here
					JOptionPane.showMessageDialog(frmExcelSearch,
						    "Applied Created thresholds",
						    "Warning",
						    JOptionPane.WARNING_MESSAGE);
				}
				
				
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
		
	
		
