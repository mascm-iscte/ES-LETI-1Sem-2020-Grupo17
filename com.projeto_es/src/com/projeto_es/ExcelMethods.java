package com.projeto_es;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

import org.apache.poi.EncryptedDocumentException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;



public class ExcelMethods {
	
	private Workbook wb = null;
	private Sheet sh = null;
	
	/**
	 * Constructor 
	 * @param wb is a workbook apache poi 
	 * @param sh is a sheet apache poi
	 */
	public ExcelMethods(Workbook wb, Sheet sh) {
		this.wb = wb;
		this.sh = sh;
	}
	
	public ExcelMethods() {
		
	}
	/**
	 * 
	 * @param wb is a workbook apache poi
	 * 
	 */
	public void setWB(Workbook wb) {
		this.wb = wb;
	}
	/**
	 * 
	 * @param sh is a sheet apache poi
	 */
	public void setSH(Sheet sh) {
		this.sh = sh;
	}	
	
	/**
	 * @param fileDir is the file directory
	 * @return workbook or null if problem getting file occurs
	 */
	public Workbook getWorkbook(String fileDir) {
		FileInputStream fis;
		try {
			fis = new FileInputStream(fileDir);
			wb = WorkbookFactory.create(fis);
			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wb;
	}
	
	/**
	 * @param sheetName is the name of the sheet you want
	 * @return return sheet by name
	 */
	public Sheet getSheet(String sheetName) {
		return wb.getSheet(sheetName);
	}
	/**
	 * @return number of rows in current sheet
	 */
	public int getRows() {
		return sh.getLastRowNum() + 1;
	}
	/**
	 * @return number of columns in current sheet
	 */
	public int getCols() {
		return sh.getRow(0).getLastCellNum();
	}
	/**
	 * 
	 * @param row
	 * @param col
	 * @return cell contents as a string
	 */
	public String getCellContentStr(int row, int col) {
		if(col == -1) throw new IllegalArgumentException("Error col not found");
		FormulaEvaluator objFormulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
		DataFormatter objDefaultFormat = new DataFormatter();
		Cell cell =	sh.getRow(row).getCell(col);
		objFormulaEvaluator.evaluate(cell);
		return objDefaultFormat.formatCellValue(cell,objFormulaEvaluator);
	}
	
	/**
	 * @param name
	 * @return number of the column with that name ( -1 means not found )
	 */
	
	public int findColByName(String name) {
		for (int i  = 0; i < getCols(); i++ ) {
			if(name.equals(getCellContentStr(0, i))){
				return i;
			}
		}
		return -1;
	}
	/**
	 * 
	 * @param name
	 * @return LinkedList with values of the selected column
	 */
	public LinkedList<String> getFullColByName(String name) {
		LinkedList<String> colValues = new LinkedList<String>();
		int rows = getRows();
		for( int i = 0; i < rows; i++ )
			colValues.add(getCellContentStr(i, findColByName(name)));
		return colValues;		
	} 
	
	/**
	 * @param col is column
	 * @return a LinkedList of the selected column 
	 */
	public LinkedList<String> getFullCol(int col) {
		LinkedList<String> colValues = new LinkedList<String>();
		int rows = getRows();
		for( int i = 0; i < rows; i++ )
			colValues.add(getCellContentStr(i, col));
		return colValues;		
	}
	
	
	/**
	 * 
	 * @param value is the value to set
	 * @param row is the row
	 * @param col is column
	 * 
	 * Sets value in a cell in current sheet
	 */
	public void setValue(String value, int row, int col) {
		Cell cell =	sh.getRow(row).getCell(col);
		cell.setCellValue(value);
	}


}
