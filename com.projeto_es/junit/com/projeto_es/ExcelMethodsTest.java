package com.projeto_es;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

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
	Workbook wb;
	Sheet sh ;
	
	@Test
	public void testGetWorkbook() throws EncryptedDocumentException, IOException {
		FileInputStream fis;
		Workbook wb = null;
		
			fis = new FileInputStream(fileDir);
			wb = WorkbookFactory.create(fis);
			fis.close();
		
		
		assert(wb != null);
		assert(wb.getNumberOfSheets() ==1);
	}
	@Test
	public void testGetSheet() throws EncryptedDocumentException, IOException {
		FileInputStream fis;
		Workbook wb = null;
		Sheet sh = null;
		
			fis = new FileInputStream(fileDir);
			wb = WorkbookFactory.create(fis);
			fis.close();
		
		sh = wb.getSheetAt(0);
		assert(sh != null);
		assert(wb.getSheetAt(0).getRow(0).getCell(0)!= null);
		assert(wb.getSheetAt(0).getRow(1).getCell(0).getNumericCellValue() == 1);
	}
	@Test
	public void testGetRows() throws EncryptedDocumentException, IOException {
		FileInputStream fis;
		Workbook wb = null;
		Sheet sh = null;
		fis = new FileInputStream(fileDir);
		wb = WorkbookFactory.create(fis);
		fis.close();
		
		sh = wb.getSheetAt(0);
		assert(sh.getLastRowNum()>= 0);
	}

	@Test
	public void testGetCols() throws EncryptedDocumentException, IOException {
		FileInputStream fis;
		Workbook wb = null;
		Sheet sh = null;
		
		fis = new FileInputStream(fileDir);
		wb = WorkbookFactory.create(fis);
		fis.close();
		
		sh = wb.getSheetAt(0);
		assert(sh.getRow(0).getLastCellNum()>=0);
	}

	@Test
	public void testGetCellContentStr() throws EncryptedDocumentException, IOException {
		FileInputStream fis;
		Workbook wb = null;
		Sheet sh = null;
		fis = new FileInputStream(fileDir);
		wb = WorkbookFactory.create(fis);
		fis.close();
		
		sh = wb.getSheetAt(0);
		if(col == -1) throw new IllegalArgumentException("Error col not found");
		FormulaEvaluator objFormulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
		DataFormatter objDefaultFormat = new DataFormatter();
		sh = wb.getSheetAt(0);
		Cell cell =	sh.getRow(row).getCell(col);
		objFormulaEvaluator.evaluate(cell);
		String str = objDefaultFormat.formatCellValue(cell,objFormulaEvaluator);
		assert(str.equals("MethodID"));
	}
	
}
