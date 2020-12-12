package com.projeto_es;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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
	

}
