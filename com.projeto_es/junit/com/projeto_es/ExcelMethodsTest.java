package com.projeto_es;

import static org.junit.Assert.assertArrayEquals;

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
import org.junit.Test;

public class ExcelMethodsTest {
	
	int row, col;
	String fileDir =  "//home//zagalao//Transferências//Defeitos.xlsx";
	Workbook wb = getWorkbook(fileDir);
	Sheet sh = wb.getSheetAt(0);
	
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
	public Sheet getSheet(String sheetName) {
		return wb.getSheet(sheetName);
	}
	public int getRows() {
		return sh.getLastRowNum() + 1;
	}
	
	public int getCols() {
		return sh.getRow(0).getLastCellNum();
	}
	
	public String getCellContentStr(int row, int col) {
		if(col == -1) throw new IllegalArgumentException("Error col not found");
		FormulaEvaluator objFormulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
		DataFormatter objDefaultFormat = new DataFormatter();
		Cell cell =	sh.getRow(row).getCell(col);
		objFormulaEvaluator.evaluate(cell);
		return objDefaultFormat.formatCellValue(cell,objFormulaEvaluator);
	}
	public int findColByName(String name) {
		for (int i  = 0; i < getCols(); i++ ) {
			if(name.equals(getCellContentStr(0, i))){
				return i;
			}
		}
		return -1;
	}

	String file = "//home//zagalao//Transferências//Defeitos.xlsx";
	@Test
	public void testGetWorkbook() {
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
		
		assert(wb != null);
		assert(wb.getNumberOfSheets() ==1);
		
	}
	@Test
	public void testGetSheet() {
		assert(sh != null);
		assert(wb.getSheetAt(0).getRow(0).getCell(0)!= null);
		assert(wb.getSheetAt(0).getRow(1).getCell(0).getNumericCellValue() == 1);
	}
	@Test
	public void testGetRows() {
		
		assert(sh.getLastRowNum()>= 0);
	}

	@Test
	public void testGetCols() {
		assert(sh.getRow(0).getLastCellNum()>=0);
	}

	@Test
	public void testGetCellContentStr() {
		
		if(col == -1) throw new IllegalArgumentException("Error col not found");
		FormulaEvaluator objFormulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
		DataFormatter objDefaultFormat = new DataFormatter();
		sh = wb.getSheetAt(0);
		Cell cell =	sh.getRow(row).getCell(col);
		objFormulaEvaluator.evaluate(cell);
		String str = objDefaultFormat.formatCellValue(cell,objFormulaEvaluator);
		assert(str.equals("MethodID"));
	}


	@Test
	public void testFindColByName() {
		
		String name = "PMD";
		for (int i  = 0; i < getCols(); i++ ) {
			if(name.equals(getCellContentStr(0, i))){
				assert(i == 10);
			}
		} 
			
	}
	String name = "PMD";
	@Test
	/*
	 * linkedlist tem valor?
	 */
	
	public void testGetFullColByName() {
		LinkedList<String> colValues = new LinkedList<String>();
		
		int rows = getRows();
		for( int i = 0; i < rows; i++ )
			colValues.add(getCellContentStr(i, findColByName(name)));
		assert(colValues.getFirst().equals(name));
			
	}
	
	int column = 0;
	@Test
	public void testGetFullCol() {
		LinkedList<String> colValues = new LinkedList<String>();
		
		int rows = getRows();
		for( int i = 0; i < rows; i++ )
			colValues.add(getCellContentStr(i, column));
		assert(colValues.getFirst().equals("MethodID"));
	}
	/* 
	 * escolher um valor e converter para bytes (array). setvalue()na celula e faço assert entre o valor escolhido e o valor da celula
	 */
	@Test
	public void testSetValue() {
		int row = 2;
		int col = 2;
		String expected = "Metr";
		Cell cell =	sh.getRow(row).getCell(col);
		cell.setCellValue(expected);
		byte[]expecteds = expected.getBytes();
		byte[] results = getCellContentStr(row, col).getBytes();
		assertArrayEquals(expecteds, results);
	}
}
