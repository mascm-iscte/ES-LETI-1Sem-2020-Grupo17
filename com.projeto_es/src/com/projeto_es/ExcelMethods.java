package com.projeto_es;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbookFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelMethods {
	
	private Workbook wb = null;
	private Sheet sh = null;
	
	/**
	 * 
	 * @param Workbook
	 * @param Sheet
	 */
	public ExcelMethods(Workbook wb, Sheet sh) {
		this.wb = wb;
		this.sh = sh;
	}
	
	/**
	 * @param filedirectory
	 * @return workbook or null if problem getting file occurs
	 */
	public Workbook getWorkbook(String fileDir) {
		FileInputStream fis;
		try {
			fis = new FileInputStream(fileDir);
			wb = HSSFWorkbookFactory.create(fis);
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
	 * @param sheetName
	 * @return
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
	 * @return LinkedList<String> with values of the selected column
	 */
	public LinkedList<String> getFullColByName(String name) {
		LinkedList<String> colValues = new LinkedList<String>();
		int rows = getRows();
		for( int i = 0; i < rows; i++ )
			colValues.add(getCellContentStr(i, findColByName(name)));
		return colValues;		
	}
	
	/**
	 * @param col
	 * @return a LinkedList<String> of the selected column
	 */
	public LinkedList<String> getFullCol(int col) {
		LinkedList<String> colValues = new LinkedList<String>();
		int rows = getRows();
		for( int i = 0; i < rows; i++ )
			colValues.add(getCellContentStr(i, col));
		return colValues;		
	}
	
	/**
	 * @param col_1
	 * @param col_2
	 * @return number of differences between 2 columns
	 */
	public int numberOfDif2ColsByName(String name_1, String name_2) {
		int sumDiferences = 0;
		int index = 0;
		int col_1 = findColByName(name_1);
		int col_2 = findColByName(name_2);
		LinkedList<String> colValues_1 = new LinkedList<String>();
		LinkedList<String> colValues_2 = new LinkedList<String>();
		colValues_1.addAll(getFullCol(col_1));
		colValues_2.addAll(getFullCol(col_2));
		colValues_1.removeFirst();
		colValues_2.removeFirst();
		for (String string : colValues_1) {
			if(!string.equals(colValues_2.get(index)))
				sumDiferences++;
			index++;
		}	
		return sumDiferences;
	}
	
	
	
	//testes
	public static void main(String[] args) {
		
	}

}
