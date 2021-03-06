package com.projeto_es;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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
import java.text.DecimalFormat;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;


import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Rectangle;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WindowGUI {

	protected static final Component parent = null;
	private JFrame frmExcelSearch;
	private Vector<String> headers = new Vector<String>();
	private Vector<String> sub_headers = new Vector<String>();
	private Vector<Vector<String>> data = new Vector<Vector<String>>();
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JTable table = new JTable();
	private String file_path;
	private String file_name;
	private ExcelMethods method;
	private DefaultTableModel model;
	private Workbook book = null;
	private Sheet sheet = null;
	private String iPlasma;
	private String PMD;
	private String isLongMethod;
	private String isFeatureEnvy;
	private boolean fileExistes = false;
	private boolean changesDetected = false;

	/**
	 * Main of graphical user interface
	 * @param args is command line arguments not needed
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
	 * Constructor
	 * Create the application.
	 */
	public WindowGUI() {
		initialize();
	}



	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frmExcelSearch = new JFrame();
		frmExcelSearch.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmExcelSearch.setSize(1200, 800);
		frmExcelSearch.getContentPane().setBounds(new Rectangle(1000, 1000, 1200, 800));
		frmExcelSearch.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		frmExcelSearch.setForeground(Color.BLUE);
		frmExcelSearch.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		frmExcelSearch.setTitle("Excel Search");
		WindowListener exitListener = new WindowAdapter() {
			
			/**
			 * Triggers on closing window
			 */
			@Override
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showOptionDialog(
						null, "Are You Sure to Close Application?", 
						"Exit Confirmation", JOptionPane.YES_NO_OPTION, 
						JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (confirm == 0) {
					System.exit(0);
				} else {
					return;
				}
			}
		};
		
		frmExcelSearch.addWindowListener(exitListener);


		JButton btnNewButton = new JButton("Abrir");
		btnNewButton.addMouseListener(new MouseAdapter() {
			
			/**
			 * Triggers on clicking "Abrir" button
			 */
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
						book = validateOS(System.getProperty("os.name").toLowerCase());
						method.setWB(book);
						method.setSH(book.getSheet("long-method"));
						headers.clear();
						for (int i = 0; i < method.getCols(); i++) {
							String headercol = method.getCellContentStr(0, i);
							headers.add(headercol);
						}
						data.clear();
						for (int i = 1; i < method.getRows(); i++) {
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
					fileExistes = true;
					changesDetected = true;
				}
			}

			/**
			 * 
			 * @param os operating system name
			 * @return correct pathname for given os
			 */
			public Workbook validateOS(String os) {
				if(os.indexOf("win")>=0)
					book = method.getWorkbook(file_path+"\\"+file_name);
				if(os.indexOf("mac")>=0)
					book = method.getWorkbook(file_path+"//"+file_name);
				if(os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0 || os.indexOf("aix") > 0)
					book = method.getWorkbook(file_path+"//"+file_name);
				return book;
			}
		});

		btnNewButton_1 = new JButton("Save");
		btnNewButton_1.addActionListener(new ActionListener() {
			
			/**
			 * Clicking on "Save" button triggers current table saving
			 */
			public void actionPerformed(ActionEvent e) {
				try {
					if(!changesDetected) {
						JOptionPane.showMessageDialog(frmExcelSearch,
								"No changes detected!!",
								"Warning",
								JOptionPane.WARNING_MESSAGE);
						return;
					}
					sheet = book.getSheet("long-method");
					Row row = null;
					Cell cell = null;

					changesDetected = changed();

					if(changesDetected) {
						Object[] options = {"Yes", "No"};

						int n = JOptionPane.showOptionDialog(frmExcelSearch,
								"Update workbook sheet?", "Save",
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

							JFileChooser chooser = new JFileChooser(); 

							chooser.setCurrentDirectory(new java.io.File("."));
							chooser.setDialogTitle("Select Folder.");
							chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
							int returnVal = chooser.showOpenDialog(parent);     
							if(returnVal == JFileChooser.APPROVE_OPTION) {
								file_path = chooser.getCurrentDirectory().toString();
								String filename = JOptionPane.showInputDialog(frmExcelSearch, "Enter file name (needs to have .xlsx):");
								file_path = chooser.getCurrentDirectory().toString();	
								if(!filename.contains(".xlsx")) {
									JOptionPane.showMessageDialog(frmExcelSearch,
											"Please write a valid name.",
											"Warning",
											JOptionPane.WARNING_MESSAGE);
									return;
								}
								FileOutputStream out = null;
								String os = System.getProperty("os.name").toLowerCase();
								if(os.indexOf("win")>=0)
									out = new FileOutputStream(chooser.getSelectedFile()+"\\"+filename);
								if(os.indexOf("mac")>=0)
									out = new FileOutputStream(chooser.getSelectedFile()+"//"+filename);
								if(os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0 || os.indexOf("aix") > 0)
									out = new FileOutputStream(chooser.getSelectedFile()+"//"+filename);
								
								book.write(out);
								out.close();
								JOptionPane.showMessageDialog(frmExcelSearch,
										"Changes saved at: " + chooser.getSelectedFile(),
										"Warning",
										JOptionPane.WARNING_MESSAGE);
							} 
 
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
			
			/**
			 * 
			 * @return changesDetected true if there are detected changes in JTable
			 */
			public boolean changed() {
				for (int i=0;i<model.getRowCount();i++) {
					for (int j=0;j<model.getColumnCount();j++) {
						if(!table.getValueAt(i, j).equals(method.getCellContentStr(i, j))) {
							changesDetected = true;
							return changesDetected;
						}
					}

				}
				
				return changesDetected;
			}});






		btnNewButton_2 = new JButton("Thresholds");
		btnNewButton_2.addActionListener(new ActionListener() {
			
			/**
			 * Triggers when pressing "Thresholds" button, acesses metrics
			 */
			public void actionPerformed(ActionEvent e) {
				if(!fileExistes) {
					showWarning("No file opened!");
					return;
				}

				Object[] options = {"Default", "is_long_method metrics", "is_feature_envy metrics"};
				int n = JOptionPane.showOptionDialog(frmExcelSearch,
						"Thresholds Options", "Save",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						options, options[2]);
				if(n==0) {
					DefaultThresholds dt = new DefaultThresholds();
					Object[] optionsDefault = {"Default is_Long_Method", "Default is_feature_envy"};
					int nDefault = JOptionPane.showOptionDialog(frmExcelSearch,
							"Thresholds Options", "Save",
							JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE,
							null,
							optionsDefault, optionsDefault[1]);
					if(nDefault == 0) {
						String LOC = dt.getLoc(), CYCLO = dt.getCyclo();
						makeTableLongMethod(LOC,CYCLO);
					}

					if(nDefault == 1) {
						String ATFD = dt.getATFD(), LAA = dt.getLAA();
						makeTableFeature(ATFD,LAA);
					}

				} else if(n==1) {
					String LOC = JOptionPane.showInputDialog(frmExcelSearch, "Enter new LOC threshold:");
					if(LOC == null) {
						showWarning("Error please add a value.");
						return;
					}
					String CYCLO = JOptionPane.showInputDialog(frmExcelSearch, "Enter new CYCLO threshold:");
					if(CYCLO == null) {
						showWarning("Error please add a value.");
						return;
					}
					makeTableLongMethod(LOC, CYCLO);
				} else if(n==2) {

					String ATFD = JOptionPane.showInputDialog(frmExcelSearch, "Enter new ATFD threshold:");
					if(ATFD == null) {
						showWarning("Error please add a value.");
						return;
					}
					String LAA = JOptionPane.showInputDialog(frmExcelSearch, "Enter new LAA threshold:");
					if(LAA == null) {
						showWarning("Error please add a value.");
						return;
					}
					makeTableFeature(ATFD, LAA);

				}


			}
			
			/**
			 * 
			 * @param ATFD 
			 * @param LAA
			 */
			public void makeTableFeature(String ATFD, String LAA) {
				int ATFD_metric = Integer.parseInt(ATFD);
				double LAA_metric = Double.parseDouble(LAA);
				double DCI = 0, DII = 0, ADCI = 0, ADII = 0;
				double DCI1 = 0, DII1 = 0, ADCI1 = 0, ADII1 = 0;
				sub_headers.add("MethodID");
				sub_headers.add("Defeito iPlasma");
				sub_headers.add("Defeito PMD");
				FeatureEnvyThresholds featureEnvy = new FeatureEnvyThresholds (ATFD_metric, LAA_metric);
				JTable long_table = new JTable();
				Vector<Vector<String>> fe_values = new Vector<Vector<String>>();
				Vector<Vector<String>> sub_values = new Vector<Vector<String>>();
				JTable sub_table = new JTable();
				int atfd = 0;
				double laa = 0;
				for(int i = 1; i < method.getRows(); i++){
					Vector<String> fe_rows = new Vector<String>();
					Vector<String> sub_rows = new Vector<String>();
					for(int j = 0; j <= method.getCols() + 1; j++){
						if(j == 6){
							String atfd_str = method.getCellContentStr(i, j);
							atfd = Integer.parseInt(atfd_str);
							fe_rows.add(method.getCellContentStr(i, j));
							j++;
							String laa_str = method.getCellContentStr(i, j);
							laa = Double.parseDouble(laa_str);
							fe_rows.add(method.getCellContentStr(i, j));
						}
						else if (j==9) {
							String iPlasma_str = method.getCellContentStr(i, j);
							if(iPlasma_str.equals("TRUE")) {
								iPlasma = "TRUE";
							}
							else {
								iPlasma = "FALSE";
							}
							fe_rows.add(method.getCellContentStr(i, j));
							j++;
							String PMD_str = method.getCellContentStr(i, j);
							if(PMD_str == "TRUE") {
								PMD = "TRUE";
							}
							else {
								PMD = "FALSE";
							}
							fe_rows.add(method.getCellContentStr(i, j));

						}

						else if(j==11) {
							if(featureEnvy.isFeatureEnvy(atfd, laa, ATFD_metric, LAA_metric)) {
								isFeatureEnvy = "TRUE";
								fe_rows.add("TRUE");
							}

							else {
								isFeatureEnvy = "FALSE";
								fe_rows.add("FALSE");
							}

						}
						else if (j == method.getCols()) {
							String qualityMethod;
							QualityFactors factor = new QualityFactors();
							qualityMethod = factor.whatFactor(iPlasma, isFeatureEnvy);
							if(qualityMethod.equals("DCI")) DCI++;
							if(qualityMethod.equals("DII")) DII++;
							if(qualityMethod.equals("ADCI")) ADCI++;
							if(qualityMethod.equals("ADII")) ADII++;
							sub_rows.add(qualityMethod);
						}
						else if (j >= method.getCols()) {
							String qualityMethod;
							QualityFactors factor = new QualityFactors();
							qualityMethod = factor.whatFactor(PMD, isFeatureEnvy);
							if(qualityMethod.equals("DCI")) DCI1++;
							if(qualityMethod.equals("DII")) DII1++;
							if(qualityMethod.equals("ADCI")) ADCI1++;
							if(qualityMethod.equals("ADII")) ADII1++;
							sub_rows.add(qualityMethod);
						}
						else{
							fe_rows.add(method.getCellContentStr(i, j));
							if(j == 0) {
								sub_rows.add(method.getCellContentStr(i, j));
							}
						}
					}fe_values.add(fe_rows);
					sub_values.add(sub_rows);
				}
				DefaultTableModel long_model = new DefaultTableModel(fe_values,headers);
				final DefaultTableModel sub_model = new DefaultTableModel(sub_values, sub_headers);
				long_table.setModel(long_model);
				sub_table.setModel(sub_model);
				
				sub_headers.clear();
				long_table.setAutoCreateRowSorter(true);
				sub_table.setAutoCreateRowSorter(true);
				JFrame long_frame = new JFrame();
				JFrame sub_frame = new JFrame();
				JScrollPane long_scroll = new JScrollPane(long_table);
				JScrollPane sub_scroll = new JScrollPane(sub_table);
				long_frame.setSize(800, 600);
				sub_frame.setSize(400, 300);
				//long_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				long_frame.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				sub_frame.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				long_frame.setTitle("is_feature_envy Search");
				sub_frame.setTitle("is_feature_envy Results");
				BorderLayout border = new BorderLayout();
				sub_frame.setLayout(border);
				long_frame.setVisible(true);
				long_frame.setResizable(true);
				long_frame.add(long_scroll);
				sub_frame.setVisible(true);
				sub_frame.setResizable(true);
				sub_frame.add(sub_scroll, BorderLayout.CENTER);
				JTextField sub_footer = new JTextField();
				sub_footer.setText("Metrics: ATFD = "+ ATFD + " LAA = " + LAA + " (is_feature_envy)");
				sub_footer.setEditable(false);

				sub_frame.add(sub_footer, BorderLayout.SOUTH);
				showDefects(DCI, DII, ADCI, ADII, DCI1, DII1, ADCI1, ADII1, "is_feature_envy");
			}
			
			/**
			 * 
			 * @param LOC number of lines of code for given method
			 * @param CYCLO cyclomatic complexity for given method
			 */
			public void makeTableLongMethod(String LOC, String CYCLO) {
				int LOC_metric = Integer.parseInt(LOC);
				int CYCLO_metric = Integer.parseInt(CYCLO);
				double DCI = 0, DII = 0, ADCI = 0, ADII = 0;
				double DCI1 = 0, DII1 = 0, ADCI1 = 0, ADII1 = 0;
				LongMethodThresholds longMethod = new LongMethodThresholds (LOC_metric, CYCLO_metric);
				JTable long_table = new JTable();
				JTable sub_table = new JTable();
				Vector<Vector<String>> loc_values = new Vector<Vector<String>>();
				Vector<Vector<String>> sub_values = new Vector<Vector<String>>();
				int loc = 0;
				int cyclo = 0;
				sub_headers.add("MethodID");
				sub_headers.add("Defeito iPlasma");
				sub_headers.add("Defeito PMD");
				for(int i = 1; i < method.getRows(); i++){
					Vector<String> loc_rows = new Vector<String>();
					Vector<String> sub_rows = new Vector<String>();
					for(int j = 0; j <= method.getCols() + 1; j++){
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
								isLongMethod = "TRUE";
								loc_rows.add("TRUE");
							}

							else {
								isLongMethod = "FALSE";
								loc_rows.add("FALSE");
							}
						}
						else if (j==9) {
							String iPlasma_str = method.getCellContentStr(i, j);
							if(iPlasma_str.equals("TRUE")) {
								iPlasma = "TRUE";
							}
							else {
								iPlasma = "FALSE";
							}
							loc_rows.add(method.getCellContentStr(i, j));
							j++;
							String PMD_str = method.getCellContentStr(i, j);
							if(PMD_str == "TRUE") {
								PMD = "TRUE";
							}
							else {
								PMD = "FALSE";
							}
							loc_rows.add(method.getCellContentStr(i, j));

						}

						else if (j == method.getCols()) {
							String qualityMethod;
							QualityFactors factor = new QualityFactors();
							qualityMethod = factor.whatFactor(iPlasma, isLongMethod);
							if(qualityMethod.equals("DCI")) DCI++;
							if(qualityMethod.equals("DII")) DII++;
							if(qualityMethod.equals("ADCI")) ADCI++;
							if(qualityMethod.equals("ADII")) ADII++;
							sub_rows.add(qualityMethod);
						}
						else if (j >= method.getCols()) {
							String qualityMethod;
							QualityFactors factor = new QualityFactors();
							qualityMethod = factor.whatFactor(PMD, isLongMethod);
							if(qualityMethod.equals("DCI")) DCI1++;
							if(qualityMethod.equals("DII")) DII1++;
							if(qualityMethod.equals("ADCI")) ADCI1++;
							if(qualityMethod.equals("ADII")) ADII1++;
							sub_rows.add(qualityMethod);
						}

						else{
							loc_rows.add(method.getCellContentStr(i, j));
							if (j == 0) {
								sub_rows.add(method.getCellContentStr(i, j));
							}
						}
					}loc_values.add(loc_rows);
					sub_values.add(sub_rows);
				}
				
				DefaultTableModel long_model = new DefaultTableModel(loc_values,headers);
				DefaultTableModel sub_model = new DefaultTableModel(sub_values, sub_headers);
				long_table.setModel(long_model);
				sub_table.setModel(sub_model);
				sub_headers.clear();
				long_table.setAutoCreateRowSorter(true);
				sub_table.setAutoCreateRowSorter(true);
				JFrame long_frame = new JFrame();
				JFrame sub_frame = new JFrame();
				JScrollPane long_scroll = new JScrollPane(long_table);
				JScrollPane sub_scroll = new JScrollPane(sub_table);
				long_frame.setSize(800, 600);
				sub_frame.setSize(400, 300);
				//long_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				long_frame.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				sub_frame.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				long_frame.setTitle("is_long_method Search");
				sub_frame.setTitle("is_long_method Results");
				BorderLayout border = new BorderLayout();
				sub_frame.setLayout(border);
				long_frame.setVisible(true);
				long_frame.setResizable(true);
				long_frame.add(long_scroll);
				sub_frame.setVisible(true);
				sub_frame.setResizable(true);
				sub_frame.add(sub_scroll, BorderLayout.CENTER);
				JTextField sub_footer = new JTextField();
				sub_footer.setText("Metrics: LOC = "+ LOC + " CYCLO = " + CYCLO + " (is_long_method)");
				sub_footer.setEditable(false);
				sub_frame.add(sub_footer, BorderLayout.SOUTH);
				showDefects(DCI, DII, ADCI, ADII, DCI1, DII1, ADCI1, ADII1, "is_long_method");
			}

		});




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
								.addComponent(btnNewButton_2))
						.addContainerGap())
				);
		frmExcelSearch.getContentPane().setLayout(groupLayout);
	}
	
	/**
	 * 
	 * @param war warning
	 */
	private void showWarning(String war){	
		JOptionPane.showMessageDialog(frmExcelSearch,
			war,
			"Warning",
			JOptionPane.WARNING_MESSAGE);
	}
	
	private void showDefects(double DCI, double DII, double ADCI, double ADII,
			double DCI1, double DII1, double ADCI1, double ADII1, String type) {
		
		double total = DCI + DII + ADCI + ADII;
		DecimalFormat nf = new DecimalFormat("#.00");
		double dci_per = DCI*100/total, dii_per = DII*100/total, adci_per = ADCI*100/total, adii_per = ADII*100/total;
		double dci_per1 = DCI1*100/total, dii_per1 = DII1*100/total, adci_per1 = ADCI1*100/total, adii_per1 = ADII1*100/total;
		
		String war = "IPlasma Ocurrences" + System.lineSeparator()  
				+ "DCI = " + (int) DCI + " (" + nf.format(dci_per)+"%) " 
				+ System.lineSeparator()
				+ "DII = " + (int) DII + " (" + nf.format(dii_per)+"%)"
				+ System.lineSeparator() 
				+ "ADCI = " + (int) ADCI + " (" + nf.format(adci_per)+"%) "
				+ System.lineSeparator()
				+ "ADII = " + (int) ADII + " (" + nf.format(adii_per)+"%) "
				+ System.lineSeparator()
		;
		war = war + System.lineSeparator() 
			+ "PMD Ocurrences" + System.lineSeparator() 
			+ "DCI = " + (int) DCI1 + " (" + nf.format(dci_per1)+"%) " 
			+ System.lineSeparator()
			+ "DII = " + (int) DII1 + " (" + nf.format(dii_per1)+"%)" 
			+ System.lineSeparator()
			+ "ADCI = " + (int) ADCI1 + " (" + nf.format(adci_per1)+"%) "
			+ System.lineSeparator()
			+ "ADII = " + (int) ADII1 + " (" + nf.format(adii_per1)+"%) "
		;
		
		JOptionPane.showMessageDialog(frmExcelSearch,
				war,
				"Defects Results: " + type,
				JOptionPane.WARNING_MESSAGE);
		
	}
	
}



